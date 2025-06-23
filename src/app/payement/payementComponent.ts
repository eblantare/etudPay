import { HttpClient} from '@angular/common/http';
import { Component, OnInit,ChangeDetectorRef,ViewChild } from '@angular/core';
import { MatCardModule } from '@angular/material/card';
import { MatDividerModule } from '@angular/material/divider';
// import { error } from 'console';
// import { errorMonitor } from 'events';
// import { url } from 'inspector';
import { Payement } from './payement.modele';
import { MatTableDataSource, MatTableModule } from '@angular/material/table'
import { MatFormField, MatFormFieldModule } from '@angular/material/form-field';
import { MatPaginator, MatPaginatorModule } from '@angular/material/paginator'
import { CommonModule } from '@angular/common';
import { bootstrapApplication } from '@angular/platform-browser';
import { MatSort, MatSortModule } from '@angular/material/sort';
import { MatInputModule } from '@angular/material/input';


@Component({
  standalone:true,
  selector: 'app-payement',
  templateUrl: './payement.component.html',
  styleUrls: ['./payement.component.scss'],
  imports: [MatCardModule,
            MatDividerModule, 
            MatTableModule, 
            MatFormFieldModule,
            MatPaginatorModule,
            CommonModule,
            MatSortModule,
            MatInputModule         
  ],
 
  
})

export class PayementComponent implements OnInit{
  // private http = inject(HttpClient);
 public payement:Payement[] =[];
 public dataSource:any;
 public displayedColumns:string[] = ['id','date','amount','type','statut','firstname','lastname']
 @ViewChild(MatPaginator) paginator! : MatPaginator;
 @ViewChild(MatSort) sort! : MatSort;
  constructor(private cdr:ChangeDetectorRef, private http:HttpClient){}



  ngOnInit(){
    //Envoie d'une requête vers le bakend
    this.http.get<Payement[]>("http://localhost:8083/api/allPayements")
    .subscribe({
      next: data =>{
        // setTimeout(()=> {
          this.payement=data;
          this.dataSource= new MatTableDataSource(this.payement);
          this.dataSource.paginator = this.paginator;
          this.dataSource.sort = this.sort;
          this.cdr.detectChanges();
        // });

    },
          error: err => { 
        console.error(err);
      }
   }); 

  }
  applyFilter(event : Event){
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
    if(this.dataSource.paginator){
      this.dataSource.paginator.firstPage();
    }

  }
  trackById(index: number, item: Payement){
    return item.id;

  }
}

