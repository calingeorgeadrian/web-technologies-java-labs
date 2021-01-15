import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { DomSanitizer } from "@angular/platform-browser";
import { Observable } from "rxjs";
import { map } from "rxjs/operators";
import { environment } from '../../environments/environment';
import { GameModel } from '../models/game.model';
import { OrderModel } from '../models/order.model';
import { OrderItemModel } from '../models/orderItem.model';

@Injectable({ providedIn: 'root' })
export class OrderService {

  constructor(private http: HttpClient, private sanitizer: DomSanitizer) { }

  getOrder(id: number): Observable<OrderModel> {
    return this.http.get<OrderModel>(environment.apiUrl + '/order/' + id)
      .pipe(map(returnValue => {
        return returnValue;
      }));
  }

  getOrdersForUser(userId: string): Observable<OrderModel[]> {
    return this.http.get<OrderModel[]>(environment.apiUrl + '/order/getOrdersForUser/' + userId)
      .pipe(map(returnValue => {
        return returnValue;
      }));
  }

  getGamesForOrder(orderId: number): Observable<OrderItemModel[]> {
    return this.http.get<OrderItemModel[]>(environment.apiUrl + '/order/getGamesForOrder/' + orderId)
      .pipe(map(returnValue => {
        return returnValue;
      }));
  }

  create(order: OrderModel) {
    return this.http.post<any>(environment.apiUrl + '/order/create', order)
      .pipe(map(returnValue => {
        return returnValue;
      }));
  }
}
