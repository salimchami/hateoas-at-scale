import {Component} from '@angular/core';
import {LayoutComponent} from './layout/layout.component';
import {RouterOutlet} from '@angular/router';

@Component({
  selector: 'app-root',
  imports: [
    LayoutComponent,
    RouterOutlet
  ],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {

  title = 'client';
}
