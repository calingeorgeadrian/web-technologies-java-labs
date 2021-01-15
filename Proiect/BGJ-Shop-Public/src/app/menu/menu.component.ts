import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Globals } from '../models/globals';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.scss']
})
export class MenuComponent implements OnInit {
  term: string;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    public globals: Globals) { }

  ngOnInit() {
  }

  search() {
    this.router.navigate(['/searchResults'], { queryParams: { term: this.term } });
  }
}
