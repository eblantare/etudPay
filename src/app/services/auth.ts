import { Injectable } from '@angular/core';
import { HttpClient,HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';
// import { promises } from 'dns';
import { firstValueFrom } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
//Gestion depuis le backend-bonne methode
export class Auth {
  private authenticated = false;
  public username:string='';
  public roles: string[]=[];

  constructor(private http:HttpClient){

  }

  login(username:string,password:string):Promise<boolean>{
    // const headers= new HttpHeaders({
      // authorization: 'Basic ' + btoa(username + ':' + password)
    // });
    // return this.http.get<any>('api/admin/me',{headers}).toPromise()
    console.log('[AUTH SERVICE ] login()  called with ',username,password);
    return firstValueFrom (this.http.post<any>('api/public/login',{username, password}))
    .then(res =>{
                if(res === true){
                  this.authenticated = true;
                  this.username = username;
                  this.roles =['ADMIN'];
                  return true;
                }else{
                 this.authenticated = false; 
                 return false;
                }
                  
    })
    .catch(()=>{this.authenticated=false;
      return false;
    });
  }

  isAuthenticated(): boolean{
   return this.authenticated;
  }

  setAuthenticated(value:boolean){
    this.authenticated = value;
  }

  logout():void{
    this.setAuthenticated(false);
    this.username ='';
    this.roles= [];
  }

}
// export class Auth {

//   // solution frontend pour l'authentification
//   public users:any = {//any: pour pouvoir l'utiliser comme on veut
//          Admin: {password :"1234", roles:['ADMIN','STUDENT']},
//          user1: {password: "1234", roles:['STUDENT']}
//   }
//   constructor() { }

//   public login(username:string,password:string):boolean{
//     if(this.users[username] && this.users[username]['password']==password){
//       return true;
//     }else{
//       return false;
//     }


//   }
// }

//La méthode en dur
// export class Auth {
 
//   private userCredentials = new Map<string,string>([
//     ['admin', '1234'],
//     ['user', 'userPass'],
//     ['other', 'pass123']

//   ]);
//   private userRoles = new Map<string,string[]>([
//     ['admin',['ADMIN','SUPERUSER']],
//     ['user',['USER']],
//     ['other',['OTHER']]

//   ]);

//   // private validUsername = 'admin';
//   // private validPassword = '1234';
//   public username:String='';
//   public isAuthenticated:boolean=false;
//   public roles:string[] = [];

//    constructor(private router:Router){

//   }
//   //  Utiliser la Map userRoles pour attribuer dynamiquement les rôles.
//   // Ajouter un vrai système d’utilisateurs (avec Map<username, password>)
  

//   login(username: string, password: string): boolean {
//     console.log('Service login called with:', username, password);
//     const validPassword = this.userCredentials.get(username);

//     if (validPassword && password === validPassword) {
//       this.username=username;
//       this.isAuthenticated=true;
//       this.roles = this.userRoles.get(username) || [];
//       return true;
//     }
//     return false;
//   }
//   logout():void{
//     this.isAuthenticated = false;
//     this.roles = [];
//     this.username = '';
//     this.router.navigateByUrl('/login');
//   }
//   //vérifions si l'utilisateur a un rôle spécifique
//   hasRole(roles:string):boolean{
//     return this.roles.includes(roles);
//   }
// }
