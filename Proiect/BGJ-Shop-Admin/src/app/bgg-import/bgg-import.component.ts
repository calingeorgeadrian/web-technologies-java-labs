import { Component, OnInit } from '@angular/core';
import { BGGService } from '../services/bgg.service';
import { ActivatedRoute, Router } from '@angular/router';
import { GameModel } from '../models/game.model';
import { GameService } from '../services/game.service';

@Component({
  selector: 'app-bgg-import',
  templateUrl: './bgg-import.component.html',
  styleUrls: ['./bgg-import.component.scss']
})
export class BggImportComponent implements OnInit {
  games: GameModel[] = [];
  pageOfItems: Array<any>;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private bggService: BGGService,
    private gameService: GameService) {
  }

  ngOnInit() {
    this.games = this.bggService.getUserCollection()
  }

  onChangePage(pageOfItems: Array<any>) {
    this.pageOfItems = pageOfItems;
  }

  submit() {
    this.gameService.importFromBgg(this.games)
      .subscribe(
        saveResult => {
          console.log(saveResult);
          console.log("Games saved");
          this.router.navigate(['/products-list']);
        });
  }
}
