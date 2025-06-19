import { Component } from '@angular/core';
import { MatCardModule } from '@angular/material/card';
import { MatDividerModule } from '@angular/material/divider';

@Component({
  selector: 'app-student',
  imports: [MatCardModule,
            MatDividerModule
  ],
  templateUrl: './student.html',
  styleUrl: './student.scss'
})
export class Student {

}
