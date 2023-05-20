import { Component, OnInit } from '@angular/core';
import { LoginService } from 'src/app/services/login.service';
import { PasswordComponent } from '../password/password.component';


@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  
  user:any;
  constructor(private login:LoginService) { }

  ngOnInit(): void {
    this.user=this.login.getCustomer();
  
  }

}
