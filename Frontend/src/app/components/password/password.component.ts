import { Component, OnInit } from '@angular/core';
import { CustomerService } from 'src/app/services/customer.service';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-password',
  templateUrl: './password.component.html',
  styleUrls: ['./password.component.css']
})
export class PasswordComponent implements OnInit {

  constructor(private login:LoginService,private customer:CustomerService) { }
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
      },
      (error:any)=>{
        console.log(error);
        console.log(" Error in Password change");
      }
    )

  }
  



}
