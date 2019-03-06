import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {Observable} from "rxjs";
import {Portfolio} from "../model/portfolio.model";
import {Order} from "../model/order.model";
import {Stock} from "../model/stock.model";

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  BASE_URL: string = environment.backEndUrl;

  constructor(private http: HttpClient) { }

  getPortfolio() : Observable<Portfolio> {
    let portfolio = this.http.get<Portfolio>(this.BASE_URL + "/portfolio/");
    console.log("Portfolio received: " + JSON.stringify(portfolio));
    return portfolio;
  }

  placeOrder(order: Order): Observable<Portfolio> {
    console.log("Placing order: " + JSON.stringify(order));
    let portfolio = this.http.post<Portfolio>(this.BASE_URL + "/portfolio/", order);
    console.log("Portfolio received: " + JSON.stringify(portfolio));
    return portfolio;
  }

  getStockBySymbol(symbol: string) : Observable<Stock> {
    return this.http.get<Stock>(this.BASE_URL + "/stock/" + symbol);
  }

  getStocks() : Observable<Map<string, Stock>> {
    return this.http.get<Map<string, Stock>>(this.BASE_URL + "/stock/");
  }

  addCash(amount: number) : Observable<Portfolio> {
    let portfolio = this.http.post<Portfolio>(this.BASE_URL + "/portfolio/cash/", amount);
    console.log("Portfolio received: " + JSON.stringify(portfolio));
    return portfolio;
  }

  removeCash(amount: number) : Observable<Portfolio> {
    let portfolio = this.http.post<Portfolio>(this.BASE_URL + "/portfolio/cash-withdraw/", amount);
    console.log("Portfolio received: " + JSON.stringify(portfolio));
    return portfolio;
  }
}
