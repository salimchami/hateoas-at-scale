import {Component, OnInit} from '@angular/core';
import {UserService} from '../user/user-service';
import {ProductsService} from './products.service';
import {Products} from './products';
import {User} from '../user/user';

@Component({
  selector: 'app-products',
  imports: [],
  providers: [ProductsService],
  templateUrl: './products.component.html',
  styleUrl: './products.component.scss'
})
export class ProductsComponent implements OnInit {
  products: Products = {list: []} as unknown as Products;

  constructor(private readonly userService: UserService,
              private readonly productsService: ProductsService,
  ) {
  }

  ngOnInit(): void {
    if (!this.userService.currentUser) {
      this.userService.refreshCurrentUser(true).subscribe(user => this.loadProducts(user));
    } else {
      this.loadProducts(this.userService.currentUser);
    }
  }

  private loadProducts(user: User) {
    this.productsService.findAll(user._links['products'].href)
      .subscribe(products => this.products = products);
  }
}
