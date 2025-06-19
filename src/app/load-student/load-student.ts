import { Component } from '@angular/core';
import { MatCardModule } from '@angular/material/card';
import { MatDividerModule } from '@angular/material/divider';

@Component({
  selector: 'app-load-student',
  imports: [MatCardModule,
            MatDividerModule
  ],
  templateUrl: './load-student.html',
  styleUrl: './load-student.scss'
})
export class LoadStudent {

}
