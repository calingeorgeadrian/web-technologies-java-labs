import { Component, OnInit } from '@angular/core';
import { GameModel } from '../models/game.model';
import { BGGService } from '../services/bgg.service';
import { ActivatedRoute, Router } from '@angular/router';
import { GameService } from '../services/game.service';

@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.component.html',
  styleUrls: ['./product-details.component.scss']
})
export class ProductDetailsComponent implements OnInit {
  id: any;
  game: GameModel = new GameModel();

  constructor(
    private route: ActivatedRoute,
    private router: Router,
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
              this.game = gameDetails;
            }, error => {
              console.log(error);
            });
        }
      });
  }

  edit() {
    this.router.navigate(['/editProduct'], { queryParams: { id: this.id } });
  }

  delete() {
    this.gameService.deleteGame(this.id)
      .subscribe(
        saveResult => {
          console.log("Game deleted");
          this.router.navigate(['/products']);
        });
  }
}
