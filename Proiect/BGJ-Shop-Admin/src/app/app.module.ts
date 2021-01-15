import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';
import { appRouting } from './app-routing.module';
import { AppComponent } from './app.component';
import { BggImportComponent } from './bgg-import/bgg-import.component';
import { CodeEditComponent } from './code-edit/code-edit.component';
import { CodesListComponent } from './codes-list/codes-list.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { JwPaginationComponent } from './helpers/jwPagination';
import { LeftSidebarComponent } from './left-sidebar/left-sidebar.component';
import { LoginComponent } from './login/login.component';
import { Constants } from './models/constants';
import { Globals } from './models/globals';
import { UserModel } from './models/user.model';
import { OrderDetailsComponent } from './order-details/order-details.component';
import { OrdersListComponent } from './orders-list/orders-list.component';
import { ProductDetailsComponent } from './product-details/product-details.component';
import { ProductEditComponent } from './product-edit/product-edit.component';
import { ProductsGalleryComponent } from './products-gallery/products-gallery.component';
import { ProductsListComponent } from './products-list/products-list.component';
import { PromotionDetailsComponent } from './promotion-details/promotion-details.component';
import { PromotionEditComponent } from './promotion-edit/promotion-edit.component';
import { PromotionsListComponent } from './promotions-list/promotions-list.component';
import { RightSidebarComponent } from './right-sidebar/right-sidebar.component';
import { SearchComponent } from './search/search.component';
import { BGGService } from './services/bgg.service';
import { OrderEditComponent } from './order-edit/order-edit.component';
import { SearchResultsComponent } from './search-results/search-results.component';


@NgModule({
  declarations: [
    AppComponent,
    LeftSidebarComponent,
    RightSidebarComponent,
    ProductDetailsComponent,
    ProductsListComponent,
    SearchComponent,
    ProductsGalleryComponent,
    DashboardComponent,
    LoginComponent,
    OrdersListComponent,
    OrderDetailsComponent,
    JwPaginationComponent,
    PromotionsListComponent,
    PromotionDetailsComponent,
    CodesListComponent,
    ProductEditComponent,
    PromotionEditComponent,
    CodeEditComponent,
    BggImportComponent,
    OrderEditComponent,
    SearchResultsComponent
  ],
  imports: [
    appRouting,
    BrowserModule.withServerTransition({ appId: 'ng-cli-universal' }),
    HttpClientModule,
    FormsModule,
    RouterModule.forRoot([])
  ],
  providers: [
    UserModel,
    Globals,
    Constants,
    BGGService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
