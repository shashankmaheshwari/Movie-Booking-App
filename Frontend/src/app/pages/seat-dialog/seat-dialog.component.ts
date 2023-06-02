import { Component, OnInit, Inject } from '@angular/core';
import {
  MAT_DIALOG_DATA,
  MatDialog,
  MatDialogRef,
} from '@angular/material/dialog';
import { Seat } from 'src/app/modals/Seat';
import { LoginService } from 'src/app/services/login.service';
import { MovieService } from 'src/app/services/movie.service';

@Component({
  selector: 'app-seat-dialog',
  templateUrl: './seat-dialog.component.html',
  styleUrls: ['./seat-dialog.component.css'],
})
export class SeatDialogComponent implements OnInit {
  seatList: Seat[] = [];
  constructor(
    private _movie: MovieService,
    public dialog: MatDialog,
    private login: LoginService,
    public dialogRef: MatDialogRef<SeatDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: { seats: Seat[] }
  ) {}

  ngOnInit(): void {}
  onClose(): void {
    this.dialogRef.close();
  }
}
