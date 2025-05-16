import {HateoasModel} from '../../shared/hateoas.model';
import {User} from '../user/user';

export class Users extends HateoasModel {
  constructor(readonly list: Array<User>) {
    super();
  }

  static from(json: any) {
    let users = new Users(json.list.map((item: any) => {
      const user = User.from(item);
      user._links = item._links;
      return user;
    }));
    users._links = json._links;
    return users;
  }
}
