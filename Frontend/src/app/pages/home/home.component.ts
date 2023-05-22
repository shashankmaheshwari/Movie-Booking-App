import { Component, OnInit, Renderer2 } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MovieService } from 'src/app/services/movie.service';
import Swal from 'sweetalert2';
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

 
  movieList:any=[];
  

  constructor(private _movie:MovieService,private _snak: MatSnackBar,private renderer: Renderer2) { }

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

  public deleteMovie(movieName:any,movieId:any){

    Swal.fire({
      icon: 'info',
      showCancelButton: true,
      confirmButtonText: 'Delete',
      title: 'Are you sure , want to delete this Movie?',
    }).then((result) => {
      if (result.isConfirmed) {
        //confim
        console.log(movieId,movieName);
        this._movie.deleteMovie(movieId,movieName).subscribe(
          (data) => {
            this._snak.open('Movie deleted ', '', {
              duration: 3000,
            });
            this.ngOnInit();
          
          },

          (error) => {
            this._snak.open('Error in deleting Movie', '', {
              duration: 3000,
            });
            console.log(error);
          }
        );
      }
    });
   
  }
 
 
  


}
