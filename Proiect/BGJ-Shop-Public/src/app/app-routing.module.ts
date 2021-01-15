import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { OrderDetailsComponent } from './order-details/order-details.component';
import { ProductDetailsComponent } from './product-details/product-details.component';
import { RegisterComponent } from './register/register.component';
import { HomeComponent } from './home/home.component';
import { AccountComponent } from './account/account.component';
import { ShoppingCartComponent } from './shopping-cart/shopping-cart.component';
import { WishlistComponent } from './wishlist/wishlist.component';
import { ProductsGalleryComponent } from './products-gallery/products-gallery.component';
import { UserEditComponent } from './user-edit/user-edit.component';
import { AuthGuard } from './security/auth,guard';
import { SearchResultsComponent } from './search-results/search-results.component';

export const appRoutes: Routes = [
  { path: '', component: HomeComponent, pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'product', component: ProductDetailsComponent },
  { path: 'products', component: ProductsGalleryComponent },
  { path: 'cart', component: ShoppingCartComponent },
  { path: 'searchResults', component: SearchResultsComponent },
  {
    path: 'account', component: AccountComponent,
    canActivate: [AuthGuard],
    data: { claimType: 'isLoggedIn' } },
  {
    path: 'editAccount', component: UserEditComponent,
    canActivate: [AuthGuard],
    data: { claimType: 'isLoggedIn' } },
  {
    path: 'wishlist', component: WishlistComponent,
    canActivate: [AuthGuard],
    data: { claimType: 'isLoggedIn' } },
  {
    path: 'order', component: OrderDetailsComponent,
    canActivate: [AuthGuard],
    data: { claimType: 'isLoggedIn' } },
  // otherwise redirect to home
  { path: '**', redirectTo: '' }
];

export const appRouting = RouterModule.forChild(appRoutes);
