import { Component, OnInit } from '@angular/core';
import { GameModel } from '../models/game.model';
import { ActivatedRoute, Router } from '@angular/router';
import { GameService } from '../services/game.service';
import { CartService } from '../services/cart.service';
import { CartItemModel } from '../models/cartItem.model';
import { WishlistService } from '../services/wishlist.service';
import { WishlistItemModel } from '../models/wishlistItem.model';
import { Globals } from '../models/globals';

@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.component.html',
  styleUrls: ['./product-details.component.scss']
})
export class ProductDetailsComponent implements OnInit {
  id: any;
  game: GameModel = new GameModel();
  quantity: number = 1;
  date: Date = new Date(Date.now() - 604800000);

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    public globals: Globals,
    private gameService: GameService,
    private cartService: CartService,
    private wishlistService: WishlistService) { }

  ngOnInit() {
    this.route
      .queryParams
      .subscribe(params => {
        this.id = params['id'];
        if (this.id) {
          this.gameService.getGame(this.id).pipe()
            .subscribe(gameDetails => { 
              this.game = gameDetails;
            }, error => {
              console.log(error);
            });
        }
      });
  }

  checkDate(date: Date) {
    return new Date(date).getTime() >= new Date(this.date).getTime();
  }

  updateQuantity(value: number) {
    this.quantity += value;

    if (this.quantity < 0)
      this.quantity = 0;
  }

  addToCart() {
    var item = new CartItemModel();
    item.gameId = this.game.id;
    item.quantity = this.quantity;
    item.userId = this.globals.user.id;

    this.cartService.addItem(item)
      .subscribe(
        saveResult => {
          console.log("Item added to cart");
          this.router.navigate(['/cart']);
        });
  }

  addToWishlist() {
    var item = new WishlistItemModel();
    item.gameId = this.game.id;
    item.userId = this.globals.user.id;

    this.wishlistService.addItem(item)
      .subscribe(
        saveResult => {
          console.log("Item added to wishlist");
          this.router.navigate(['/wishlist']);
        });
  }

}
