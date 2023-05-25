import { Seat } from "./Seat";

export class Ticket{

    seat:Seat[]=[];
    movieName:String;
    theatreName:String;
    numberOfTicket:number;
    totalCost:number;

    constructor(seat:Seat[],movieName:String,
        theatreName:String,
        numberOfTicket:number,
        totalCost:number){
        this.seat=seat;
        this.movieName=movieName;
        this.theatreName=theatreName;
        this.numberOfTicket=numberOfTicket;
        this.totalCost=totalCost;
    }
}