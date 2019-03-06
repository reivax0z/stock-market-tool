import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {ShowPortfolioComponent} from "./component/show-portfolio/show-portfolio.component";
import {TradeComponent} from "./component/trade/trade.component";
import {AddCashComponent} from "./component/add-cash/add-cash.component";
import {RemoveCashComponent} from "./component/remove-cash/remove-cash.component";

const routes: Routes = [
  { path: 'show-portfolio', component: ShowPortfolioComponent },
  { path: 'trade', component: TradeComponent },
  { path: 'deposit', component: AddCashComponent },
  { path: 'withdraw', component: RemoveCashComponent },
  { path : '', component : ShowPortfolioComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
