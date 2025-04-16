import {HateoasModel} from '../../shared/hateoas.model';

export class User extends HateoasModel {
  constructor(readonly username: string,
              readonly firstname: string,
              readonly lastname: string,) {
    super();
  }

  static from(user: any): User {
    const currentUser = new User(user.username, user.firstname, user.lastname);
    currentUser._links = user._links;
    return currentUser;
  }
}
