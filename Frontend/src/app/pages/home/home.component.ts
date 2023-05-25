import { Component, OnInit, Renderer2 } from '@angular/core';
import { PageEvent } from '@angular/material/paginator';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/services/login.service';
import { MovieService } from 'src/app/services/movie.service';
import Swal from 'sweetalert2';
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

 
  movieList:any=[];
  

  constructor(private _movie:MovieService,private _snak: MatSnackBar,private renderer: Renderer2,public login:LoginService,private router:Router) { }

  ngOnInit(): void {
     this.getAllMovies();
     this.isAdmin();
     this.isUser();
  }
  searchMovies(key:any){
    this.movieList=[];
    this.getAllMovies(key);
  }
  public isAdmin(){
    if(this.login.isLoggedIn()&&this.login.getRole()=="ADMIN") return true;
    return false;
  }
  public isUser(){
    if(this.login.isLoggedIn()&&this.login.getRole()=="USER") return true;
    return false;
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
  public getMovie(movieName:string,theatreName:string,movieId:any){
    console.log("clicked");
    console.log(movieName);
      this._movie.getMovie(movieName,theatreName).subscribe(
          (res)=>{
            console.log(res);
            let customerId=this.login.getCustomer().loginId;
            const updatedmovieName = decodeURIComponent(movieName);
            console.log(updatedmovieName);
            this.router.navigate(['/movie', updatedmovieName, theatreName,customerId,movieId]);

            // this.router.navigate(["/customer/movie",movieName,id]);
          },
          (error)=>{
            console.log(error);
          }

      )
  }



  


}
