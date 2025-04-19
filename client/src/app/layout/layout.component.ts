import {Component, inject} from '@angular/core';
import {RouterOutlet, RouterLink, RouterLinkActive, Router} from '@angular/router';
import {AsyncPipe, NgOptimizedImage} from '@angular/common';
import {BreakpointObserver, Breakpoints} from '@angular/cdk/layout';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatButtonModule} from '@angular/material/button';
import {MatSidenavModule} from '@angular/material/sidenav';
import {MatListModule} from '@angular/material/list';
import {MatIconModule} from '@angular/material/icon';
import {Observable} from 'rxjs';
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
export class LayoutComponent {
  private readonly breakpointObserver = inject(BreakpointObserver);

  // Extract child routes from the main routes configuration
  rootRoutes = routes
    .find(r => r.path === '' && r.children)?.children || [];

  isHandset$: Observable<boolean> = this.breakpointObserver.observe(Breakpoints.Handset)
    .pipe(
      map(result => result.matches),
      shareReplay()
    );
  currentUser: User = {} as User;

  constructor(private readonly userService: UserService, private readonly router: Router) {
  }

  connect() {
    this.userService.loadCurrentUser().subscribe(user => {
      this.userService.setCurrentUser(user);
      this.currentUser = user;
      this.router.navigate(['/products']).then();
    })
  }
}
