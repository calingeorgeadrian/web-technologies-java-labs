import { Component, OnInit } from '@angular/core';
import { GameModel } from '../models/game.model';
import { Constants } from '../models/constants';
import { GameService } from '../services/game.service';
import { ActivatedRoute, Router } from '@angular/router';
import { BGGService } from '../services/bgg.service';

@Component({
  selector: 'app-product-edit',
  templateUrl: './product-edit.component.html',
  styleUrls: ['./product-edit.component.scss']
})
export class ProductEditComponent implements OnInit {
  product: GameModel = new GameModel();
  id: number;
  constructor(
    public constants: Constants,
    public route: ActivatedRoute,
    public router: Router,
    private bggService: BGGService,
    private gameService: GameService) {
  }

  ngOnInit() {
    this.route
      .queryParams
      .subscribe(params => {
        this.id = params['id'];
        if (this.id) {
          this.gameService.getGame(this.id).pipe()
            .subscribe(gameDetails => {
              this.product = gameDetails;
            }, error => {
              console.log(error);
            });
        }
        else {
          this.product.type = "boardgame";
        }
      });
  }

  selectOption(type: string) {
    this.product.type = type;
  }

  importFromBgg() {
    var id = this.product.id;
    var stock = this.product.stock;
    var price = this.product.price;
    this.product = this.bggService.getGame(this.product.bggId);
    this.product.id = id;
    this.product.stock = stock;
    this.product.price = price;
  }

  submit() {
    if (this.id) {
      this.gameService.updateGame(this.product)
        .subscribe(
          saveResult => {
            console.log("Game updated");
            this.router.navigate(['/product'], { queryParams: { id: this.id } });
          });
    }
    else {
      this.gameService.saveGame(this.product)
        .subscribe(
          saveResult => {
            console.log("Game saved");
            this.router.navigate(['/products-list']);
          });
    }
  }

}
