import {Component, OnInit} from '@angular/core';
import {MatIconModule} from '@angular/material/icon';
import {MatCardModule} from '@angular/material/card';
import {User} from '../user/user';
import {Users} from './users';
import {UserService} from '../user/user-service';
import {NgForOf, NgIf} from '@angular/common';

@Component({
  selector: 'app-users',
  imports: [MatIconModule, MatCardModule, NgForOf, NgIf],
  templateUrl: './users.component.html',
  styleUrl: './users.component.scss'
})
export class UsersComponent implements OnInit {
  currentUser: User | null = null;
  users: Users = {list: []} as unknown as Users;

  constructor(private readonly userService: UserService) {
  }

  ngOnInit(): void {
    this.userService.currentUser.subscribe(user => {
      this.currentUser = user;
      if (this.currentUser?.username) {
        this.loadUsers();
      }
    });
  }

  private loadUsers() {
    this.userService.findAll(this.currentUser?._links['allUsers'].href)
      .subscribe(users => {
        this.users = users;
      });
  }
}
