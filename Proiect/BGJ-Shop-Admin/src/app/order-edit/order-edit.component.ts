import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { OrderService } from '../services/order.service';
import { OrderItemModel } from '../models/orderItem.model';
import { OrderModel } from '../models/order.model';

@Component({
  selector: 'app-order-edit',
  templateUrl: './order-edit.component.html',
  styleUrls: ['./order-edit.component.scss']
})
export class OrderEditComponent implements OnInit {
  id: number;
  order: OrderModel = new OrderModel();
  games: OrderItemModel[] = [];

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private orderService: OrderService) { }

  ngOnInit() {
    this.route
      .queryParams
      .subscribe(params => {
        this.id = params['id'];
        if (this.id) {
          this.orderService.getOrder(this.id).pipe()
            .subscribe(order => {
              this.order = order;

              this.orderService.getGamesForOrder(order.id).pipe()
                .subscribe(games => {
                  this.games = games;
                }, error => {
                  console.log(error);
                });
            }, error => {
              console.log(error);
            });
        }
      });
  }

  submit() {
    this.orderService.updateOrder(this.order)
      .subscribe(
        saveResult => {
          console.log("order updated");
          this.router.navigate(['/order'], { queryParams: { id: this.id } });
        });
  }
}
