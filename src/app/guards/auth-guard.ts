// Pour la gestion de la sécurité des users
import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, CanActivateFn, GuardResult, MaybeAsync, RouterStateSnapshot } from '@angular/router';
import { Auth } from '../services/auth';
import { Router } from '@angular/router';
@Injectable({providedIn:'root'}) // pour signifier que c'est un service
export class AuthGuard implements CanActivate{

  constructor(private authService:Auth,private router:Router){

  }
  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot):
   MaybeAsync<GuardResult> {
    if(this.authService.isAuthenticated){return true;}
    else{
         this.router.navigateByUrl('/login')
         return false;
    }
    
  }
};
