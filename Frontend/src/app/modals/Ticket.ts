import { Seat } from "./Seat";


export class Ticket{

    seats:Seat[]=[];
    movieName:String;
    theatreName:String;
    numberOfTicket:number;
    totalCost:number;

    constructor(seats:Seat[],movieName:String,
        theatreName:String,
        numberOfTicket:number,
        totalCost:number){
        this.seats=seats;
        this.movieName=movieName;
        this.theatreName=theatreName;
        this.numberOfTicket=numberOfTicket;
        this.totalCost=totalCost;
    }
}