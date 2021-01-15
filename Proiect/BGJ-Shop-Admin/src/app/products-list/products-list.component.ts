import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { GameModel } from '../models/game.model';
import { GameService } from '../services/game.service';

@Component({
  selector: 'app-products-list',
  templateUrl: './products-list.component.html',
  styleUrls: ['./products-list.component.scss']
})
export class ProductsListComponent implements OnInit {
  games: GameModel[] = [];
  pageOfItems: Array<any>;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
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

  delete(id: number) {
    this.gameService.deleteGame(id)
      .subscribe(
        saveResult => {
          console.log("Game deleted");
          this.games = this.games.filter(g => g.id != id);
        });
  }

  edit(id: number) {
    this.router.navigate(['/editProduct'], { queryParams: { id: id } });
  }
}
