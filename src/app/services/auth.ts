import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
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
export class Auth {
 
  private userCredentials = new Map<string,string>([
    ['admin', '1234'],
    ['user', 'userPass'],
    ['other', 'pass123']

  ]);
  private userRoles = new Map<string,string[]>([
    ['admin',['admin','superUser']],
    ['user',['user']],
    ['other',['other']]

  ]);

  // private validUsername = 'admin';
  // private validPassword = '1234';
  public username:String='';
  public isAuthenticated:boolean=false;
  public roles:string[] = [];

   constructor(private router:Router){

  }
  //  Utiliser la Map userRoles pour attribuer dynamiquement les rôles.
  // Ajouter un vrai système d’utilisateurs (avec Map<username, password>)
  

  login(username: string, password: string): boolean {
    console.log('Service login called with:', username, password);
    const validPassword = this.userCredentials.get(username);

    if (validPassword && password === password) {
      this.username=username;
      this.isAuthenticated=true;
      this.roles = this.userRoles.get(username) || [];
      return true;
    }
    return false;
  }
  logout():void{
    this.isAuthenticated = false;
    this.roles = [];
    this.username = '';
    this.router.navigateByUrl('/login');
  }
  //vérifions si l'utilisateur a un rôle spécifique
  hasRole(roles:string):boolean{
    return this.roles.includes(roles);
  }
}
