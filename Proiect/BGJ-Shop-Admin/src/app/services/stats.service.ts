import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { DomSanitizer } from "@angular/platform-browser";
import { Observable } from "rxjs";
import { map } from "rxjs/operators";
import { environment } from '../../environments/environment';
import { OrderStatsModel } from '../models/orderStats.model';
import { PopularGameModel } from '../models/popularGame.model';
import { ReportModel } from '../models/report.model';

@Injectable({ providedIn: 'root' })
export class StatsService {

  constructor(private http: HttpClient, private sanitizer: DomSanitizer) { }

  getPopularGames(): Observable<PopularGameModel[]> {
    return this.http.get<PopularGameModel[]>(environment.apiUrl + '/stats/popularGames')
      .pipe(map(returnValue => {
        return returnValue;
      }));
  }

  getOrderStats(): Observable<OrderStatsModel> {
    return this.http.get<OrderStatsModel>(environment.apiUrl + '/stats/orderStats')
      .pipe(map(returnValue => {
        return returnValue;
      }));
  }

  getReport(type: number): Observable<ReportModel> {
    return this.http.get<ReportModel>(environment.apiUrl + '/stats/report/' + type)
      .pipe(map(returnValue => {
        return returnValue;
      }));
  }
}
