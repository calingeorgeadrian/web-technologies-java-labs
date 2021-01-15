import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { DomSanitizer } from "@angular/platform-browser";
import { Observable } from "rxjs";
import { map } from "rxjs/operators";
import { environment } from '../../environments/environment';
import { PromotionModel } from '../models/promotion.model';
import { CodeModel } from '../models/code.model';

@Injectable({ providedIn: 'root' })
export class PromotionService {

  constructor(private http: HttpClient, private sanitizer: DomSanitizer) { }

  getPromotion(id: number): Observable<PromotionModel> {
    return this.http.get<PromotionModel>(environment.apiUrl + '/promotion/' + id)
      .pipe(map(returnValue => {
        return returnValue;
      }));
  }

  getAllPromotions(): Observable<PromotionModel[]> {
    return this.http.get<PromotionModel[]>(environment.apiUrl + '/promotion/all')
      .pipe(map(returnValue => {
        return returnValue;
      }));
  }

  savePromotion(promotion: PromotionModel) {
    return this.http.post<any>(environment.apiUrl + '/promotion/create', promotion)
      .pipe(map(returnValue => {
        return returnValue;
      }));
  }

  updatePromotion(promotion: PromotionModel) {
    return this.http.put<any>(environment.apiUrl + '/promotion/update', promotion)
      .pipe(map(returnValue => {
        return returnValue;
      }));
  }

  deletePromotion(id: number) {
    return this.http.delete<any>(environment.apiUrl + '/promotion/delete/' + id)
      .pipe(map(returnValue => {
        return returnValue;
      }));
  }
}
