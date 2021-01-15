import { Component, OnInit } from '@angular/core';
import { CodeModel } from '../models/code.model';
import { PromotionService } from '../services/promotion.service';
import { ActivatedRoute, Router } from '@angular/router';
import { CodeService } from '../services/code.service';

@Component({
  selector: 'app-codes-list',
  templateUrl: './codes-list.component.html',
  styleUrls: ['./codes-list.component.scss']
})
export class CodesListComponent implements OnInit {
  codes: CodeModel[] = [];
  pageOfItems: Array<any>;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private codeService: CodeService) { }

  ngOnInit() {
    this.codeService.getAllCodes().pipe()
      .subscribe(codes => {
        this.codes = codes;
      }, error => {
        console.log(error);
      });
  }

  onChangePage(pageOfItems: Array<any>) {
    this.pageOfItems = pageOfItems;
  }

  delete(code: string) {
    this.codeService.deleteCode(code)
      .subscribe(
        saveResult => {
          console.log("Code deleted");
          this.codes = this.codes.filter(g => g.code != code);
        });
  }

}
