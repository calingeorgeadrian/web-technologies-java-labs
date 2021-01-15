import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';
import { AccountComponent } from './account/account.component';
import { appRouting } from './app-routing.module';
import { AppComponent } from './app.component';
import { FooterComponent } from './footer/footer.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { MenuComponent } from './menu/menu.component';
import { Globals } from './models/globals';
import { UserModel } from './models/user.model';
import { OrderDetailsComponent } from './order-details/order-details.component';
import { ProductDetailsComponent } from './product-details/product-details.component';
import { ProductsGalleryComponent } from './products-gallery/products-gallery.component';
import { RegisterComponent } from './register/register.component';
import { ShoppingCartComponent } from './shopping-cart/shopping-cart.component';
import { UserEditComponent } from './user-edit/user-edit.component';
import { WishlistComponent } from './wishlist/wishlist.component';
import { SearchResultsComponent } from './search-results/search-results.component';

@NgModule({
  declarations: [
    AppComponent,
    AccountComponent,
    LoginComponent,
    RegisterComponent,
    OrderDetailsComponent,
    ProductDetailsComponent,
    ShoppingCartComponent,
    WishlistComponent,
    MenuComponent,
    FooterComponent,
    HomeComponent,
    ProductsGalleryComponent,
    UserEditComponent,
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
    Globals],
  bootstrap: [AppComponent]
})
export class AppModule { }
