import { Component, OnInit } from '@angular/core';
import { Constants } from '../models/constants';
import { ActivatedRoute, Router } from '@angular/router';
import { CodeModel } from '../models/code.model';
import { PromotionService } from '../services/promotion.service';
import { CodeService } from '../services/code.service';

@Component({
  selector: 'app-code-edit',
  templateUrl: './code-edit.component.html',
  styleUrls: ['./code-edit.component.scss']
})
export class CodeEditComponent implements OnInit {
  code: CodeModel = new CodeModel();

  constructor(
    public constants: Constants,
    public route: ActivatedRoute,
    public router: Router,
    private codeService: CodeService) {
    this.code.type = 0;
  }

  ngOnInit() {
  }

  selectOption(type: number) {
    this.code.type = type;
    this.code.discount = 0;
  }

  submit() {
    this.codeService.addCode(this.code)
      .subscribe(
        saveResult => {
          console.log("Code saved");
          this.router.navigate(['/codes']);
        });
  }

}
