import {Component, OnInit} from '@angular/core';
import {UserService} from '../user/user-service';
import {User} from '../user/user';
import {NgIf} from '@angular/common';
import {MatIconModule} from '@angular/material/icon';
import {MatButton} from '@angular/material/button';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [
    NgIf,
    MatIconModule,
    MatButton
  ],
  template: `
    <div class="home-content">
      <h2>Welcome to HATEOAS Marketplace</h2>
      <p *ngIf="!currentUser.username">Please click on the connection button <button class="connection-button" mat-stroked-button disabled>Connection</button> to get started. <mat-icon class="arrow-left" aria-label="">north</mat-icon>
      </p>
      <p *ngIf="currentUser.username"><mat-icon class="arrow-left" aria-label="">west</mat-icon> Please select a link on the sidebar <mat-icon aria-label="">menu</mat-icon> to get started.</p>
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
  currentUser: User = {} as User;

  constructor(private readonly userService: UserService) {
  }

  ngOnInit(): void {
    this.userService.currentUser$.subscribe(user => this.currentUser = user);
    this.userService.refreshCurrentUser().subscribe(user => this.currentUser = user);
  }
}
