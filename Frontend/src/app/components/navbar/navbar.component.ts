import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  constructor(public  login:LoginService,private router:Router) { }

  ngOnInit(): void {
  }
  public isAdmin(){
    if(this.login.isLoggedIn()&&this.login.getRole()=="ADMIN") return true;
    return false;
  }
  public isUser(){
    if(this.login.isLoggedIn()&&this.login.getRole()=="USER") return true;
    return false;
  }
 public logout(){
   this.login.logout();
   this.router.navigate(['/']);
  // window.location.reload();
  }

}
