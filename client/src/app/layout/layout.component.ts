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
import {routes} from '../app.routes';
import {User} from '../user/user';
import {UserService} from '../user/user-service';

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
    MatIconModule
  ],
  templateUrl: './layout.component.html',
  styleUrl: './layout.component.scss'
})
export class LayoutComponent implements OnInit {
  private allRoutes = routes
    .find(r => r.path === '' && r.children)?.children || [];

  // Create a BehaviorSubject to hold the visible routes
  private visibleRoutesSubject = new BehaviorSubject<Route[]>([]);
  visibleRoutes$ = this.visibleRoutesSubject.asObservable();

  isHandset$!: Observable<boolean>;

  currentUser: User = {} as User;

  constructor(
    private readonly userService: UserService,
    private readonly breakpointObserver: BreakpointObserver,
  ) {
  }

  ngOnInit(): void {
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
    this.userService.loadCurrentUser(username).subscribe(user => {
      this.userService.setCurrentUser(user);
    });
  }

  /**
   * Update the list of visible routes based on current user
   */
  private updateVisibleRoutes() {
    const visibleRoutes = this.allRoutes.filter(route => this.hasAccess(route));
    this.visibleRoutesSubject.next(visibleRoutes);
  }

  hasAccess(route: Route): boolean {
    const routeLinkName = route.data?.['linkName'] || route.path;
    return route.path === 'home'
      ||
      (this.currentUser._links
        && Object.keys(this.currentUser._links).some(linkKey => linkKey === routeLinkName));
  }
}
