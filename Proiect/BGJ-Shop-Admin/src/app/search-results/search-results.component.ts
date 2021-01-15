import { Component, OnInit } from '@angular/core';
import { GameService } from '../services/game.service';
import { GameModel } from '../models/game.model';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-search-results',
  templateUrl: './search-results.component.html',
  styleUrls: ['./search-results.component.scss']
})
export class SearchResultsComponent implements OnInit {
  term: string;
  games: GameModel[] = [];
  date: Date = new Date(Date.now() - 604800000);
  pageOfItems: Array<any>;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private gameService: GameService) {
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

  onChangePage(pageOfItems: Array<any>) {
    this.pageOfItems = pageOfItems;
  }

  checkDate(date: Date) {
    return new Date(date).getTime() >= new Date(this.date).getTime();
  }
}
