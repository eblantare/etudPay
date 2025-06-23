import { provideCloudinaryLoader } from "@angular/common";
import { Injectable } from "@angular/core";
import { ActivatedRouteSnapshot, CanActivate, GuardResult, MaybeAsync, RouterStateSnapshot } from "@angular/router";
import { Auth } from "../services/auth";
import { Router } from "@angular/router";

@Injectable({providedIn:'root'})
export class AutorizationGuards implements CanActivate{

    constructor(private authService:Auth,private router:Router){

    }

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): 
    MaybeAsync<GuardResult> {
        if(this.authService.isAuthenticated){
            const requiredRoles = route.data['roles'];
            const userRoles:string[] = this.authService.roles;
            //si aucun role requis, autoriser l'accès
            if(!requiredRoles){
                return true;
            }
            for(let role of userRoles){
                if(requiredRoles.includes(role))
                    {
                        return true;
                    }
            }
            return false;
        }
        else{
            this.router.navigateByUrl('/login')
            return false;
        }
        
    }

}