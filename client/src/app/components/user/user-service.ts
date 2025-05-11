import {Injectable} from '@angular/core';
import {HttpService} from '../../shared/http.service';
import {BehaviorSubject, Observable} from 'rxjs';
import {User} from './user';
import {map} from 'rxjs/operators';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../../environments/environment';
import {AuthService} from '../../shared/authentication';

@Injectable()
export class UserService extends HttpService {
  private readonly currentUserSubject = new BehaviorSubject<User>({} as User);
  currentUser$ = this.currentUserSubject.asObservable();

  constructor(private readonly authService: AuthService,
              readonly httpClient: HttpClient) {
    super(httpClient);
  }

  get currentUser(): User | null {
    const user = this.currentUserSubject.value;
    if (user?.username) {
      return user;
    }
    return null;
  }

  findCurrentUser(): Observable<User> {
    return this.get(this.url(`${environment.startupEndpoint}`))
      .pipe(map(user => {
        const userObj = User.from(user);
        this.setCurrentUser(userObj);
        return userObj;
      }));
  }

  logout() {
    this.currentUserSubject.next({} as User);
    this.authService.logout();
  }

  private setCurrentUser(user: User) {
    this.currentUserSubject.next(user);
  }
}
