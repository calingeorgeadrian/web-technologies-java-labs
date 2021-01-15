import { Component, OnInit } from '@angular/core';
import { WishlistItemModel } from '../models/wishlistItem.model';
import { ActivatedRoute, Router } from '@angular/router';
import { WishlistService } from '../services/wishlist.service';
import { CartService } from '../services/cart.service';
import { CartItemModel } from '../models/cartItem.model';
import { Globals } from '../models/globals';

@Component({
  selector: 'app-wishlist',
  templateUrl: './wishlist.component.html',
  styleUrls: ['./wishlist.component.scss']
})
export class WishlistComponent implements OnInit {
  items: WishlistItemModel[] = [];

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    public globals: Globals,
    private wishlistService: WishlistService,
    private cartService: CartService) { }

  ngOnInit() {
    this.wishlistService.getWishlist(this.globals.user.id).pipe()
      .subscribe(items => {
        this.items = items;
      }, error => {
        console.log(error);
      });
  }

  addToCart(item: WishlistItemModel) {
    var cartItem = new CartItemModel();
    cartItem.gameId = item.gameId;
    cartItem.quantity = 1;
    cartItem.userId = this.globals.user.id;

    this.cartService.addItem(cartItem)
      .subscribe(
        saveResult => {
          console.log("Item added to cart");
          this.router.navigate(['/cart']);
        });
  }

  remove(item: WishlistItemModel) {
    this.wishlistService.removeItem(item)
      .subscribe(
        saveResult => {
          console.log("Item removed");
          this.items = this.items.filter(i => i.gameId != item.gameId);
        });
  }

  clear() {
    this.wishlistService.clearWishlist(this.globals.user.id)
      .subscribe(
        saveResult => {
          console.log("Wishlist cleared");
          this.items = [];
        });
  }

}
