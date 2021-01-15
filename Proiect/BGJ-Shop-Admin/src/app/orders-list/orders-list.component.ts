import { Component, OnInit } from '@angular/core';
import { OrderService } from '../services/order.service';
import { Globals } from '../models/globals';
import { Router, ActivatedRoute } from '@angular/router';
import { OrderModel } from '../models/order.model';
import { OrderStatsModel } from '../models/orderStats.model';
import { StatsService } from '../services/stats.service';

@Component({
  selector: 'app-orders-list',
  templateUrl: './orders-list.component.html',
  styleUrls: ['./orders-list.component.scss']
})
export class OrdersListComponent implements OnInit {
  orders: OrderModel[] = [];
  stats: OrderStatsModel = new OrderStatsModel();
  pageOfItems: Array<any>;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    public globals: Globals,
    private orderService: OrderService,
    private statsService: StatsService) { }

  ngOnInit() {
    this.orderService.getAll().pipe()
      .subscribe(orders => {
        this.orders = orders;
      }, error => {
        console.log(error);
      });

    this.getStats();
  }

  onChangePage(pageOfItems: Array<any>) {
    this.pageOfItems = pageOfItems;
  }

  getStats() {
    this.statsService.getOrderStats().pipe()
      .subscribe(stats => {
        this.stats = stats;
      }, error => {
        console.log(error);
      });
  }

  updateStatus(order: OrderModel, value: number) {
    order.status = value;
    this.orderService.updateStatus(order)
      .subscribe(
        saveResult => {
          console.log("Status updated");
        });
  }

}
