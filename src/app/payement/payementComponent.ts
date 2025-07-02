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
import { MatIconModule } from '@angular/material/icon';
import { MatTooltipModule }from '@angular/material/tooltip'
import { MatButtonModule } from '@angular/material/button';
import { AddPayementDialogComponent } from '../add-payement-dialog/add-payement-dialog';
import { MatDialog } from '@angular/material/dialog';
import { Auth } from '../services/auth';
import { CdkTableModule } from '@angular/cdk/table';
import { ConfirmDialogComponent } from './confirm-dialog.component';
import { FormsModule } from '@angular/forms';



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
            MatInputModule,
            MatIconModule,
            MatTooltipModule,
            MatButtonModule,
            CdkTableModule,
            FormsModule
            // ConfirmDialogComponent
  ],
 
  
})

export class PayementComponent implements OnInit{
  // private http = inject(HttpClient);
  exemple:string='ex.Mia';
 public payements:Payement[] =[];
 public dataSource:any;
 public displayedColumns:string[] = ['id','date','amount','type','statut','file','firstname','lastname','actions']
 @ViewChild(MatPaginator) paginator! : MatPaginator;
 @ViewChild(MatSort) sort! : MatSort;
  constructor(private cdr:ChangeDetectorRef, private http:HttpClient,
    private dialog:MatDialog,public authService:Auth){}

  //Définition des couleurs
  my_color1 = 'green';
  my_color2 = 'red';
  my_color3 = '#9c640c';


  ngOnInit(){
    //Envoie d'une requête vers le bakend
    this.http.get<Payement[]>("http://localhost:8083/api/allPayements")
    .subscribe({
      next: data =>{
        // setTimeout(()=> {
          this.payements=data;
          this.dataSource= new MatTableDataSource(this.payements);
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

  onDelete(id: number): void {
        const dialogRef = this.dialog.open(ConfirmDialogComponent,{
          width:'350px', //taille du modal
          disableClose:true, // empêche de fermer quand on cloque dehors
          autoFocus:true
        });
        dialogRef.afterClosed().subscribe(result=>{
          if(result){
            // this.refreshList();
           this.http.delete(`http://localhost:8083/api/payement/${id}`)
            .subscribe({
               next: () => {
                // alert('Paiement supprimé avec succès');
                this.refreshList(); // Recharge la liste si nécessaire
                 },
                 error: err => {
                   console.error('Erreur lors de la suppression', err);
                   alert('Erreur lors de la suppression');
               }
        });
      }
      });
    
  }

  refreshList() {
    // recharge les paiements depuis l'API
    this.http.get<Payement[]>('http://localhost:8083/api/allPayements')
    .subscribe({
          next: (ts)=>{
            //suppression depuis le backend
            this.dataSource = new MatTableDataSource(ts);
            this.dataSource.paginator = this.paginator;
            this.dataSource.sort = this.sort;
            this.cdr.detectChanges();
            //suppression depuis le frontend
            // this.dataSource.ts=this.dataSource.ts.filter((p:any)=>p.id!==id);
          },
          error:err =>{
            console.log('Erreur lors du rechargement des payements ',err);
          }
    });
  } 

  //Methode de filtrage
  applyFilter(event : Event){
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
    if(this.dataSource.paginator){
      this.dataSource.paginator.firstPage();
    }

  }
  trackById(index: number, item: any):any{
    return item.id;

  }
  //Fonction du modal
  openAddDialog():void{
    (document.activeElement as HTMLElement)?.blur();
    const dialogRef=this.dialog.open(AddPayementDialogComponent,{
      width:'700px',//Largeur personnalisée
      height:'auto',//Hauteur ajuster automatiquement
      maxHeight:'90vh', //Empêche de dépasser l'écran
      autoFocus:true,
      restoreFocus:true,
    });
    dialogRef.afterClosed().subscribe(result =>{
      if(result){
        // Ici, tu peux envoyer `result` au backend ou l’ajouter au tableau 
        console.log('Données,reçues ',result);
        this.dataSource.data=[...this.dataSource.data, result];
      }
    });
  }


}

