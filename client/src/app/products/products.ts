import {Product} from './product';
import {HateoasModel} from '../../shared/hateoas.model';

export class Products extends HateoasModel {
  constructor(readonly list: Array<Product>) {
    super();
  }
}
