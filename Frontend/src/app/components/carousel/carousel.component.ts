import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import compositeId from 'src/app/modals/CompositeId';
import { Movie } from 'src/app/modals/Movie';
import { MovieService } from 'src/app/services/movie.service';

@Component({
  selector: 'app-carousel',
  templateUrl: './carousel.component.html',
  styleUrls: ['./carousel.component.css']
})
export class CarouselComponent implements OnInit {

  images: string[] = [
    'https://assets-in.bmscdn.com/iedb/movies/images/mobile/listing/xxlarge/ib71-et00312966-1683884508.jpg',
    'https://assets-in.bmscdn.com/iedb/movies/images/mobile/listing/xxlarge/guardians-of-the-galaxy-vol-3-et00310794-1683529214.jpg',
    'https://assets-in.bmscdn.com/iedb/movies/images/mobile/listing/xxlarge/2018-et00357072-1681718285.jpg',
     
  ];
  currentImage: string = this.images[0];
  activeIndex: number = 0;
  private interval: any;
  movie:Movie;
  constructor(private _movieService:MovieService,private router:Router) {
    this.movie=new Movie(
      new compositeId("", ""),0,0,""
    );
   }

  ngOnInit(): void {
    this.getMovieImages();
    this.startCarousel();
  }
  startCarousel() {
    this.interval = setInterval(() => {
      this.nextImage();
    }, 3000);
  }

  nextImage() {
    this.activeIndex = (this.activeIndex + 1) % this.images.length;
    this.currentImage = this.images[this.activeIndex];
  }

  goToImage(index: number) {
    this.activeIndex = index;
    this.currentImage = this.images[this.activeIndex];
    clearInterval(this.interval);
    this.startCarousel();
  }
  public getMovieImages(){
    this._movieService.getAllMovies().subscribe(
      (res)=>{
        
        console.log(res);
      },
      (error)=>{
        console.log(error);
      }
    )
  }

}
