import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot } from '@angular/router';
import { Observable } from 'rxjs';
import { Globals } from '../models/globals';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
  constructor(
    private router: Router,
    public globals: Globals) { }

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
    let claimType: string = next.data["claimType"];
    let id: string = next.queryParams['id'];
    if (this.globals.user && this.globals.user.roleType == 1 && claimType == "isLoggedIn") {
      return true;
    }
    else {
      this.router.navigate(['']);
    }
  }
}
