import { provideCloudinaryLoader } from "@angular/common";
import { Injectable } from "@angular/core";
import { ActivatedRouteSnapshot, CanActivate,Router, RouterStateSnapshot } from "@angular/router";
import { Auth } from "../services/auth";

@Injectable({providedIn:'root'})
export class AutorizationGuards implements CanActivate{

    constructor(private authService:Auth,private router:Router){

    }

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): 
    boolean {
        if(!this.authService.isAuthenticated){
            this.router.navigateByUrl('/login');
            return false;
        }
            const requiredRoles = route.data['roles'];
            const userRoles:string[] = this.authService.roles;
            //si aucun role requis, autoriser l'accès
            if(!requiredRoles || requiredRoles.length == 0){
                return true;
            }
             // Vérifier si au moins un rôle est présent
             return userRoles.some(role => requiredRoles.includes(role));
            // for(let role of userRoles){
            //     if(requiredRoles.includes(role))
            //         {
            //             return true;
            //         }
            // }
            // return false;
    }

}