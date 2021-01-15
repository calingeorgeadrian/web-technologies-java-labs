import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { DomSanitizer } from "@angular/platform-browser";
import { Observable } from "rxjs";
import { map } from "rxjs/operators";
import { environment } from '../../environments/environment';
import { GameModel } from '../models/game.model';
import { CartItemModel } from '../models/cartItem.model';

@Injectable({ providedIn: 'root' })
export class CartService {

  constructor(private http: HttpClient, private sanitizer: DomSanitizer) { }

  getCart(userId: string): Observable<CartItemModel[]> {
    return this.http.get<CartItemModel[]>(environment.apiUrl + '/cart/' + userId)
      .pipe(map(returnValue => {
        return returnValue;
      }));
  }

  addItem(item: CartItemModel) {
    return this.http.post<any>(environment.apiUrl + '/cart/add', item)
      .pipe(map(returnValue => {
        return returnValue;
      }));
  }

  updateItem(item: CartItemModel) {
    return this.http.put<any>(environment.apiUrl + '/cart/update', item)
      .pipe(map(returnValue => {
        return returnValue;
      }));
  }

  removeItem(item: CartItemModel) {
    return this.http.delete<any>(environment.apiUrl + '/cart/remove/userId=' + item.userId + '&gameId=' + item.gameId)
      .pipe(map(returnValue => {
        return returnValue;
      }));
  }

  emptyCart(userId: string) {
    return this.http.delete<any>(environment.apiUrl + '/cart/empty/' + userId)
      .pipe(map(returnValue => {
        return returnValue;
      }));
  }
}
