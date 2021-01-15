import { Component, OnInit } from '@angular/core';
import { PromotionModel } from '../models/promotion.model';
import { PromotionService } from '../services/promotion.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-promotions-list',
  templateUrl: './promotions-list.component.html',
  styleUrls: ['./promotions-list.component.scss']
})
export class PromotionsListComponent implements OnInit {
  promotions: PromotionModel[] = [];
  pageOfItems: Array<any>;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private promotionService: PromotionService) { }

  ngOnInit() {
    this.promotionService.getAllPromotions().pipe()
      .subscribe(promotions => {
        this.promotions = promotions
      }, error => {
        console.log(error);
      });
  }

  onChangePage(pageOfItems: Array<any>) {
    this.pageOfItems = pageOfItems;
  }

  delete(id: number) {
    this.promotionService.deletePromotion(id)
      .subscribe(
        saveResult => {
          console.log("Promotion deleted");
          this.promotions = this.promotions.filter(p => p.id != id);
        });
  }

  edit(id: number) {
    this.router.navigate(['/editPromotion'], { queryParams: { id: id } });
  }

}
