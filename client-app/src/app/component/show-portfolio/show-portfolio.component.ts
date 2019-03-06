import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {ApiService} from "../../service/api.service";
import {Portfolio} from "../../model/portfolio.model";

@Component({
  selector: 'app-show-portfolio',
  templateUrl: './show-portfolio.component.html',
  styleUrls: ['./show-portfolio.component.css']
})
export class ShowPortfolioComponent implements OnInit {

  portfolio: Portfolio;

  constructor(private router: Router,
              private apiService: ApiService) { }

  ngOnInit() {
    this.apiService.getPortfolio()
      .subscribe( data => {
        this.portfolio = data;
      });
  }

  trade(uuid: string): void {
    window.localStorage.removeItem("tradeUuid");
    window.localStorage.setItem("tradeUuid", uuid);
    this.router.navigate(['trade']);
  };

  deposit(): void {
    this.router.navigate(['deposit']);
  };

  withdraw(): void {
    this.router.navigate(['withdraw']);
  };

}
