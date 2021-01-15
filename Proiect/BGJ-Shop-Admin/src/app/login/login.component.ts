import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from '../services/user.service';
import { UserLoginModel } from '../models/userLogin.model';
import { Globals } from '../models/globals';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  user: UserLoginModel = new UserLoginModel();

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private globals: Globals,
    private userService: UserService) { }

  ngOnInit() {
    if (this.globals.user)
      this.router.navigate(['/dashboard']);
  }

  login() {
    this.userService.login(this.user)
      .subscribe(
        user => {
          console.log("User logged in");
          this.router.navigate(['/dashboard']);
        });
  }
}
