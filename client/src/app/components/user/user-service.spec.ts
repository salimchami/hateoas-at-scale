import {UserService} from './user-service';
import {TestBed} from '@angular/core/testing';
import {localStorageMock} from '../../../tests/fake-localstorage';
import {HttpTestingController, provideHttpClientTesting} from '@angular/common/http/testing';
import {provideHttpClient} from '@angular/common/http';
import {firstValueFrom} from 'rxjs';
import {User} from './user';
import {environment} from '../../../environments/environment';
import {LocalStorageService} from '../../shared/local-storage.service';

describe('UserService', () => {
  let sut: UserService;
  let httpTestingController: HttpTestingController;
  let localStorageService: LocalStorageService;
  const userHttpResponse = {
    _links: {
      "self": {
        href: 'http://localhost:8080/api/users/ada.lovelace'
      },
    },
    username: 'ada.lovelace',
    firstname: 'Ada',
    lastname: 'Lovelace',
  };
  const currentUser = User.from(userHttpResponse);
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [
        UserService,
        provideHttpClient(),
        provideHttpClientTesting(),
        {provide: LocalStorageService, useValue: localStorageMock},
      ],
    })
    sut = TestBed.inject(UserService);
    httpTestingController = TestBed.inject(HttpTestingController);
    localStorageService = TestBed.inject(LocalStorageService);
  });
  afterEach(() => {
    httpTestingController.verify();
  });

  it('should ', () => {
    expect(sut.usernames).toEqual([
      'ada.lovelace',
      'alan.turing',
      'charles.darwin',
      'karen.spence',
      'martin.curie',
      'richard.stallman',
      'samuel.jackson',
    ]);
  });

  it('should load current user', async () => {
    const userPromise = firstValueFrom(sut.findCurrentUser('ada.lovelace'));
    const expectedUrl = `${environment.appApiHost}${environment.startupEndpoint}/ada.lovelace`;
    const req = httpTestingController.expectOne(expectedUrl);
    expect(req.request.method).toBe('GET');
    req.flush(userHttpResponse);
    const user = await userPromise;
    expect(user).toEqual(currentUser);
    expect(localStorageService.getCurrentUser()).toEqual(
      JSON.stringify(userHttpResponse)
    );
  });
});
