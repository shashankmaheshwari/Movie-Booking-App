import { Component, OnInit } from '@angular/core';
import { tick } from '@angular/core/testing';
import { ActivatedRoute, Router } from '@angular/router';
import { Ticket } from 'src/app/modals/Ticket';
import { LoginService } from 'src/app/services/login.service';
import { MovieService } from 'src/app/services/movie.service';

@Component({
  selector: 'app-cost',
  templateUrl: './cost.component.html',
  styleUrls: ['./cost.component.css']
})
export class CostComponent implements OnInit {
   ticketId:any;
   ticket: Ticket = new Ticket([], '', '', 0, 0,0);
  constructor(public  login:LoginService,private _route:ActivatedRoute,private _movie:MovieService,private router:Router) { }

  ngOnInit(): void {
    this._route.params.subscribe((params)=>{
      this.ticketId=params['ticketId'];
      console.log(this.ticketId);
    
    })
    this.getTicketBasisOnTicketId();
  
  }
  public isAdmin(){
    if(this.login.isLoggedIn()&&this.login.getRole()=="ADMIN") return true;
    return false;
  }
  public isUser(){
    if(this.login.isLoggedIn()&&this.login.getRole()=="USER") return true;
    return false;
  }
  printPage(){
    window.print();
  }
  public getTicketBasisOnTicketId(){
    this._movie.getTicketBasedOnTicketId(this.ticketId).subscribe(

      (res:any)=>{
        this.ticket=res;
        console.log(res);
      },
      (error)=>{
        console.log(error);
      }
    )
  }
  public homePage(){
    if(this.isUser())
    this.router.navigate(['/customer']);
    else if(this.isAdmin()){
      this.router.navigate(['/admin']);
    }

  }

}
