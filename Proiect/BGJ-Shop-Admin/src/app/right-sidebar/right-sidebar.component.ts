import { Component, OnInit } from '@angular/core';
import { GameService } from '../services/game.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Globals } from '../models/globals';
import { UserService } from '../services/user.service';
import { StatsService } from '../services/stats.service';
import { ReportModel } from '../models/report.model';

@Component({
  selector: 'app-right-sidebar',
  templateUrl: './right-sidebar.component.html',
  styleUrls: ['./right-sidebar.component.scss']
})
export class RightSidebarComponent implements OnInit {
  dailyReport: ReportModel = new ReportModel();

  constructor(public globals: Globals,
    private router: Router,
    private userService: UserService,
    private statsService: StatsService) { }

  ngOnInit() {
    this.statsService.getReport(1).pipe()
      .subscribe(report => {
        this.dailyReport = report;
      }, error => {
        console.log(error);
      });
  }

  logout() {
    this.userService.logout();
    this.router.navigate(['/']);
  }
}
