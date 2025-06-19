// Pour la gestion de la sécurité des users
import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, CanActivateFn, GuardResult, MaybeAsync, RouterStateSnapshot } from '@angular/router';
import { Auth } from '../services/auth';
@Injectable({providedIn:'root'}) // pour signifier que c'est un service
export class AuthGuard implements CanActivate{

  constructor(private authService:Auth){

  }
  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): MaybeAsync<GuardResult> {
    return this.authService.isAuthenticated;
  }
};
