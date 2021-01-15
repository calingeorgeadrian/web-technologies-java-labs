import { Component, OnInit } from '@angular/core';
import { CartService } from '../services/cart.service';
import { ActivatedRoute, Router } from '@angular/router';
import { CartItemModel } from '../models/cartItem.model';
import { Globals } from '../models/globals';
import { OrderModel } from '../models/order.model';
import { CodeService } from '../services/code.service';
import { OrderService } from '../services/order.service';
import { CodeModel } from '../models/code.model';
import { OrderItemModel } from '../models/orderItem.model';

@Component({
  selector: 'app-shopping-cart',
  templateUrl: './shopping-cart.component.html',
  styleUrls: ['./shopping-cart.component.scss']
})
export class ShoppingCartComponent implements OnInit {
  items: CartItemModel[] = [];
  code: CodeModel = new CodeModel();
  subtotal: number = 0;
  total: number = 0;
  order: OrderModel = new OrderModel();

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    public globals: Globals,
    private cartService: CartService,
    private codeService: CodeService,
    private orderService: OrderService) {
    if (this.globals.user) {
      this.order.userId = this.globals.user.id;
      this.order.firstName = this.globals.user.firstName;
      this.order.lastName = this.globals.user.lastName;
      this.order.phone = this.globals.user.phone;
      this.order.country = this.globals.user.country;
      this.order.city = this.globals.user.city;
      this.order.address = this.globals.user.address;
    }
  }

  ngOnInit() {
    if (this.globals.user) {
      this.cartService.getCart(this.globals.user.id).pipe()
        .subscribe(items => {
          this.items = items;
          this.calculateTotal();
        }, error => {
          console.log(error);
        });
    }
  }

  updateQuantity(item: CartItemModel, value: number) {
    item.quantity += value;

    if (item.quantity < 0)
      item.quantity = 0;

    this.update(item);
  }

  calculateTotal() {
    this.subtotal = 0;
    this.items.forEach(i => this.subtotal += (i.price != i.newPrice ? i.newPrice : i.price) * i.quantity);

    if (this.code.code) {
      console.log("has code");
      if (this.code.type == 0) {
        this.items.forEach(i => i.newPrice = (i.price != i.newPrice ? i.newPrice : i.price) - (i.price != i.newPrice ? i.newPrice : i.price) * this.code.discount / 100);
        this.total = this.subtotal - this.subtotal * (this.code.discount / 100);
      }
      else if (this.code.type == 1)
        this.total = this.subtotal - this.code.discount;
      else
        this.total = this.subtotal;
    }
    else
      this.total = this.subtotal;

    if (this.subtotal < 0)
      this.subtotal = 0;

    if (this.total < 0)
      this.total = 0;
  }

  remove(item: CartItemModel) {
    this.cartService.removeItem(item)
      .subscribe(
        saveResult => {
          console.log("Item removed");
          this.items = this.items.filter(i => i.gameId != item.gameId);
          this.calculateTotal();
        });
  }

  empty() {
    this.cartService.emptyCart(this.globals.user.id)
      .subscribe(
        saveResult => {
          this.calculateTotal();
          console.log("Cart emptied");
          this.items = [];
        });
  }

  update(item: CartItemModel) {
    this.cartService.updateItem(item)
      .subscribe(
        saveResult => {
          this.calculateTotal();
          console.log("Item updated");
          if (item.quantity == 0) {
            this.items = this.items.filter(i => i.gameId != item.gameId);
          }
        });
  }

  submitCode() {
    this.codeService.getCode(this.code.code).pipe()
      .subscribe(code => {
        if (code) {
          this.code = code;
          this.calculateTotal();
          this.order.code = code.code;
        }
      }, error => {
        console.log(error);
      });
  }

  checkout() {
    this.order.total = this.total + 20;
    this.order.status = 0;
    this.order.items = [];
    this.items.forEach(i => {
      var item = new OrderItemModel();
      item.gameId = i.gameId;
      item.price = i.newPrice;
      item.quantity = i.quantity;
      this.order.items.push(item);
    });

    this.orderService.create(this.order)
      .subscribe(
        result => {
          console.log("Checkout finished");
          this.router.navigate(['/account']);
        });
  }

}
