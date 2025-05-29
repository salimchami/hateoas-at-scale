import {Routes} from '@angular/router';
import {LayoutComponent} from './components/layout/layout.component';
import {HomeComponent} from './components/home/home.component';
import {ProductsComponent} from './components/products/products.component';
import {CartComponent} from './components/cart/cart.component';
import {ProductComponent} from './components/product/product.component';
import {ProductResolver} from './components/product/product.resolver';
import {CartResolver} from './components/cart/cart.resolver';
import {UsersComponent} from './components/users/users.component';
import {AuthGuard} from './shared/authentication/auth.guard';

export const routes: Routes = [
  {path: '', redirectTo: '/home', pathMatch: 'full'},
  {
    path: '',
    component: LayoutComponent,
    children: [
      {
        path: 'home',
        component: HomeComponent,
        title: 'Home'
      },
      {
        path: 'products',
        canActivate: [AuthGuard],
        component: ProductsComponent,
        title: 'Products',
        data: {linkName: 'products'}
      },
      {
        path: 'products/:name',
        canActivate: [AuthGuard],
        component: ProductComponent,
        title: ':name',
        resolve: {
          product: ProductResolver
        }
      },
      {
        path: 'cart',
        canActivate: [AuthGuard],
        component: CartComponent,
        title: 'Cart',
        data: {
          linkName: 'my-cart'
        },
        resolve: {
          cart: CartResolver
        }
      },
      {
        path: 'users',
        canActivate: [AuthGuard],
        component: UsersComponent,
        title: 'Users',
        data: {linkName: 'all-users'}
      },
      {
        path: '**',
        redirectTo: '/home'
      }
    ]
  }
];
