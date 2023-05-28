// import { Component, OnInit } from '@angular/core';
// import { ActivatedRoute } from '@angular/router';

// import { SeatIn } from 'src/app/modals/SeatIn';


// import { Ticket } from 'src/app/modals/Ticket';


// import { MovieService } from 'src/app/services/movie.service';

// @Component({
//   selector: 'app-ticket-booking',
//   templateUrl: './ticket-booking.component.html',
//   styleUrls: ['./ticket-booking.component.css']
// })
// export class TicketBookingComponent implements OnInit {
//   movieName:any="";
//   theatreName:any="";
//   customerId:any=0;
//   movieId:any=0;
//   seatsInp:SeatIn[]=[];
//   seat: { seatNumber: number }[] = [];
//   // seat:Seat[]=[];

//   ticket: Ticket = new Ticket([], '', '', 0, 0);

//   constructor(private _route:ActivatedRoute,private _movie:MovieService
//     ) { }

//   ngOnInit(): void {
//     this._route.params.subscribe((params)=>{
//       this.movieName=params['movieName'];
//       this.theatreName=params['theatreName'];
//       this.customerId=params['customerId'];
//       this.movieId=params['movieId'];
//       console.log("movieName:",this.movieName);
//       console.log("theatreName:",this.theatreName);
//       console.log("customerId",this.customerId);
//       console.log("movieId:",this.movieId);
//       this.getAllSeatsWithMovieId();

//     })

//   }
 
//   // seats1: Seat[] = [
//   //   { seatId:1,seatNumber: 1, seatType: 'Regular', seatStatus:"Available", isSelected: false },
//   //   { seatId:2,seatNumber: 2, seatType: 'Regular', seatStatus:"Booked", isSelected: false },
//   //   { seatId:3,seatNumber: 3, seatType: 'Regular', seatStatus:"Available", isSelected: false },
//   //   { seatId:4,seatNumber: 4, seatType: 'Regular', seatStatus:"Available", isSelected: false },
//   //   { seatId:5,seatNumber: 5, seatType: 'Regular', seatStatus:"Available", isSelected: false },
//   //   { seatId:6,seatNumber: 6, seatType: 'Regular', seatStatus:"Booked", isSelected: false },
//   //   { seatId:7,seatNumber: 7, seatType: 'Regular', seatStatus:"Available", isSelected: false },
//   //   { seatId:8,seatNumber: 8, seatType: 'Regular', seatStatus:"Available", isSelected: false },
//   //   { seatId:9,seatNumber: 9, seatType: 'Regular', seatStatus:"Available", isSelected: false },
//   //   { seatId:10,seatNumber: 10, seatType: 'Regular', seatStatus:"Booked", isSelected: false },

//   //   // Add more seat objects as needed
//   // ];

//   toggleSeatSelection(seatInp: SeatIn): void {
//     if (seatInp.seatStatus=="Available") {
//       seatInp.isSelected = !seatInp.isSelected;
//       this.updateSelectedSeats();
//     }
//   }

//   updateSelectedSeats(): void {

//     // seat= seatInp.map(({ isSelected, ...seat }) => seat);
//     for(const seat1 of this.seatsInp){
//       if(seat1.isSelected){
//          const newSeatObject = { seatNumber: seat1.seatNumber };
//          this.seat.push(newSeatObject);
//       }
//     }

//     //  const seatsToSend = seatInp.map(seat1 => {
//     //   const { isSelected, ...seatData } = seat1;
//     //   return seatData;
//     // });

//     //  this.seat= Seat.map((seat: { [x: string]: any; isSelected: any; }) => {
//     //   const { isSelected, ...seatData } = seat;
//     //   return seatData;
//     // });
    
//     //this.ticket.seats = this.seats1.filter(seat => seat.isSelected);
//    // this.ticket.numberOfTicket = this.ticket.seat.length;
//     // Update totalCost calculation logic based on your requirements
//     //this.ticket.totalCost = this.ticket.numberOfTicket * 10; // Assuming ticket cost is $10 per seat
//   }

//   bookTicket(): void {
//     this.ticket.movieName=this.movieName;
//     this.ticket.theatreName=this.theatreName;
//     this.updateSelectedSeats();
//     this.ticket.seats=this.seat;
//     this.ticket.numberOfTicket=this.seat.length;
//     console.log(this.ticket.seats);
//     console.log('Ticket booked:', this.ticket);
//     this._movie.bookTicket(this.ticket).subscribe(
//         (res)=>{
//           console.log(res);
//         },
//         (error)=>{
//           console.log(error);
//         }

//     )
//     this.resetSeatSelection();
//   }

//   resetSeatSelection(): void {
//     this.seatsInp.forEach(seat => {
//       seat.isSelected = false;
//     });
//     this.updateSelectedSeats();
//   }
//   // get all seats for this particular movieId
//   public getAllSeatsWithMovieId(){
//     this. _movie.getAllSeatsWithMovieId(this.movieId).subscribe(
//       (res:any)=>{
//        this. seatsInp=res;
//         for (const seat of this.seatsInp) {
//           seat.isSelected = false; // Adding the 'isSelected' property with a default value of false
//         }
//         console.log("Changed seat list");
//         console.log(this.seatsInp);
//       },
//       (error)=>{
//         console.log(error);
//       }
//     )
//   }


// }
