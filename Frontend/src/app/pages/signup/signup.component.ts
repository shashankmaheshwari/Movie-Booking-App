import { HttpResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { CustomerService } from 'src/app/services/customer.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import Swal from 'sweetalert2';
import { Router } from '@angular/router';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css'],
})
export class SignupComponent implements OnInit {
  constructor(private customerService:CustomerService,private _snackBar:MatSnackBar,private router:Router) {}
  public customer = {
    userName: '',
    firstName: '',
    lastName: '',
    email: '',
    password: '',
    confirmPassword: '',
    contactNumber: '',
  };
  hide=true;

  ngOnInit(): void {}
  formSubmit() {
    console.log(this.customer);
    // call registerCustomer function:CustomerService
    
    this.customerService.registerCustomer(this.customer).subscribe(
     (response: any)=>{
      Swal.fire('Successfully  User Registered !!','User Registered !!','success').then((e)=>{
        console.log(response);
        this.router.navigate(['/login']);
      })
        
          
      },
      (error:any)=>{
       this.showValuesInSnackbar(error.error);
        console.log(error);
        console.log("fail");
       
      }  
    );

  } 
  showValuesInSnackbar(jsonObject: string) {
    const parsedObject = JSON.parse(jsonObject);
    const keys = Object.keys(parsedObject);
  
    let index = 0;
    const delay = 2000; // Delay in milliseconds between each snackbar
  
    const displayNextSnackbar = () => {
      if (index < keys.length) {
        const key = keys[index];
        const displayMessage =  `${parsedObject[key]}`;
        this.openSnackBar(displayMessage);
        index++;
  
        setTimeout(displayNextSnackbar, delay);
      }
    };
  
    displayNextSnackbar();
  }
  
  openSnackBar(message: string): void {
    this._snackBar.open(message, 'Close', {
      duration: 3000,
      verticalPosition: 'top',
      horizontalPosition: 'right'
    });
  }
  public alreadyLogged(){
    this.router.navigate(['/login']);
  }

  
  
  
  
}
