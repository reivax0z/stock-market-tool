import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {ApiService} from "../../service/api.service";
import {first} from "rxjs/operators";

@Component({
  selector: 'app-remove-cash',
  templateUrl: './remove-cash.component.html',
  styleUrls: ['./remove-cash.component.css']
})
export class RemoveCashComponent implements OnInit {

  editForm: FormGroup;

  constructor(private formBuilder: FormBuilder,
              private router: Router,
              private apiService: ApiService) { }

  ngOnInit() {
    this.editForm = this.formBuilder.group({
      amount: [0, Validators.required]
    });
  }

  onSubmit() {
    console.log("Submitting cash withdrawal to backend...")
    this.apiService.removeCash(this.editForm.value.amount)
      .pipe(first())
      .subscribe(
        data => {
          if(data) {
            alert('Cash removed successfully.');
            this.router.navigate(['show-portfolio']);
          }
        },
        error => {
          alert(JSON.stringify(error.error.message));
        });
  }

}
