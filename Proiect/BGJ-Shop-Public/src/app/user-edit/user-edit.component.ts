import { Component, OnInit } from '@angular/core';
import { UserModel } from '../models/user.model';
import { UserService } from '../services/user.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Globals } from '../models/globals';

@Component({
  selector: 'app-user-edit',
  templateUrl: './user-edit.component.html',
  styleUrls: ['./user-edit.component.scss']
})
export class UserEditComponent implements OnInit {
  user: UserModel = new UserModel();

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    public globals: Globals,
    private userService: UserService) { }

  ngOnInit() {
    this.user = this.globals.user;
  }

  save() {
    this.userService.update(this.user)
      .subscribe(
        saveResult => {
          console.log("User updated");
          this.router.navigate(['/account']);
        });
  }

}
