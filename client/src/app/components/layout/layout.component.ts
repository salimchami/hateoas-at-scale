import {Component, OnInit} from '@angular/core';
import {Route, RouterLink, RouterLinkActive, RouterOutlet} from '@angular/router';
import {AsyncPipe, NgOptimizedImage} from '@angular/common';
import {BreakpointObserver, Breakpoints} from '@angular/cdk/layout';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatButtonModule} from '@angular/material/button';
import {MatSidenavModule} from '@angular/material/sidenav';
import {MatListModule} from '@angular/material/list';
import {MatIconModule} from '@angular/material/icon';
import {BehaviorSubject, Observable} from 'rxjs';
import {map, shareReplay} from 'rxjs/operators';
import {User} from '../user/user';
import {UserService} from '../user/user-service';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatMenuModule} from '@angular/material/menu';
import {routes} from '../../app.routes';

@Component({
  selector: 'app-layout',
  standalone: true,
  imports: [
    RouterOutlet,
    RouterLink,
    RouterLinkActive,
    AsyncPipe,
    NgOptimizedImage,
    MatToolbarModule,
    MatButtonModule,
    MatSidenavModule,
    MatListModule,
    MatIconModule,
    MatFormFieldModule,
    MatMenuModule
  ],
  templateUrl: './layout.component.html',
  styleUrl: './layout.component.scss'
})
export class LayoutComponent implements OnInit {
  private readonly allRoutes = routes
    .find(r => r.path === '' && r.children)?.children || [];

  private readonly visibleRoutesSubject = new BehaviorSubject<Route[]>([]);
  visibleRoutes$ = this.visibleRoutesSubject.asObservable();

  isHandset$!: Observable<boolean>;
  usernames: Array<string> = [];
  currentUser: User | null = null;

  constructor(
    private readonly userService: UserService,
    private readonly breakpointObserver: BreakpointObserver,
  ) {
  }

  ngOnInit(): void {
    this.usernames = this.userService.usernames;
    this.isHandset$ = this.breakpointObserver.observe(Breakpoints.Handset)
      .pipe(
        map(result => result.matches),
        shareReplay()
      );
    this.userService.currentUser$.subscribe(user => {
      this.currentUser = user;
      this.updateVisibleRoutes();
    });

    this.updateVisibleRoutes();
  }

  connect(username: string) {
    this.userService.findUser(username).subscribe();
  }

  private updateVisibleRoutes() {
    const visibleRoutes = this.allRoutes.filter(route => this.hasAccess(route));
    this.visibleRoutesSubject.next(visibleRoutes);
  }

  hasAccess(route: Route): boolean {
    const userLinks = this.userService.currentUser?._links;
    const routeIsHome = route.path === 'home';
    const routeLinkName = route.data?.['linkName'] ?? route.path;
    const userLinksContainsRoute = !!userLinks && Object.keys(userLinks).some(linkKey => linkKey === routeLinkName)
    return routeIsHome || userLinksContainsRoute;
  }


  logout() {
    this.userService.logout();
    this.currentUser = null;
  }
}
