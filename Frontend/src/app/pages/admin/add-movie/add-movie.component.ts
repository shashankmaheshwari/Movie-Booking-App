import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import compositeId from 'src/app/modals/CompositeId';
import { Movie } from 'src/app/modals/Movie';
import { MovieService } from 'src/app/services/movie.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-add-movie',
  templateUrl: './add-movie.component.html',
  styleUrls: ['./add-movie.component.css']
})
export class AddMovieComponent implements OnInit {
  movie:Movie;
  constructor(private _movieService:MovieService,private router:Router) {
    this.movie=new Movie(
      new compositeId("", ""),0,0,""
    );
   }
  
  ngOnInit(): void {
  }
  formSubmit(){
    console.log(this.movie);
    this._movieService.addMovie(this.movie).subscribe(
      (res)=>{
        Swal.fire('Successfully  !!','Movie Added !!','success').then((e)=>{
          console.log(res);
          this.router.navigate(['/admin']);
        })
        
      },(error)=>{
        console.log(error);
      }
    )
  }

}
