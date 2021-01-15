import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { DomSanitizer } from "@angular/platform-browser";
import { Observable } from "rxjs";
import { map } from "rxjs/operators";
import { environment } from '../../environments/environment';
import { GameModel } from '../models/game.model';
import { BGGService } from './bgg.service';
import { PopularGameModel } from '../models/popularGame.model';

@Injectable({ providedIn: 'root' })
export class GameService {

  constructor(private http: HttpClient, private sanitizer: DomSanitizer, private bggService: BGGService) { }

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

  saveGame(game: GameModel) {
    return this.http.post<any>(environment.apiUrl + '/game/create', game)
      .pipe(map(returnValue => {
        return returnValue;
      }));
  }

  updateGame(game: GameModel) {
    return this.http.put<any>(environment.apiUrl + '/game/update', game)
      .pipe(map(returnValue => {
        return returnValue;
      }));
  }

  importFromBgg(games: GameModel[]) {
    return this.http.post<any>(environment.apiUrl + '/game/importGames', games)
      .pipe(map(returnValue => {
        return returnValue;
      }));
  }

  deleteGame(id: number) {
    return this.http.delete<any>(environment.apiUrl + '/game/delete/' + id)
      .pipe(map(returnValue => {
        return returnValue;
      }));
  }
}
