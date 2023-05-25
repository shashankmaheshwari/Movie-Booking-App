import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-ticket-booking',
  templateUrl: './ticket-booking.component.html',
  styleUrls: ['./ticket-booking.component.css']
})
export class TicketBookingComponent implements OnInit {
  movieName:any;
  theatreName:any;
  customerId:any;
  constructor(    private _route:ActivatedRoute,
    ) { }

  ngOnInit(): void {
    this._route.params.subscribe((params)=>{
      this.movieName=params['movieName'];
      this.theatreName=params['theatreName'];
      this.customerId=params['customerId'];
      console.log("movieName",this.movieName);
      console.log("theatreName",this.theatreName);
      console.log("customerId",this.customerId);

    })

  }

}
