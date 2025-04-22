import {Product} from './product';

export class CartProduct {
  constructor(readonly product: Product, readonly quantity: number) {
  }
}
