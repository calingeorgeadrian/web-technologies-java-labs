import { Component, OnInit } from '@angular/core';
import { GameService } from '../services/game.service';
import { PopularGameModel } from '../models/popularGame.model';
import { OrderService } from '../services/order.service';
import { OrderModel } from '../models/order.model';
import { StatsService } from '../services/stats.service';
import { ReportModel } from '../models/report.model';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {
  games: PopularGameModel[] = [];
  orders: OrderModel[] = [];
  dailyReport: ReportModel = new ReportModel();
  weeklyReport: ReportModel = new ReportModel();
  monthlyReport: ReportModel = new ReportModel();

  constructor(private gameService: GameService,
    private orderService: OrderService,
    private statsService: StatsService) { }

  ngOnInit() {
    this.statsService.getReport(1).pipe()
      .subscribe(report => {
        this.dailyReport = report;
      }, error => {
        console.log(error);
      });

    this.statsService.getReport(2).pipe()
      .subscribe(report => {
        this.weeklyReport = report;
      }, error => {
        console.log(error);
      });

    this.statsService.getReport(3).pipe()
      .subscribe(report => {
        this.monthlyReport = report;
      }, error => {
        console.log(error);
      });

    this.statsService.getPopularGames().pipe()
      .subscribe(games => {
        this.games = games;
      }, error => {
        console.log(error);
      });

    this.orderService.getRecent().pipe()
      .subscribe(orders => {
        this.orders = orders;
      }, error => {
        console.log(error);
      });
  }

}
