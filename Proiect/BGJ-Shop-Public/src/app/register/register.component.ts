import { Component, OnInit } from '@angular/core';
import { UserLoginModel } from '../models/userLogin.model';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {
  user: UserLoginModel = new UserLoginModel();

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private userService: UserService) { }

  ngOnInit() {
  }

  register() {
    this.userService.register(this.user)
      .subscribe(
        saveResult => {
          console.log("User registered");
          this.router.navigate(['/login']);
        });
  }

}
