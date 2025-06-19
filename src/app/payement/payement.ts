import { Component } from '@angular/core';
import { MatCardModule } from '@angular/material/card';
import { MatDividerModule } from '@angular/material/divider';

@Component({
  selector: 'app-payement',
  imports: [MatCardModule,
            MatDividerModule
  ],
  templateUrl: './payement.html',
  styleUrl: './payement.scss'
})
export class Payement {

}
