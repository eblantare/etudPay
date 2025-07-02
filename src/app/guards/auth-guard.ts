// Pour la gestion de la sécurité des users
import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, CanActivateFn, GuardResult, MaybeAsync, RouterStateSnapshot } from '@angular/router';
import { Auth } from '../services/auth';
import { Router,UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
@Injectable({providedIn:'root'}) // pour signifier que c'est un service
export class AuthGuard implements CanActivate{

  constructor(private authService:Auth,private router:Router){

  }
  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot):
   boolean | UrlTree {
    if(this.authService.isAuthenticated()){return true;}
    else{
        //  this.router.navigateByUrl('/login')
         return this.router.parseUrl('/login');
        //  return false;
    }
    
  }
};
