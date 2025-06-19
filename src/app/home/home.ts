import { Component } from '@angular/core';
import { MatCardModule } from '@angular/material/card';
import { MatDividerModule } from '@angular/material/divider';

@Component({
  selector: 'app-home',
  imports: [MatCardModule,
             MatDividerModule
           ],
  templateUrl: './home.html',
  styleUrl: './home.scss'
})
export class Home {

}
