import { Component, OnInit } from '@angular/core';
import { Globals } from '../models/globals';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from '../services/user.service';
import { UserModel } from '../models/user.model';
import { OrderService } from '../services/order.service';
import { OrderModel } from '../models/order.model';

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.scss']
})
export class AccountComponent implements OnInit {
  user: UserModel = new UserModel();
  orders: OrderModel[] = [];

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    public globals: Globals,
    private userService: UserService,
    private orderService: OrderService) { }

  ngOnInit() {
    if (this.globals.user) {
      this.userService.getUser(this.globals.user.id).pipe()
        .subscribe(userDetails => {
          this.user = userDetails;
        }, error => {
          console.log(error);
        });

      this.orderService.getOrdersForUser(this.globals.user.id).pipe()
        .subscribe(orders => {
          this.orders = orders;
        }, error => {
          console.log(error);
        });
    }
  }

  logout() {
    this.userService.logout();
  }

}
