import { Component, OnInit } from '@angular/core';
import { tick } from '@angular/core/testing';
import { ActivatedRoute, Router } from '@angular/router';
import { Ticket } from 'src/app/modals/Ticket';
import { MovieService } from 'src/app/services/movie.service';

@Component({
  selector: 'app-cost',
  templateUrl: './cost.component.html',
  styleUrls: ['./cost.component.css']
})
export class CostComponent implements OnInit {
   ticketId:any;
   ticket: Ticket = new Ticket([], '', '', 0, 0,0);
  constructor(private _route:ActivatedRoute,private _movie:MovieService,private router:Router) { }

  ngOnInit(): void {
    this._route.params.subscribe((params)=>{
      this.ticketId=params['ticketId'];
      console.log(this.ticketId);
    
    })
    this.getTicketBasisOnTicketId();
  
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
    this.router.navigate(['/customer']);

  }

}
