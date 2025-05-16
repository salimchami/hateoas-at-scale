import {HateoasModel} from "../../shared/hateoas.model";

export class CartProduct extends HateoasModel {
  constructor(readonly name: string, readonly totalPrice: number, readonly quantity: number) {
    super();
  }
}
