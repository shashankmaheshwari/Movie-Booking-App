import { Seat } from "./Seat";


export class Ticket{

    seats:Seat[]=[];
    movieName:String;
    theatreName:String;
    numberOfTicket:number;
    totaCost:number;
    customerId:number;

    constructor(seats:Seat[],movieName:String,
        theatreName:String,
        numberOfTicket:number,
        totaCost:number,
        customerId:number){
        this.seats=seats;
        this.movieName=movieName;
        this.theatreName=theatreName;
        this.numberOfTicket=numberOfTicket;
        this.totaCost=totaCost;
        this.customerId=customerId;
    }
}