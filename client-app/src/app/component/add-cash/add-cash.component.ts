import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {ApiService} from "../../service/api.service";
import {first} from "rxjs/operators";

@Component({
  selector: 'app-add-cash',
  templateUrl: './add-cash.component.html',
  styleUrls: ['./add-cash.component.css']
})
export class AddCashComponent implements OnInit {

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
    console.log("Submitting cash deposit to backend...")
    this.apiService.addCash(this.editForm.value.amount)
      .pipe(first())
      .subscribe(
        data => {
          if(data) {
            alert('Cash added successfully.');
            this.router.navigate(['show-portfolio']);
          }
        },
        error => {
          alert(JSON.stringify(error.error.message));
        });
  }

}
