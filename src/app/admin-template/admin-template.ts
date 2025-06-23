import { Component, importProvidersFrom, ViewEncapsulation } from '@angular/core';
import { MatToolbarModule } from '@angular/material/toolbar';
import {MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatMenuModule } from '@angular/material/menu';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatListModule } from '@angular/material/list';
import { RouterOutlet,RouterModule,RouterLink,RouterLinkActive } from '@angular/router';
import { MatCardModule } from '@angular/material/card';
import { MatDividerModule } from '@angular/material/divider';
import { Auth } from '../services/auth';
import { CommonModule } from '@angular/common';
// import { HttpClientModule } from '@angular/common/http';
// import { payementComponent } from "../payement/payementComponent";
// import { RouterModule } from '@angular/router';



@Component({
  selector: 'app-admin-template',
  imports: [MatToolbarModule,
    MatButtonModule,
    MatIconModule,
    MatMenuModule,
    MatSidenavModule,
    MatListModule,
    RouterOutlet,
    MatCardModule,
    MatDividerModule,
    RouterModule,
    RouterLink,
    // RouterLinkActive,
    CommonModule,
    ],
  standalone: true,
  templateUrl: './admin-template.html',
  styleUrl: './admin-template.scss',
 
})
export class AdminTemplate {
  constructor(public authService:Auth){


  }
  logout(){
    this.authService.logout();
  }

}
