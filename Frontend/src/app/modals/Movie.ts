import compositeId from "./CompositeId";


export class Movie {
    compositeId:compositeId;
    movieCost: number;
    totalNoOfTickets: number; 
    constructor(
        compositeId: compositeId,
        movieCost: number,
        totalNoOfTickets: number,
        ){
        this.compositeId = compositeId;
        this.movieCost = movieCost;
        this. totalNoOfTickets = totalNoOfTickets;
        }
}
 