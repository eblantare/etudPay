import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { AuthGuard } from './guards/auth-guard';
// import { AdminTemplate } from "./admin-template/admin-template";



@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet
            
          ],
  providers:[
    AuthGuard
  ],
  
  templateUrl: './app.html',
  styleUrl: './app.scss'
})
export class App {
  protected title = 'frontend-angular';
}
