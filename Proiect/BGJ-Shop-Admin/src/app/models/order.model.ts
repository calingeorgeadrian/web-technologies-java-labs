import { OrderItemModel } from './orderItem.model';

export class OrderModel {
  id: number;
  userId: string;
  firstName: string;
  lastName: string;
  phone: string;
  country: string;
  city: string;
  address: string;
  note: string;
  total: number;
  code: string;
  status: number;
  datePlaced: Date;
  dateDelivered: Date;
  items: OrderItemModel[];
}
