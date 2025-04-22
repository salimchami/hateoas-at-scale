import {CartProduct} from './cart-product';

export class CartProducts {
  constructor(readonly value: Array<CartProduct>) {
  }

  static from(cartProducts: any) {
    return new CartProducts(cartProducts.value.map((product: any) => new CartProduct(product.product, product.quantity)));
  }

  add(cartProduct: CartProduct) {
    const existingProductIndex = this.value.findIndex(
      item => item.product.name === cartProduct.product.name
    );

    if (existingProductIndex >= 0) {
      this.value[existingProductIndex] = new CartProduct(
        this.value[existingProductIndex].product,
        this.value[existingProductIndex].quantity + cartProduct.quantity
      );
    } else {
      this.value.push(cartProduct);
    }
  }
}
