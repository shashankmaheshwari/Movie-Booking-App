import { Component, OnInit } from '@angular/core';
import compositeId from 'src/app/modals/CompositeId';
import { Movie } from 'src/app/modals/Movie';

@Component({
  selector: 'app-add-movie',
  templateUrl: './add-movie.component.html',
  styleUrls: ['./add-movie.component.css']
})
export class AddMovieComponent implements OnInit {
  movie:Movie;
  constructor() {
    this.movie=new Movie(
      new compositeId("", ""),0,0
    );
   }
  
  ngOnInit(): void {
  }
  formSubmit(){
    console.log(this.movie);
  }

}
