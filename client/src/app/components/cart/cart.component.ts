import {Component, OnInit} from '@angular/core';
import {MatCardModule} from '@angular/material/card';
import {MatListModule} from '@angular/material/list';
import {MatIconModule} from '@angular/material/icon';
import {CurrencyPipe, NgForOf, NgIf, NgOptimizedImage, UpperCasePipe} from '@angular/common';
import {ActivatedRoute, RouterLink} from '@angular/router';
import {MatButtonModule} from '@angular/material/button';
import {Cart} from './cart';
import {MatTooltip} from '@angular/material/module.d-DKQBC69L';

@Component({
  selector: 'app-cart',
  imports: [MatButtonModule, MatCardModule, MatListModule, MatIconModule, NgOptimizedImage, UpperCasePipe, RouterLink, NgForOf, NgIf, CurrencyPipe, MatTooltip],
  templateUrl: './cart.component.html',
  styleUrl: './cart.component.scss'
})
export class CartComponent implements OnInit {
  cart: Cart = {} as Cart;

  constructor(private readonly activatedRoute: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.activatedRoute.data.subscribe((data: any) => this.cart = data.cart);
  }

  removeProduct(product: any) {
    // TODO not yet implemented
  }

  clearCart() {

  }

  checkout() {

  }
}
