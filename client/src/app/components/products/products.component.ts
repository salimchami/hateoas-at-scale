import {Component, OnInit} from '@angular/core';
import {UserService} from '../user/user-service';
import {ProductsService} from './products.service';
import {Products} from './products';
import {User} from '../user/user';
import {MatCardModule} from '@angular/material/card';
import {NgForOf, NgOptimizedImage, UpperCasePipe} from '@angular/common';
import {RouterLink} from '@angular/router';
import {MatGridList, MatGridTile} from '@angular/material/grid-list';
import {Product} from '../../shared/product';

@Component({
  selector: 'app-products',
  imports: [
    MatCardModule,
    NgOptimizedImage,
    NgForOf,
    UpperCasePipe,
    RouterLink,
    MatGridList,
    MatGridTile
  ],
  providers: [ProductsService],
  templateUrl: './products.component.html',
  styleUrl: './products.component.scss'
})
export class ProductsComponent implements OnInit {
  currentUser: User | null = null;
  products: Products = {list: []} as unknown as Products;

  constructor(private readonly userService: UserService,
              private readonly productsService: ProductsService,
  ) {
  }

  ngOnInit(): void {
    this.userService.currentUser.subscribe(user => {
      this.currentUser = user;
      if (this.currentUser?.username) {
        this.loadProducts();
      }
    });
  }

  selectProduct(product: Product) {
    this.productsService.selectProduct(product);
  }

  private loadProducts() {
    this.productsService.findAll(this.currentUser?._links['allProducts'].href)
      .subscribe(products => this.products = products);
  }
}
