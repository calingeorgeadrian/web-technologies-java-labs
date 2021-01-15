import { Component, OnInit } from '@angular/core';
import { Globals } from '../models/globals';
import { UserService } from '../services/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-left-sidebar',
  templateUrl: './left-sidebar.component.html',
  styleUrls: ['./left-sidebar.component.scss']
})
export class LeftSidebarComponent implements OnInit {

  constructor(public globals: Globals,
    private router: Router) { }

  ngOnInit() {
  }

}
