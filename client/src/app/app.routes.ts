import {Routes} from '@angular/router';
import {LayoutComponent} from './components/layout/layout.component';
import {HomeComponent} from './components/home/home.component';
import {ProductsComponent} from './components/products/products.component';
import {CartComponent} from './components/cart/cart.component';
import {ProductComponent} from './components/product/product.component';
import {ProductResolver} from './components/product/product.resolver';
import {CartResolver} from './components/cart/cart.resolver';

export const routes: Routes = [
  {path: '', redirectTo: '/home', pathMatch: 'full'},
  {
    path: '',
    component: LayoutComponent,
    children: [
      {path: 'home', component: HomeComponent, title: 'Home'},
      {path: 'products', component: ProductsComponent, title: 'Products', data: {linkName: 'products'}},
      {
        path: 'products/:name', component: ProductComponent, title: ':name',
        resolve: {
          product: ProductResolver
        }
      },
      {
        path: 'cart', component: CartComponent, title: 'Cart', data: {linkName: 'cart'}, resolve: {
          cart: CartResolver
        }
      },
    ]
  }
];
