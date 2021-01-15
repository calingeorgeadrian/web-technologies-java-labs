import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { PromotionService } from '../services/promotion.service';
import { PromotionModel } from '../models/promotion.model';

@Component({
  selector: 'app-promotion-edit',
  templateUrl: './promotion-edit.component.html',
  styleUrls: ['./promotion-edit.component.scss']
})
export class PromotionEditComponent implements OnInit {
  promotion: PromotionModel = new PromotionModel();
  date: Date = new Date();
  id: number;
  constructor(
    public route: ActivatedRoute,
    public router: Router,
    private promotionService: PromotionService) {
  }

  ngOnInit() {
    this.route
      .queryParams
      .subscribe(params => {
        this.id = params['id'];
        if (this.id) {
          this.promotionService.getPromotion(this.id).pipe()
            .subscribe(promotionDetails => {
              this.promotion = promotionDetails;
            }, error => {
              console.log(error);
            });
        }
      });
  }

  submit() {
    if (this.id) {
      this.promotionService.updatePromotion(this.promotion)
        .subscribe(
          saveResult => {
            console.log("Promotion updated");
            this.router.navigate(['/promotions']);
          });
    }
    else {
      this.promotionService.savePromotion(this.promotion)
        .subscribe(
          saveResult => {
            console.log("Promotion saved");
            this.router.navigate(['/promotions']);
          });
    }
  }

}
