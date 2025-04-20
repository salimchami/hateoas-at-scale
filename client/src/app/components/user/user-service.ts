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
    super(httpClient)
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

  findUser(username: string): Observable<User> {
    return this.get(this.url(`${environment.startupEndpoint}/${username}`))
      .pipe(map(user => {
        this.setCurrentUser(user);
        return User.from(user);
      }));
  }

  get currentUser(): User | null {
    const currentUserFromLocalStorage = this.localStorageService.getItem<string>('currentUser');
    return this.currentUserSubject.value ?? (currentUserFromLocalStorage ? JSON.parse(currentUserFromLocalStorage) : null);
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
    if (this.selectedUsername) {
      return this.findUser(this.selectedUsername);
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
