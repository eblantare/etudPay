import { Component } from '@angular/core';
import { MatCardModule } from '@angular/material/card';
import { MatDividerModule } from '@angular/material/divider';

@Component({
  selector: 'app-profile',
  imports: [MatCardModule,
            MatDividerModule
  ],
  templateUrl: './profile.html',
  styleUrl: './profile.scss'
})
export class Profile {

}
