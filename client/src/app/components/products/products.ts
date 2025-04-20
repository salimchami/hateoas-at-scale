import {Product} from './product';
import {HateoasModel} from '../../shared/hateoas.model';

export class Products extends HateoasModel {
  constructor(readonly list: Array<Product>) {
    super();
  }

  static from(products: any) {
    return new Products(products.list.map((product: any) => {
      return new Product(product.name, product.reference, product.price);
    }));
  }
}
