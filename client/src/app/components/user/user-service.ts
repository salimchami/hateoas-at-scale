import {Injectable} from '@angular/core';
import {HttpService} from '../../shared/http.service';
import {BehaviorSubject, EMPTY, Observable} from 'rxjs';
import {User} from './user';
import {map} from 'rxjs/operators';
import {HttpClient} from '@angular/common/http';
import {LocalStorageService} from '../../shared/local-storage.service';
import {environment} from '../../../environments/environment';
import {Router} from '@angular/router';

@Injectable()
export class UserService extends HttpService {
  constructor(private readonly localStorageService: LocalStorageService,
              readonly httpClient: HttpClient,
              private readonly router: Router,) {
    super(httpClient);
    this.initializeFromLocalStorage();
  }

  private readonly currentUserSubject = new BehaviorSubject<User>({} as User);
  currentUser$ = this.currentUserSubject.asObservable();

  usernames: Array<string> = [
    'ada.lovelace',
    'alan.turing',
    'charles.darwin',
    'karen.spence',
    'martin.curie',
    'richard.stallman',
    'samuel.jackson',
  ];

  private initializeFromLocalStorage() {
    const storedUser = this.localStorageService.getItem<string>('currentUser');
    if (storedUser) {
      try {
        const user = JSON.parse(storedUser);
        this.currentUserSubject.next(user);
      } catch (e) {
        console.error('Error parsing stored user', e);
        this.localStorageService.removeItem('currentUser'); // Remove invalid data
      }
    }
  }

  findUser(username: string): Observable<User> {
    return this.get(this.url(`${environment.startupEndpoint}/${username}`))
      .pipe(map(user => {
        const userObj = User.from(user);
        this.setCurrentUser(userObj);
        return userObj;
      }));
  }

  get currentUser(): User | null {
    const user = this.currentUserSubject.value;
    if (user?.username) {
      return user;
    }
    return null;
  }

  private setCurrentUser(user: User) {
    this.localStorageService.setItem('currentUser', JSON.stringify(user));
    this.setSelectedUsername(user.username);
    this.currentUserSubject.next(user);
  }

  private setSelectedUsername(username: string) {
    this.localStorageService.setItem('selectedUsername', username);
  }

  get selectedUsername(): string | null {
    return this.localStorageService.getItem('selectedUsername');
  }

  refreshCurrentUser(withRedirectOnError: boolean = false): Observable<User> {
    const username = this.selectedUsername;
    if (username) {
      return this.findUser(username);
    }
    if (withRedirectOnError) {
      this.router.navigate(['/home']).then();
    }
    return EMPTY;
  }

  logout() {
    this.currentUserSubject.next({} as User);
    this.localStorageService.removeItem('currentUser');
    this.localStorageService.removeItem('selectedUsername');
  }
}
