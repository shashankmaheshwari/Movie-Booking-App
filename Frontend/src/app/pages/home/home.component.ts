import { Component, OnInit } from '@angular/core';
import { MovieService } from 'src/app/services/movie.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

 
  movieList:any=[];
  

  constructor(private _movie:MovieService) { }

  ngOnInit(): void {
     this.getAllMovies();
  }
  searchMovies(key:any){
    this.movieList=[];
    this.getAllMovies(key);
  }

  public getAllMovies(key:string=""){
    console.log(key);
    this._movie.getMovieBasedMovieName(key).subscribe(
      (data)=>{
       
        this.movieList=data;
        console.log(data);
        
      },
      (error)=>{
     
        console.log(error);
      }
    )
  }


}
