import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import compositeId from 'src/app/modals/CompositeId';
import { Movie } from 'src/app/modals/Movie';
import { MovieService } from 'src/app/services/movie.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-add-movie',
  templateUrl: './add-movie.component.html',
  styleUrls: ['./add-movie.component.css'],
})
export class AddMovieComponent implements OnInit {
  movie: Movie;
  constructor(
    private _movieService: MovieService,
    private _snackBar: MatSnackBar,
    private router: Router
  ) {
    this.movie = new Movie(new compositeId('', ''), 0, 0, '');
  }

  ngOnInit(): void {}
  formSubmit() {
    console.log(this.movie);
    this._movieService.addMovie(this.movie).subscribe(
      (res) => {
        Swal.fire('Successfully  !!', 'Movie Added !!', 'success').then((e) => {
          console.log(res);
          this.router.navigate(['/admin']);
        });
      },
      (error) => {
        this.showValuesInSnackbar(error.error);
        console.log(error);
        console.log('fail');
      }
    );
  }

  showValuesInSnackbar(jsonObject: any) {
    const keys = Object.keys(jsonObject);

    let index = 0;
    const delay = 2000; // Delay in milliseconds between each snackbar

    const displayNextSnackbar = () => {
      if (index < keys.length) {
        const key = keys[index];
        const displayMessage = `${key}:${jsonObject[key]}`;
        this.openSnackBar(displayMessage);
        index++;

        setTimeout(displayNextSnackbar, delay);
      }
    };

    displayNextSnackbar();
  }

  openSnackBar(message: string): void {
    this._snackBar.open(message, 'Close', {
      duration: 3000,
      verticalPosition: 'top',
      horizontalPosition: 'right',
    });
  }
}
