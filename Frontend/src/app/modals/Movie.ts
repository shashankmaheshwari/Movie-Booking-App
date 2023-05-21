import compositeId from "./CompositeId";


export class Movie {
    compositeId:compositeId;
    movieCost: number;
    totalNoOfTickets: number; 
    movieImage:String;
    constructor(
        compositeId: compositeId,
        movieCost: number,
        totalNoOfTickets: number,
        movieImage:String
        ){
        this.compositeId = compositeId;
        this.movieCost = movieCost;
        this. totalNoOfTickets = totalNoOfTickets;
        this.movieImage=movieImage;
        }
}
 