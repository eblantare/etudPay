import { Routes } from '@angular/router';
import { Home } from './home/home';
import { LoadPayement } from './load-payement/load-payement';
import { LoadStudent } from './load-student/load-student';
import { Login } from './login/login';
import { Profile } from './profile/profile';
import { Dashboard } from './dashboard/dashboard';
import { Student } from './student/student';
import { PayementComponent } from './payement/payementComponent';
import { AdminTemplate } from './admin-template/admin-template';
import { AuthGuard } from './guards/auth-guard';
import { AutorizationGuards } from './guards/autorizationGuards';


export const routes: Routes = [
    { path: 'login', component: Login }, // page publique
        {path : "", component : AdminTemplate,canActivate:[AuthGuard],
        
        children : [
                // {path : "", component : Login},
                //  {path : "admin", component : AdminTemplate,},   
                // {path : "login", component : Login},
                {path : "home", component : Home},
                {path : "profile",component : Profile},
                {path : "load-student", component : LoadStudent,
                    canActivate:[AutorizationGuards],data:{roles:['ADMIN']}
                },
                {path : "load-payement", component : LoadPayement,
                    canActivate:[AutorizationGuards],data:{roles:['ADMIN']}
                },
                     
                {path : "dashboard", component : Dashboard},
                {path : "student", component : Student},
                {path : "payement", component : PayementComponent},
                { path: '', redirectTo: 'login', pathMatch: 'full' },// par défaut vers home au sein de l'admin

        ]
    },
                 
                 { path: '**', redirectTo: 'login' }    // gestion des routes inconnues
];
