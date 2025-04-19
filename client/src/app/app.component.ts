import {Component} from '@angular/core';
import {UserService} from './user/user-service';
import {HttpService} from '../shared/http.service';
import {RouterOutlet} from '@angular/router';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet],
  standalone: true,
  providers: [HttpService, UserService],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
}
