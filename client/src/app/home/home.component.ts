import {Component, OnInit} from '@angular/core';
import {UserService} from '../user/user-service';
import {User} from '../user/user';
import {NgIf} from '@angular/common';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [
    NgIf
  ],
  template: `
    <div class="home-content">
      <h2>Welcome to HATEOAS Marketplace</h2>
      <p *ngIf="!currentUser.username">Please click on the connection button to get started.</p>
      <p *ngIf="currentUser.username">Please select a link on the sidebar to get started.</p>
    </div>
  `,
  styles: `
    .home-content {
      padding: 20px;
      text-align: center;
    }
  `
})
export class HomeComponent implements OnInit{
  currentUser: User = {} as User;
  constructor(private readonly userService: UserService) {
  }

  ngOnInit(): void {
        this.currentUser = this.userService.currentUser;
    }
}
