import {Component, OnInit} from '@angular/core';
import {ProductService} from './product.service';
import {Product} from '../../shared/product';
import {MatCardModule} from '@angular/material/card';
import {NgIf, NgOptimizedImage, UpperCasePipe} from '@angular/common';
import {MatButtonModule} from '@angular/material/button';
import {ActivatedRoute, RouterModule} from '@angular/router';

@Component({
  selector: 'app-product',
  imports: [MatCardModule, UpperCasePipe, NgOptimizedImage, MatButtonModule, RouterModule, NgIf],
  providers: [ProductService],
  templateUrl: './product.component.html',
  styleUrl: './product.component.scss'
})
export class ProductComponent implements OnInit {
  product: Product = {} as Product;
  quantity = 1;
  addedToCart = false;

  constructor(
    private readonly activatedRoute: ActivatedRoute,
    private readonly productService: ProductService,) {
  }

  ngOnInit(): void {
    this.activatedRoute.data.subscribe((data: any) => this.product = data.product);
  }

  decreaseQuantity() {
    if (this.quantity >= 2) {
      this.quantity--;
    }
  }

  increaseQuantity() {
    this.quantity++;
  }

  addToCart() {
    this.addedToCart = true;
    this.productService.addToCart(this.product, this.quantity);
  }
}
