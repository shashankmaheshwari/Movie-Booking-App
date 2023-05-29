import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import baseUrl from './helper';

@Injectable({
  providedIn: 'root'
})
export class MovieService {
  requestHeader = new HttpHeaders(

    {
      "authorization":`Bearer ${localStorage.getItem("token")}`
   
     }
   
   );
  constructor(private http: HttpClient) { }
  // GET ALL MOVIES
  public getAllMovies(){
    return this.http.get(`${baseUrl}/moviebooking/all`);
   } ;
   //GET MOVIES BASED ON THE MOVIE NAME
   public getMovieBasedMovieName(searchKey:string=""){
   
    return this.http.get(`${baseUrl}/moviebooking/movies/search?searchKeyword=${searchKey}`);
   }
   // ADD Movie
   public addMovie(movie:any){
    console.log(`${localStorage.getItem("token")}`);
    return this.http.post(`${baseUrl}/moviebooking/addMovie`,movie,
    {
      "headers": { "Authorization": `Bearer ${localStorage.getItem("token")}` }
    }
    );
   }
   //delete Movie
   public deleteMovie(movieId:any,movieName:any){
    console.log(`${localStorage.getItem("token")}`);
    return this.http.delete(`${baseUrl}/moviebooking/${movieName}/delete/${movieId}`,
    {
      "headers": { "Authorization": `Bearer ${localStorage.getItem("token")}` }
    }
    );
   }
   //get all tickets
   public getAllTicketsAdmin(){
    return this.http.get(`${baseUrl}/moviebooking/view/tickets`,
    {
      "headers": { "Authorization": `Bearer ${localStorage.getItem("token")}` }
    }
    
    );
   }
   //book a ticket
   public getMovie(movieName:String,theatreName:String){
    console.log(`${localStorage.getItem("token")}`);
    console.log(this.requestHeader);
    return this.http.get(`${baseUrl}/moviebooking/search/${movieName}/${theatreName}`,
    {
      "headers": { "Authorization": `Bearer ${localStorage.getItem("token")}` }
    }
      );
   }

   // get all seats for particluar movieId
   public getAllSeatsWithMovieId(movieId:any){
       return this.http.get(`${baseUrl}/seats/${movieId}`,
       {
        "headers": { "Authorization": `Bearer ${localStorage.getItem("token")}` }
      }
       );
   }
   // Book A Ticket 
   public bookTicket(ticket:any){
      return this.http.post(`${baseUrl}/moviebooking/add`,ticket,
      {
        "headers": { "Authorization": `Bearer ${localStorage.getItem("token")}` }
      }
      
      );
   }
  //GET A TICKET BASED ON TICKET Id
  public getTicketBasedOnTicketId(ticketId:any){
    return this.http.get(`${baseUrl}/moviebooking/get/movie/tickets/${ticketId}`,
    {
      "headers": { "Authorization": `Bearer ${localStorage.getItem("token")}` }
    }
    
    );
  }
  //GET ALL TICKETS BASED IN USERID
  public getTicketsUserBasedOnUserId(userId:any){
    return this.http.get(`${baseUrl}/moviebooking/get/tickets/${userId}`,
    {
      "headers": { "Authorization": `Bearer ${localStorage.getItem("token")}` }
    }
    
    );
  }

  // update Ticket Status
  public updateTicketStatus(movieName:String,theatreName:String){
    return this.http.put(`${baseUrl}/moviebooking/${movieName}/${theatreName}/update/ticket`, null,
    {
      "headers": { "Authorization": `Bearer ${localStorage.getItem("token")}` },
      "responseType":"text"
    }
    
    );
  }

  }

