import {LocalCartProduct} from './local-cart-product';

export class LocalCartProducts {
  constructor(readonly value: Array<LocalCartProduct>) {
  }

  static from(cartProducts: any) {
    return new LocalCartProducts(cartProducts.value.map((product: any) => new LocalCartProduct(product.product, product.quantity)));
  }

  add(cartProduct: LocalCartProduct) {
    const existingProductIndex = this.value.findIndex(
      item => item.product.name === cartProduct.product.name
    );

    if (existingProductIndex >= 0) {
      this.value[existingProductIndex] = new LocalCartProduct(
        this.value[existingProductIndex].product,
        this.value[existingProductIndex].quantity + cartProduct.quantity
      );
    } else {
      this.value.push(cartProduct);
    }
  }
}
