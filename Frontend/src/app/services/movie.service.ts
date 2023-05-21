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
    return this.http.get(`${baseUrl}/moviebooking/all`
   )} ;
   //GET MOVIES BASED ON THE MOVIE NAME
   public getMovieBasedMovieName(searchKey:string=""){
   
    return this.http.get(`${baseUrl}/moviebooking/movies/search?searchKeyword=${searchKey}`);
   }
   // ADD Movie
   public addMovie(movie:any){
    console.log(`${localStorage.getItem("token")}`);
    return this.http.post(`${baseUrl}/moviebooking/addMovie`,movie,{headers:this.requestHeader})
   }

  }

