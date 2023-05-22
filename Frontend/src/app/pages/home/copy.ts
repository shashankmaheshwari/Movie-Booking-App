// <div class="bootstrap-wrapper">
//   <div class="container">
//     <div class="row" style="margin-top: 20px">
//       <div class="col-md-6 offset-md-3">
//         <mat-form-field appearance="outline" class="searchbar w-50">
//           <mat-label class="searchField">Search for Movies</mat-label>
//           <input
//             matInput
//             type="text"
//             name="search"
//             id="search"
//             #searchKey
//             (keyup)="searchMovies(searchKey.value)"
//             placeholder="Search for Movies"
//           />
//           <mat-icon matSuffix>search</mat-icon>
//         </mat-form-field>
//       </div>
//     </div>
//     <div class="row  gy-4">
//       <div class="col-md-3 " style="background-color: red;" *ngFor="let m of movieList">
//         <mat-card class="movie-card">
//           <mat-card-header>
//             <mat-card-title>{{ m.compositeId.movieName }}</mat-card-title>
//             <mat-card-subtitle>{{ m.compositeId.theatreName }}</mat-card-subtitle>
//           </mat-card-header>
          
//             <img class="movieImage" mat-card-image src="{{ m.movieImage }}" alt="Movie Image" />
          
//           <mat-card-actions class="buttonCSS">
//             <button mat-raised-button color="primary" *ngIf="m.ticketStatus == 'BOOK ASAP'" mat-button>
//               {{ m.ticketStatus }}
//             </button>
//             <button mat-raised-button *ngIf="m.ticketStatus == 'SOLD OUT'" mat-button color="warn">
//               {{ m.ticketStatus }}
//             </button>
//             <button
//               color="warn"
//               (click)="deleteMovie(m.compositeId.movieName, m.movieId)"
//               aria-label="Example icon button with a delete icon"
//             >
//               <mat-icon>delete</mat-icon>
//             </button>
//           </mat-card-actions>
//         </mat-card>
//       </div>
//     </div>
//   </div>
// </div>
