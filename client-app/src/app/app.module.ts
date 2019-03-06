import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ShowPortfolioComponent } from './component/show-portfolio/show-portfolio.component';
import { TradeComponent } from './component/trade/trade.component';
import { ReactiveFormsModule } from "@angular/forms";
import {ApiService} from "./service/api.service";
import {HttpClientModule} from "@angular/common/http";
import { AddCashComponent } from './component/add-cash/add-cash.component';
import { RemoveCashComponent } from './component/remove-cash/remove-cash.component';

@NgModule({
  declarations: [
    AppComponent,
    ShowPortfolioComponent,
    TradeComponent,
    AddCashComponent,
    RemoveCashComponent
  ],
  imports: [
    BrowserModule,
    ReactiveFormsModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [ApiService],
  bootstrap: [AppComponent]
})
export class AppModule { }
