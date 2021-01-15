import { Component, OnInit } from '@angular/core';
import { OrderModel } from '../models/order.model';
import { OrderService } from '../services/order.service';
import { Router, ActivatedRoute } from '@angular/router';
import { OrderItemModel } from '../models/orderItem.model';

@Component({
  selector: 'app-order-details',
  templateUrl: './order-details.component.html',
  styleUrls: ['./order-details.component.scss']
})
export class OrderDetailsComponent implements OnInit {
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

  updateStatus(order: OrderModel, value: number) {
    order.status = value;
    this.orderService.updateStatus(order)
      .subscribe(
        saveResult => {
          console.log("Status updated");
          this.router.navigate(['/order'], { queryParams: { id: order.id } });
        });
  }

}
