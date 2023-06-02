import { Component, OnInit } from '@angular/core';
import { LoginService } from 'src/app/services/login.service';
import { PasswordComponent } from '../password/password.component';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css'],
})
export class ProfileComponent implements OnInit {
  user: any;
  profilePic: string = '';
  constructor(private login: LoginService) {}

  ngOnInit(): void {
    this.user = this.login.getCustomer();
    this.profilePic = `https://api.dicebear.com/6.x/notionists/svg?seed=${this.user.firstName}+${this.user.lastName}`;
  }
  public isAdmin() {
    if (this.login.isLoggedIn() && this.login.getRole() == 'ADMIN') return true;
    return false;
  }
  public isUser() {
    if (this.login.isLoggedIn() && this.login.getRole() == 'USER') return true;
    return false;
  }
}
