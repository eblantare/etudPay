import { CommonModule } from '@angular/common';// Gérer les json
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatDividerModule } from '@angular/material/divider';
import { MatFormFieldModule} from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { Auth } from '../services/auth';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',                  
  imports: [MatCardModule,
            MatDividerModule,
            MatFormFieldModule,
            MatInputModule,
            MatButtonModule,
            ReactiveFormsModule,
            CommonModule
  ],
  templateUrl: './login.html',
  styleUrls: ['./login.scss']
})
export class Login implements OnInit{

  public Loginform!: FormGroup;
   constructor(private fb:FormBuilder,private authService: Auth,private router:Router){

   }
  ngOnInit():void{
    this.Loginform = this.fb.group({
      username: this.fb.control(''),
      password: this.fb.control('')
    }

    )

  }
  //Récupération de username et password
  login():void{
    console.log('Login clicked');
    let username = this.Loginform.value.username;
    let password = this.Loginform.value.password;
    console.log('username:', username, 'password:', password);
    let authen:boolean=this.authService.login(username,password);
    if(authen==true){
      this.router.navigateByUrl("/home");
      console.log('Authentication successful, navigating...');
    }else{
       console.log('Authentication failed');
//     }
//     if (username === 'admin' && password === '1234') {
//   console.log('Login OK');
//   this.router.navigate(['/home']); // ou autre route valide
// } else {
//   console.log('Login FAIL');
}
  }

}
