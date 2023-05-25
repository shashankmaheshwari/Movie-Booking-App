import { Component, OnInit } from '@angular/core';
import { Seat } from 'src/app/modals/Seat';

import { Ticket } from 'src/app/modals/Ticket';
import { MovieService } from 'src/app/services/movie.service';


@Component({
  selector: 'app-view-tickets',
  templateUrl: './view-tickets.component.html',
  styleUrls: ['./view-tickets.component.css']
})
export class ViewTicketsComponent implements OnInit {
   
  ticket:any=[];
  img:string="";

  seatList:Seat[]=[];
   constructor(private _movie:MovieService) { 
   
    //  this.ticket= new Ticket(this.seatList,"","",0,0);
   }

  ngOnInit(): void {
     this.getTicketsAdmin();
  }
  public getTicketsAdmin(){
    this._movie.getAllTicketsAdmin().subscribe(
      (res)=>{
        this.ticket=res;
        console.log(res);
        console.log("Tickets ....");
        console.log(this.ticket);
      },
      (error)=>{
        console.log(error);
      }
    )
  }
  
}
