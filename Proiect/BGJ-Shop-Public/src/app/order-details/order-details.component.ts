import { Component, OnInit } from '@angular/core';
import { OrderService } from '../services/order.service';
import { Globals } from '../models/globals';
import { Router, ActivatedRoute } from '@angular/router';
import { OrderModel } from '../models/order.model';
import { CodeService } from '../services/code.service';
import { CodeModel } from '../models/code.model';
import { OrderItemModel } from '../models/orderItem.model';

@Component({
  selector: 'app-order-details',
  templateUrl: './order-details.component.html',
  styleUrls: ['./order-details.component.scss']
})
export class OrderDetailsComponent implements OnInit {
  id: any;
  order: OrderModel = new OrderModel();
  games: OrderItemModel[] = [];
  code: CodeModel = new CodeModel();
  subtotal: number = 0;
  total: number = 0;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    public globals: Globals,
    private orderService: OrderService,
    private codeService: CodeService) { }

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

              if (order.code) {
                this.codeService.getCode(order.code).pipe()
                  .subscribe(code => {
                    this.code = code;
                  }, error => {
                    console.log(error);
                  });
              }
            }, error => {
              console.log(error);
            });
        }
      });
  }

}
