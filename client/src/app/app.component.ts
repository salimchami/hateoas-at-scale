import {Component} from '@angular/core';
import {LayoutComponent} from './layout/layout.component';
import {RouterOutlet} from '@angular/router';
import {UserService} from './user/user-service';
import {HttpService} from '../shared/http.service';

@Component({
  selector: 'app-root',
  imports: [
    LayoutComponent,
    RouterOutlet,
  ],
  standalone: true,
  providers: [HttpService, UserService],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
}
