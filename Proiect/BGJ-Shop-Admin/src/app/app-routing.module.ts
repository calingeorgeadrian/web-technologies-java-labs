import { RouterModule, Routes } from '@angular/router';
import { ProductDetailsComponent } from './product-details/product-details.component';
import { ProductsGalleryComponent } from './products-gallery/products-gallery.component';
import { ProductsListComponent } from './products-list/products-list.component';
import { LoginComponent } from './login/login.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { OrdersListComponent } from './orders-list/orders-list.component';
import { OrderDetailsComponent } from './order-details/order-details.component';
import { PromotionsListComponent } from './promotions-list/promotions-list.component';
import { CodesListComponent } from './codes-list/codes-list.component';
import { PromotionDetailsComponent } from './promotion-details/promotion-details.component';
import { ProductEditComponent } from './product-edit/product-edit.component';
import { PromotionEditComponent } from './promotion-edit/promotion-edit.component';
import { CodeEditComponent } from './code-edit/code-edit.component';
import { BggImportComponent } from './bgg-import/bgg-import.component';
import { OrderEditComponent } from './order-edit/order-edit.component';
import { AuthGuard } from './security/auth,guard';
import { SearchResultsComponent } from './search-results/search-results.component';

export const appRoutes: Routes = [
  { path: '', component: LoginComponent, pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  {
    path: 'dashboard', component: DashboardComponent,
    canActivate: [AuthGuard],
    data: { claimType: 'isAdmin' } },
  {
    path: 'product', component: ProductDetailsComponent,
    canActivate: [AuthGuard],
    data: { claimType: 'isAdmin' } },
  {
    path: 'editProduct', component: ProductEditComponent,
    canActivate: [AuthGuard],
    data: { claimType: 'isAdmin' } },
  {
    path: 'bggImport', component: BggImportComponent,
    canActivate: [AuthGuard],
    data: { claimType: 'isAdmin' } },
  {
    path: 'products', component: ProductsGalleryComponent,
    canActivate: [AuthGuard],
    data: { claimType: 'isAdmin' } },
  {
    path: 'products-list', component: ProductsListComponent,
    canActivate: [AuthGuard],
    data: { claimType: 'isAdmin' } },
  {
    path: 'order', component: OrderDetailsComponent,
    canActivate: [AuthGuard],
    data: { claimType: 'isAdmin' } },
  {
    path: 'orders', component: OrdersListComponent,
    canActivate: [AuthGuard],
    data: { claimType: 'isAdmin' } },
  {
    path: 'editOrder', component: OrderEditComponent,
    canActivate: [AuthGuard],
    data: { claimType: 'isAdmin' } },
  {
    path: 'editPromotion', component: PromotionEditComponent,
    canActivate: [AuthGuard],
    data: { claimType: 'isAdmin' } },
  {
    path: 'promotion', component: PromotionDetailsComponent,
    canActivate: [AuthGuard],
    data: { claimType: 'isAdmin' } },
  {
    path: 'promotions', component: PromotionsListComponent,
    canActivate: [AuthGuard],
    data: { claimType: 'isAdmin' } },
  {
    path: 'addCode', component: CodeEditComponent,
    canActivate: [AuthGuard],
    data: { claimType: 'isAdmin' }
  },
  {
    path: 'codes', component: CodesListComponent,
    canActivate: [AuthGuard],
    data: { claimType: 'isAdmin' }
  },
  {
    path: 'searchResults', component: SearchResultsComponent,
    canActivate: [AuthGuard],
    data: { claimType: 'isAdmin' }
  },
  // otherwise redirect to home
  { path: '**', redirectTo: '' }
];

export const appRouting = RouterModule.forChild(appRoutes);
