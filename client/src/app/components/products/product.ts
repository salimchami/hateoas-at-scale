import {HateoasModel} from '../../shared/hateoas.model';

export class Product extends HateoasModel {
  constructor(
    readonly name: string,
    readonly reference: string,
    readonly price: number,
  ) {
    super();
  }
}
