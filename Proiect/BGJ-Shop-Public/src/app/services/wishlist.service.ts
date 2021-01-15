import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { DomSanitizer } from "@angular/platform-browser";
import { Observable } from "rxjs";
import { map } from "rxjs/operators";
import { environment } from '../../environments/environment';
import { WishlistItemModel } from '../models/wishlistItem.model';

@Injectable({ providedIn: 'root' })
export class WishlistService {

  constructor(private http: HttpClient, private sanitizer: DomSanitizer) { }

  getWishlist(userId: string): Observable<WishlistItemModel[]> {
    return this.http.get<WishlistItemModel[]>(environment.apiUrl + '/wishlist/' + userId)
      .pipe(map(returnValue => {
        return returnValue;
      }));
  }

  addItem(item: WishlistItemModel) {
    return this.http.post<any>(environment.apiUrl + '/wishlist/add', item)
      .pipe(map(returnValue => {
        return returnValue;
      }));
  }

  removeItem(item: WishlistItemModel) {
    return this.http.delete<any>(environment.apiUrl + '/wishlist/remove/userId=' + item.userId + '&gameId=' + item.gameId)
      .pipe(map(returnValue => {
        return returnValue;
      }));
  }

  clearWishlist(userId: string) {
    return this.http.delete<any>(environment.apiUrl + '/wishlist/clear/' + userId)
      .pipe(map(returnValue => {
        return returnValue;
      }));
  }
}
