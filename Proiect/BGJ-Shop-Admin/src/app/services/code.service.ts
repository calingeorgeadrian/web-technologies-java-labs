import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { DomSanitizer } from "@angular/platform-browser";
import { Observable } from "rxjs";
import { map } from "rxjs/operators";
import { environment } from '../../environments/environment';
import { PromotionModel } from '../models/promotion.model';
import { CodeModel } from '../models/code.model';

@Injectable({ providedIn: 'root' })
export class CodeService {

  constructor(private http: HttpClient, private sanitizer: DomSanitizer) { }

  getAllCodes(): Observable<CodeModel[]> {
    return this.http.get<CodeModel[]>(environment.apiUrl + '/code/getAll')
      .pipe(map(returnValue => {
        return returnValue;
      }));
  }

  addCode(code: CodeModel) {
    return this.http.post<any>(environment.apiUrl + '/code/addCode', code)
      .pipe(map(returnValue => {
        return returnValue;
      }));
  }

  deleteCode(code: string) {
    return this.http.delete<any>(environment.apiUrl + '/code/deleteCode/' + code)
      .pipe(map(returnValue => {
        return returnValue;
      }));
  }
}
