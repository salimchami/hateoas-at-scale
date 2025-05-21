import {Component, OnInit} from '@angular/core';
import {MatIconModule} from '@angular/material/icon';
import {AuthService} from '../../shared/authentication';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [
    MatIconModule
  ],
  template: `
    <div class="home-content">
      <h2>Welcome to HATEOAS Marketplace</h2>
      <p>
        <mat-icon class="arrow-left" aria-label="">west</mat-icon>
        Please select a link on the sidebar
        <mat-icon aria-label="">menu</mat-icon>
        to get started.
      </p>
    </div>
  `,
  styles: `
    .home-content {
      padding: 20px;
      text-align: center;
    }

    .arrow-left {
      margin-right: 30px;
      padding-top: 10px;
    }

    .connection-button {
      margin-left: 10px;
      margin-right: 10px;
    }
  `
})
export class HomeComponent implements OnInit {
  isLoggedIn: boolean = false;

  constructor(
    private readonly authService: AuthService,
  ) {
  }

  ngOnInit(): void {
    this.isLoggedIn = this.authService.isValidSession();
    if (!this.isLoggedIn) {
      this.authService.login();
    }
  }
}
