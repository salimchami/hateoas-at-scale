import {Product} from './product';

export class LocalCartProduct {
  constructor(readonly product: Product, readonly quantity: number) {
  }
}
