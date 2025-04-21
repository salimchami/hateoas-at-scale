import {Product} from '../../shared/product';
import {HateoasModel} from '../../shared/hateoas.model';

export class Products extends HateoasModel {
  constructor(readonly list: Array<Product>) {
    super();
  }

  static from(products: any) {
    return new Products(products.list.map((item: any) => {
      const product = new Product(item.name, item.reference, item.price);
      product._links = item._links;
      return product;
    }));
  }
}
