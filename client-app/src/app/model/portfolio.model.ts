import {Order} from "./order.model";

export class Portfolio {
  historyOrders: Order[];
  shareHoldings: Order[];
  cashBalance: number;
}
