import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";

@Injectable({providedIn : 'root'})
export class AddService {
    private apiUrladd = 'http://localhost:8083/api/uploadFichier'// adapte selon ton backend
     constructor(private http:HttpClient){}

     uploadPay(formData:FormData
     ):Observable<any>{
        console.log("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
         return this.http.post(this.apiUrladd,formData); // On peut utiliser <payement> si on reçoit un objet

     }
}
