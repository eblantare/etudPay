import { Component,Inject } from "@angular/core";
import { MatDialogModule, MatDialogRef, MAT_DIALOG_DATA } from "@angular/material/dialog";
import { PayementComponent } from './payementComponent';
import { MatButtonModule } from "@angular/material/button";


@Component({
  standalone: true,
  selector: 'app-confirm-dialog',
  template: `
    <h2 mat-dialog-title>Confirmation</h2>
    <mat-dialog-content>Êtes-vous sûr de supprimer ce paiement ?</mat-dialog-content>
    <mat-dialog-actions align="end">
      <button mat-button (click)="onNo()">Non</button>
      <button mat-button color="warn" (click)="onYes()">Oui</button>
    </mat-dialog-actions>`,

  imports: [MatDialogModule, MatButtonModule],
})
export class ConfirmDialogComponent  {

    constructor(public dialogRef:MatDialogRef<ConfirmDialogComponent>){}
    
    onYes():void{
            this.dialogRef.close(true);
    }
    onNo(){
        this.dialogRef.close(false);
    }
    }
