import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { DomSanitizer } from "@angular/platform-browser";
import { Observable } from "rxjs";
import { map } from "rxjs/operators";
import { environment } from '../../environments/environment';
import { GameModel } from '../models/game.model';
import { CodeModel } from '../models/code.model';

@Injectable({ providedIn: 'root' })
export class CodeService {

  constructor(private http: HttpClient, private sanitizer: DomSanitizer) { }

  getCode(code: string): Observable<CodeModel> {
    return this.http.get<CodeModel>(environment.apiUrl + '/code/' + code)
      .pipe(map(returnValue => {
        return returnValue;
      }));
  }
}
