import {Routes} from '@angular/router';
import {ProductsComponent} from './products/products.component';
import {HomeComponent} from './home/home.component';
import {LayoutComponent} from './layout/layout.component';
import {CartComponent} from './cart/cart.component';

export const routes: Routes = [
  {path: '', redirectTo: '/home', pathMatch: 'full'},
  {
    path: '',
    component: LayoutComponent,
    children: [
      {path: 'home', component: HomeComponent, title: 'Home'},
      {path: 'products', component: ProductsComponent, title: 'Products', data: {linkName: 'products'}},
      {path: 'cart', component: CartComponent, title: 'Cart', data: {linkName: 'cart'}},
    ]
  }
];
