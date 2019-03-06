import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {ApiService} from "../../service/api.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Stock} from "../../model/stock.model";
import {OrderInput} from "../../model/order.input";
import * as uuid from 'uuid/v1';
import {first} from "rxjs/operators";

@Component({
  selector: 'app-trade',
  templateUrl: './trade.component.html',
  styleUrls: ['./trade.component.css']
})
export class TradeComponent implements OnInit {

  orderInput: OrderInput;
  editForm: FormGroup;
  stocks: Map<string, Stock>;
  stocksArray: string[];

  constructor(private formBuilder: FormBuilder,
              private router: Router,
              private apiService: ApiService) { }

  ngOnInit() {
    let tradeUuid = window.localStorage.getItem("tradeUuid");

    this.editForm = this.formBuilder.group({
      uuid: [''],
      shareUuid: [''],
      action: ['', Validators.required],
      symbol: ['', Validators.required],
      quantity: [0, Validators.required],
      price: [0, Validators.required]
    });

    this.apiService.getStocks()
      .subscribe( data => {
        this.stocks = data;
        this.stocksArray = [];

        Object.keys(data).forEach(key => {
          this.stocksArray.push(data[key])
        });

        this.orderInput = {
          uuid: uuid(),
          shareUuid: tradeUuid !== "undefined" ? tradeUuid : uuid(),
          price: 0,
          quantity: 0,
          action: tradeUuid !== "undefined" ? "SELL" : "BUY",
          symbol: ''
        };

        if (tradeUuid !== "undefined") {
          this.orderInput.shareUuid = tradeUuid;
        }

        this.editForm.setValue(this.orderInput);
      });

    this.onChanges();
  }

  onChanges(): void {
    this.editForm.get('symbol').valueChanges.subscribe(val => {
      if (this.stocks[val] !== undefined) {

        this.editForm.patchValue({
          price: this.editForm.value.quantity * this.stocks[val].price
        });
      } else {
        this.editForm.patchValue({
          price: 0
        });
      }
    });

    this.editForm.get('quantity').valueChanges.subscribe(val => {
      if (this.stocks[this.editForm.value.symbol] !== undefined) {
        this.editForm.patchValue({
          price: this.editForm.value.quantity * this.stocks[this.editForm.value.symbol].price
        });
      } else {
        this.editForm.patchValue({
          price: 0
        });
      }
    });
  }

  onSubmit() {
    this.orderInput = this.editForm.value;

    console.log("Submitting trade to backend...")
    this.apiService.placeOrder({
      uuid: this.orderInput.uuid,
      shareUuid: this.orderInput.shareUuid,
      stock: this.stocks[this.orderInput.symbol],
      action: this.orderInput.action,
      quantity: this.orderInput.quantity,
      pricePaid: this.orderInput.price
    }).pipe(first())
      .subscribe(
        data => {
          if(data) {
            alert('Order placed successfully.');
            this.router.navigate(['show-portfolio']);
          }
        },
        error => {
          alert(JSON.stringify(error.error.message));
        });
  }

}
