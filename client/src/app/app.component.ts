import {Component} from '@angular/core';
import {RouterOutlet} from '@angular/router';
import {HttpService} from './shared/http.service';
import {UserService} from './components/user/user-service';

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
