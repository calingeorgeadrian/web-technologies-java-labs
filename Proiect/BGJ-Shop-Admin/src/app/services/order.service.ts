import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { DomSanitizer } from "@angular/platform-browser";
import { Observable } from "rxjs";
import { map } from "rxjs/operators";
import { environment } from '../../environments/environment';
import { OrderModel } from '../models/order.model';
import { OrderItemModel } from '../models/orderItem.model';
import { OrderStatsModel } from '../models/orderStats.model';

@Injectable({ providedIn: 'root' })
export class OrderService {

  constructor(private http: HttpClient, private sanitizer: DomSanitizer) { }

  getOrder(id: number): Observable<OrderModel> {
    return this.http.get<OrderModel>(environment.apiUrl + '/order/' + id)
      .pipe(map(returnValue => {
        return returnValue;
      }));
  }

  getOrderStats(): Observable<OrderStatsModel> {
    return this.http.get<OrderStatsModel>(environment.apiUrl + '/order/stats')
      .pipe(map(returnValue => {
        return returnValue;
      }));
  }

  getAll(): Observable<OrderModel[]> {
    return this.http.get<OrderModel[]>(environment.apiUrl + '/order/all')
      .pipe(map(returnValue => {
        return returnValue;
      }));
  }

  getRecent(): Observable<OrderModel[]> {
    return this.http.get<OrderModel[]>(environment.apiUrl + '/order/recent')
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

  updateOrder(order: OrderModel) {
    return this.http.put<any>(environment.apiUrl + '/order/update', order)
      .pipe(map(returnValue => {
        return returnValue;
      }));
  }

  updateStatus(order: OrderModel) {
    return this.http.put<any>(environment.apiUrl + '/order/updateStatus', order)
      .pipe(map(returnValue => {
        return returnValue;
      }));
  }
}
