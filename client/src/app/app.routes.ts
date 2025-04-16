import {Routes} from '@angular/router';
import {ProductsComponent} from './products/products.component';

export const routes: Routes = [
  {path: 'products', pathMatch: 'full', component: ProductsComponent, title: 'Products'},
];
