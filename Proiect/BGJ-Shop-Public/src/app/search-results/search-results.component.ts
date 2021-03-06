import { Component, OnInit } from '@angular/core';
import { GameModel } from '../models/game.model';
import { ActivatedRoute, Router } from '@angular/router';
import { Globals } from '../models/globals';
import { GameService } from '../services/game.service';
import { CartService } from '../services/cart.service';
import { WishlistService } from '../services/wishlist.service';
import { CartItemModel } from '../models/cartItem.model';
import { WishlistItemModel } from '../models/wishlistItem.model';

@Component({
  selector: 'app-search-results',
  templateUrl: './search-results.component.html',
  styleUrls: ['./search-results.component.scss']
})
export class SearchResultsComponent implements OnInit {
  term: string;
  games: GameModel[] = [];
  type: string = null;
  minPrice: number = 0;
  maxPrice: number = 0;
  minPlayers: number = 0;
  maxPlayers: number = 0;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    public globals: Globals,
    private gameService: GameService,
    private cartService: CartService,
    private wishlistService: WishlistService) {
  }

  ngOnInit() {
    this.route
      .queryParams
      .subscribe(params => {
        this.term = params['term'];
        if (this.term) {
          this.gameService.search(this.term).pipe()
            .subscribe(games => {
              this.games = games;
            }, error => {
              console.log(error);
            });
        }
      });
  }

  addToCart(gameId: number) {
    var cartItem = new CartItemModel();
    cartItem.gameId = gameId;
    cartItem.quantity = 1;
    cartItem.userId = this.globals.user.id;

    this.cartService.addItem(cartItem)
      .subscribe(
        saveResult => {
          console.log("Item added to cart");
        });
  }

  addToWishlist(gameId: number) {
    var wishlistItem = new WishlistItemModel();
    wishlistItem.gameId = gameId;
    wishlistItem.userId = this.globals.user.id;

    this.wishlistService.addItem(wishlistItem)
      .subscribe(
        saveResult => {
          console.log("Item added to wishlist");
        });
  }

  setType(type: string) {
    this.type = type;
    this.filter();
  }

  setPrices(minPrice: number, maxPrice: number) {
    this.minPrice = minPrice;
    this.maxPrice = maxPrice;
    this.filter();
  }

  clear() {
    this.type = null;
    this.minPrice = 0;
    this.maxPrice = 0;
    this.minPlayers = 0;
    this.maxPlayers = 0;
    this.filter();
  }

  filter() {
    this.gameService.filterGames((this.type != null ? this.type : null),
      (this.minPrice != null ? this.minPrice : null),
      (this.maxPrice != null ? this.maxPrice : null),
      (this.minPlayers != null ? this.minPlayers : null),
      (this.maxPlayers != null ? this.maxPlayers : null)).pipe()
      .subscribe(games => {
        this.games = games;
      }, error => {
        console.log(error);
      });
  }
}
