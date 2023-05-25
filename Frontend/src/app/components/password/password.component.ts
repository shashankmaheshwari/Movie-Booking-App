import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CustomerService } from 'src/app/services/customer.service';
import { LoginService } from 'src/app/services/login.service';
import Swal from 'sweetalert2';
@Component({
  selector: 'app-password',
  templateUrl: './password.component.html',
  styleUrls: ['./password.component.css']
})
export class PasswordComponent implements OnInit {

  constructor(private login:LoginService,private customer:CustomerService,private router:Router) { }
  public password:any;
  private userName:any;
  private user:any;
  ngOnInit(): void {
  
  }
  public changePassword(){
    this.user=this.login.getCustomer();
    // this.password=this.user.password;
    this.userName=this.user.userName;
    console.log(this.userName);
    console.log(this.password);
    this.customer.changePassword(this.userName,this.password).subscribe(
      (data:any)=>{
        console.log("Password change");
        Swal.fire('Successfully  Password!!',' Now you need to login again','success').then((e)=>{
          console.log(data);
          this.router.navigate(['/login']);
        })
      },
      (error:any)=>{
        console.log(error);
        console.log(" Error in Password change");
      }
    )

  }
  



}
