import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { DomSanitizer } from "@angular/platform-browser";
import { Observable } from "rxjs";
import { map } from "rxjs/operators";
import { environment } from '../../environments/environment';
import { GameModel } from '../models/game.model';

@Injectable({ providedIn: 'root' })
export class GameService {

  constructor(private http: HttpClient, private sanitizer: DomSanitizer) { }

  getGame(id: number): Observable<GameModel> {
    return this.http.get<GameModel>(environment.apiUrl + '/game/' + id)
      .pipe(map(returnValue => {
        return returnValue;
      }));
  }

  getAllGames(): Observable<GameModel[]> {
    return this.http.get<GameModel[]>(environment.apiUrl + '/game/all')
      .pipe(map(returnValue => {
        return returnValue;
      }));
  }

  search(term: string): Observable<GameModel[]> {
    return this.http.get<GameModel[]>(environment.apiUrl + '/game/search/' + term)
      .pipe(map(returnValue => {
        return returnValue;
      }));
  }

  filterGames(type: string, minPrice: number, maxPrice: number, minPlayers: number, maxPlayers: number): Observable<GameModel[]> {
    return this.http.get<GameModel[]>(environment.apiUrl + '/game/filter/type=' + (type != null ? type : 'all') +
      '&minPrice=' + (minPrice != null ? minPrice : 0) +
      '&maxPrice=' + (maxPrice != null ? maxPrice : 0) +
      '&minPlayers=' + (minPlayers != null ? minPlayers : 0) +
      '&maxPlayers=' + (maxPlayers != null ? maxPlayers : 0))
      .pipe(map(returnValue => {
        return returnValue;
      }));
  }
}
