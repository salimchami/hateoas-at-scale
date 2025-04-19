import {Injectable} from '@angular/core';
import {HttpService} from '../../shared/http.service';
import {environment as env} from './../../environments/environment';
import {BehaviorSubject, Observable} from 'rxjs';
import {User} from './user';
import {map} from 'rxjs/operators';

@Injectable()
export class UserService extends HttpService {
  private currentUserSubject = new BehaviorSubject<User>({} as User);
  currentUser$ = this.currentUserSubject.asObservable();


  loadCurrentUser(username: string): Observable<User> {
    return this.get(this.url(`${env.startupEndpoint}/${username}`))
      .pipe(map(user => User.from(user)));
  }

  setCurrentUser(user: User) {
    this.currentUserSubject.next(user);
  }
}
