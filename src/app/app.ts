import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { AuthGuard } from './guards/auth-guard';
import { AutorizationGuards } from './guards/autorizationGuards';
// import { AdminTemplate } from "./admin-template/admin-template";
// import { payementComponent } from './payement/payementComponent';



@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet,
            
          ],
  providers:[
    AuthGuard,AutorizationGuards,
  ],
  
  templateUrl: './app.html',
  styleUrl: './app.scss'
})
export class App {
  protected title = 'frontend-angular';
}
