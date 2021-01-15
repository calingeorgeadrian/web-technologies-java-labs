import { Component } from '@angular/core';
import { Globals } from './models/globals';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'BGJ-Shop-Admin';

  constructor(public globals: Globals) {
  }
}
