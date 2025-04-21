import {HateoasModel} from './hateoas.model';

export class Product extends HateoasModel {
  constructor(
    readonly name: string,
    readonly reference: string,
    readonly price: number,
  ) {
    super();
  }

  static from(json: any) {
    let product = new Product(json.name, json.reference, json.price);
    product._links = json._links;
    return product;
  }
}
