import {Stock} from "./stock.model";

export class Share {
  stock: Stock;
  action: string;

  quantity: number;
  pricePaid: number;
  uuid: string;
}
