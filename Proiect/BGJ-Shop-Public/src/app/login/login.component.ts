import { Component, OnInit } from '@angular/core';
import { UserLoginModel } from '../models/userLogin.model';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from '../services/user.service';

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
    private userService: UserService) { }

  ngOnInit() {
  }

  login() {
    this.userService.login(this.user)
      .subscribe(
        user => {
          console.log("User logged in");
          this.router.navigate(['/account']);
        });
  }
}
