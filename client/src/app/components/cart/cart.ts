import {HateoasModel} from '../../shared/hateoas.model';

class CartUser {
  constructor(readonly username: string) {
  }
}

class CartProduct extends HateoasModel {
  constructor(readonly name: string, readonly totalPrice: number, readonly quantity: number) {
    super();
  }
}

export class Cart {
  constructor(readonly totalPrice: number, readonly user: CartUser, readonly products: Array<CartProduct>) {
  }
}
