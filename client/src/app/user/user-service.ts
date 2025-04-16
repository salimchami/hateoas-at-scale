import {Injectable} from '@angular/core';
import {HttpService} from '../../shared/http.service';
import {environment as env} from './../../environments/environment';
import {Observable} from 'rxjs';
import {User} from './user';
import {map} from 'rxjs/operators';

@Injectable()
export class UserService extends HttpService {
  currentUser: User = {} as User;

  loadCurrentUser(): Observable<User> {
    return this.get(env.startupEndpoint).pipe(map(user => User.from(user)));
  }

  setCurrentUser(user: User) {
    this.currentUser = user;
  }
}
