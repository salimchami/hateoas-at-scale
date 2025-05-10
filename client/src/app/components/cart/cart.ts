import {CartProduct} from './cart-product';

class CartUser {
  constructor(readonly username: string) {
  }
}

export class Cart {
  constructor(readonly totalPrice: number, readonly user: CartUser, readonly products: Array<CartProduct>) {
  }
}
