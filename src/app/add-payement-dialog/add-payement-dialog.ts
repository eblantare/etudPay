import { ChangeDetectorRef,AfterViewInit, Component } from '@angular/core';
import { MatDialogModule, MatDialogRef} from '@angular/material/dialog';
import { FormBuilder,FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { CommonModule } from '@angular/common';
import { MatDatepickerModule, MatDateRangeInput } from '@angular/material/datepicker';
import { MatNativeDateModule, NativeDateAdapter } from '@angular/material/core';
import { MAT_DATE_LOCALE,DateAdapter } from '@angular/material/core';
import { MatSelectModule} from '@angular/material/select'
import { MatIconModule } from '@angular/material/icon';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Payement } from '../payement/payement.modele';
import { AddService }from '../app/add-payement-dialog/add-service'
import { error } from 'console';



@Component({
  standalone:true,
  selector: 'app-add-payement-dialog',
  imports: [MatFormFieldModule,
            ReactiveFormsModule,
            MatInputModule,
            MatButtonModule,
            MatDialogModule,
            CommonModule,
            MatDatepickerModule,
            MatNativeDateModule,
            MatSelectModule,
            MatIconModule,
          
  ],

  providers:[MatNativeDateModule,{provide:DateAdapter,useClass:NativeDateAdapter},{provide:MAT_DATE_LOCALE, useValue:'fr-FR' }],
  templateUrl: './add-payement-dialog.html',
  styleUrls: ['./add-payement-dialog.scss']
})
export class AddPayementDialogComponent {
  payementForm !: FormGroup;
  selectedFile?:File;
typeOptions:string[] = []; // exemple
statutOptions:string[] = [];
studentNames:{firstname:string,lastname:string}[]=[];
 dialogTitle = '';

  constructor(
    private fb:FormBuilder,
    public dialogRef: MatDialogRef<AddPayementDialogComponent>,
    public http:HttpClient,
       private cdRef:ChangeDetectorRef,private addService:AddService)
    {
      this.dialogTitle = 'Ajouter un paiement';
      this.payementForm = this.fb.group({
        date:[null,Validators.required],
        amount:['',Validators.required],
        type:['',Validators.required],
        statut:['',Validators.required],
        student:[null,Validators.required],
        firstname:['',Validators.required],
        lastname:['',Validators.required],

        file:[null,Validators.required]
      });

  }
  // Meilleur sans la methode add
    // onSubmit(){
    //   if(this.payementForm.valid){
    //     this.dialogRef.close(this.payementForm.valid)
    //   }
    // }

    onSubmit(){
      if(!this.selectedFile){alert('Veuillez sélectionner un fichier.');
        return;
      }
      if(this.payementForm.valid){
        const formData = new FormData();
        const rawDate:Date = this.payementForm.value.date;
        const formatDate = rawDate.toISOString().split('T')[0];
        formData.append('file',this.selectedFile);
        const{date,amount,type,statut,firstname,lastname}=this.payementForm.value;
        formData.append('date',formatDate);
        formData.append('amount',amount);
        formData.append('type',type);
        formData.append('statut',statut);
        formData.append('firstname',firstname);
        formData.append('lastname',lastname);

        this.addService.uploadPay(formData)
          .subscribe({
            next:(dt)=>{alert('Payement enregistré avec succès');
              console.log("************************************",dt);
              this.dialogRef.close(dt);//ferme le dialogue avec resultat
            },
            error: (err)=>{console.error(err);
              alert('Erreur lors de l\'enregistrement du payement');
            }
          });
      }else{alert('Veuillez remplir tous les champs');}
    }

    onCancel(){
      this.dialogRef.close();
    }
    onClear(){
      this.payementForm.reset();//Réinitialise les champs liés au formulaire
      this.selectedFile=undefined;//vide le fichier sélectionné
      
      // Optionnel : vide aussi le champ fichier visuellement si tu utilises un élément HTML custom
      const fileInput = document.querySelector('fileInput[type="file"]') as HTMLInputElement;
      if(fileInput){fileInput.value='';//vide le champ fichier dans le DOM
        }
        // Forcer détection des changements (pour mise à jour du nom affiché du fichier)
        this.cdRef.detectChanges();
    }

    ngAfterViewInit(){
      // setTimeout(()=>{
         this.cdRef.detectChanges();
      // });
     
    }
      
    onFileChange(event:Event):void{
      const fileInput = event.target as HTMLInputElement;
      if(fileInput.files && fileInput.files.length>0){
          this.selectedFile=fileInput.files[0];
      this.payementForm.patchValue({file:this.selectedFile});
      }
    }

    onStudentSelect(event:any){
        const student = event.value;
        this.payementForm.patchValue({
          firstname:student.firstname,
          lastname:student.lastname
        });
    }
    //   onFileChange(event:Event):void{
    //   const fileInput = event.target as HTMLInputElement;
    //   if(fileInput.files && fileInput.files.length>0){
    //       const file=fileInput.files[0];
    //       const formData = new FormData();
    //       formData.append('file',file);
    //       this.http.post('http://localhost:8083/api/uploadFichier', formData)
    //                   .subscribe({
    //                       next:(res)=>{
    //                         console.log('Upload réussi ', res);
    //                       },
    //                       error:(err)=>{console.error('upload error ',err);

    //                       }
    //                   });
    //   console.log('📎 Fichier sélectionné :', this.selectedFile.name);
    //   // this.payementForm.patchValue({file:this.selectedFile});
    //   }
    // }
    
  ngOnInit(){
    //Charger les types etles status
     this.http.get<{types: string[];statuts:string[]}>('http://localhost:8083/api/payement-options')
    .subscribe({
      next: res =>{
        // setTimeout(()=>{
              this.typeOptions = res.types;
              this.statutOptions = res.statuts;

        // });
   
       //Forcer la mise à jour de la vue après l'async
       this.cdRef.markForCheck();
    },
          error: err => { 
        console.error("Erreur lors du chargement des options de payement",err);
        alert('Impossible de charger les types et les status de paiement. Vérifier la connexion au serveur!');
        //ou désactiver le formulaire
        this.payementForm.disable();
      }
   });
   //Charger les noms des étudiants
   this.http.get<{firstname:string,lastname:string}[]>('http://localhost:8083/api/names')
            .subscribe({
             next: (res)=>{
              this.studentNames = res;
              this.cdRef.markForCheck();
             } ,
             error:(err)=>{console.error("Erreur lors du chargement du nom des étudiants",err);
              alert('Echec du chargement des noms');
             }
            });
     this.cdRef.detectChanges();

  }
      
}
