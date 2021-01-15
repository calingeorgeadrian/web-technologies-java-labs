import { Component, OnInit } from '@angular/core';
import { GameModel } from '../models/game.model';
import { BGGService } from '../services/bgg.service';
import { GameService } from '../services/game.service';

@Component({
  selector: 'app-products-gallery',
  templateUrl: './products-gallery.component.html',
  styleUrls: ['./products-gallery.component.scss']
})
export class ProductsGalleryComponent implements OnInit {
  games: GameModel[] = [];
  date: Date = new Date(Date.now() - 604800000);
  pageOfItems: Array<any>;

  constructor(private bggService: BGGService,
    private gameService: GameService) {
  }

  ngOnInit() {
    this.gameService.getAllGames().pipe()
      .subscribe(games => {
        this.games = games;
      }, error => {
        console.log(error);
      });
  }

  onChangePage(pageOfItems: Array<any>) {
    this.pageOfItems = pageOfItems;
  }

  checkDate(date: Date) {
    return new Date(date).getTime() >= new Date(this.date).getTime();
  }
}
