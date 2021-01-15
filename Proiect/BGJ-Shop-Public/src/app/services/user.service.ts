import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { DomSanitizer } from "@angular/platform-browser";
import { Observable } from "rxjs";
import { map } from "rxjs/operators";
import { environment } from '../../environments/environment';
import { UserModel } from '../models/user.model';
import { UserLoginModel } from '../models/userLogin.model';
import { Globals } from '../models/globals';

@Injectable({ providedIn: 'root' })
export class UserService {

  constructor(private http: HttpClient,
    private sanitizer: DomSanitizer,
    public globals: Globals) { }

  getUser(userId: string): Observable<UserModel> {
    return this.http.get<UserModel>(environment.apiUrl + '/user/' + userId)
      .pipe(map(returnValue => {
        return returnValue;
      }));
  }

  register(user: UserLoginModel) {
    return this.http.post<any>(environment.apiUrl + '/user/register', user)
      .pipe(map(returnValue => {
        return returnValue;
      }));
  }

  login(userLogin: UserLoginModel): Observable<UserModel> {
    var currentService = this;
    return this.http.put<UserModel>(environment.apiUrl + '/user/login', userLogin)
      .pipe(map(loginInfo => {
        if (loginInfo) {
          localStorage.setItem('currentLoginInfo', JSON.stringify(loginInfo));
          sessionStorage.setItem('currentLoginInfo', JSON.stringify(loginInfo));

          currentService.globals.user = loginInfo;
        }

        return loginInfo;
      }));
  }

  update(user: UserModel) {
    var currentService = this;
    return this.http.put<any>(environment.apiUrl + '/user/update', user)
      .pipe(map(returnValue => {
        localStorage.setItem('currentLoginInfo', JSON.stringify(user));
        sessionStorage.setItem('currentLoginInfo', JSON.stringify(user));

        currentService.globals.user = user;
        return returnValue;
      }));
  }

  logout() {
    localStorage.removeItem('currentLoginInfo');
    sessionStorage.removeItem('currentLoginInfo');
    this.globals.user = null;
  }
}
