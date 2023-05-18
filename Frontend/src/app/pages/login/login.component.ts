import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginData={
    userName:'',
    password:''
  }
  constructor(private _snackBar:MatSnackBar,private login:LoginService,private router:Router) { }

  ngOnInit(): void {
  }
  formSubmit(){
    console.log("Form Submit");
    // Request To Server For Generating Token
    this.login.generateToken(this.loginData).subscribe(
      (data:any)=>{
        console.log("success");
        console.log(data);
        //login.....
        //1) Save the token in local Storage
        this.login.loginUser(data.token);
        //2) Save the customer details in local Storage
        this.login.setCustomer(data.customer);
        // redirect.. ADMIN :admin-dashboard
        if(this.login.getRole()=="ADMIN"){
          //admin dashboard
          this.router.navigate(['/admin']);

        }
        //redirect...NORMAL :normal-dashboard
        else if(this.login.getRole()=="USER"){
           //normla user
           this.router.navigate(['/customer']);
           
        }else{
          this.login.logout();
        }

        

      },
      (error:any)=>{
        console.log("error");
        console.log(error);
        
          console.log("Error",error);
          this._snackBar.open ('Invalid Details !! Try Again',' Close ',{
            duration:3000,
          });
         
      }
    )
  }

}
