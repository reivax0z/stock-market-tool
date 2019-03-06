import {Stock} from "./stock.model";

export class Order {
  stock: Stock;
  action: string;

  quantity: number;
  pricePaid: number;
  uuid: string;
  shareUuid: string;
}
