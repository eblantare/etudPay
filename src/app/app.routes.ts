import { Routes } from '@angular/router';
import { Home } from './home/home';
import { LoadPayement } from './load-payement/load-payement';
import { LoadStudent } from './load-student/load-student';
import { Login } from './login/login';
import { Profile } from './profile/profile';
import { Dashboard } from './dashboard/dashboard';
import { Student } from './student/student';
import { Payement } from './payement/payement';
import { AdminTemplate } from './admin-template/admin-template';
import { AuthGuard } from './guards/auth-guard';


export const routes: Routes = [
    {path : "", component : AdminTemplate,
        
        children : [
                // {path : "", component : Login},
                 {path : "admin", component : AdminTemplate,canActivate:[AuthGuard],},   
                // {path : "login", component : Login},
                {path : "home", component : Home},
                {path : "profile",component : Profile},
                {path : "load-student", component : LoadStudent},
                {path : "load-payement", component : LoadPayement},
                {path : "dashboard", component : Dashboard},
                {path : "student", component : Student},
                {path : "payement", component : Payement},
                { path: '', redirectTo: 'login', pathMatch: 'full' },
        ]
    },
                 { path: 'login', component: Login }
];
