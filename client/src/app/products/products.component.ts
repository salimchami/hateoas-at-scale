import {Component, OnInit} from '@angular/core';
import {User} from '../user/user';
import {UserService} from '../user/user-service';
import {ProductsService} from './products.service';
import {Products} from './products';

@Component({
  selector: 'app-products',
  imports: [],
  templateUrl: './products.component.html',
  styleUrl: './products.component.scss'
})
export class ProductsComponent implements OnInit {
  currentUser: User = {} as User;
  products: Products = {list: []} as unknown as Products;

  constructor(private readonly userService: UserService,
              private readonly productsService: ProductsService
  ) {
  }

  ngOnInit(): void {
    this.userService.currentUser$.subscribe(user => this.currentUser = user);
  }
}
