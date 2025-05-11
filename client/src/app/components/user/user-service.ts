import {Injectable} from '@angular/core';
import {HttpService} from '../../shared/http.service';
import {BehaviorSubject, Observable} from 'rxjs';
import {User} from './user';
import {map} from 'rxjs/operators';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../../environments/environment';
import {AuthService} from '../../shared/authentication';
import {Users} from '../users/users';

@Injectable()
export class UserService extends HttpService {
  readonly currentUser = new BehaviorSubject<User | null>(null);

  constructor(private readonly authService: AuthService,
              readonly httpClient: HttpClient) {
    super(httpClient);
  }

  findAll(href: string) {
    return this.get(href)
      .pipe(map(users => Users.from(users)));

  }

  findCurrentUser(): Observable<User> {
    return this.get(this.url(`${environment.startupEndpoint}`))
      .pipe(map(user => {
        const userObj = User.from(user);
        this.currentUser.next(userObj);
        return userObj;
      }));
  }

  logout() {
    this.currentUser.next({} as User);
    this.authService.logout();
  }
}
