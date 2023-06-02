import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Seat } from 'src/app/modals/Seat';

import { Ticket } from 'src/app/modals/Ticket';
import { LoginService } from 'src/app/services/login.service';
import { MovieService } from 'src/app/services/movie.service';
import { SeatDialogComponent } from '../../seat-dialog/seat-dialog.component';

@Component({
  selector: 'app-view-tickets',
  templateUrl: './view-tickets.component.html',
  styleUrls: ['./view-tickets.component.css'],
})
export class ViewTicketsComponent implements OnInit {
  ticket: any = [];
  img: string = '';
  errorMessage: String = '';

  seatList: Seat[] = [];
  constructor(
    private _movie: MovieService,
    public dialog: MatDialog,
    private login: LoginService
  ) {
    //  this.ticket= new Ticket(this.seatList,"","",0,0);
  }

  ngOnInit(): void {
    this.isCheck();
  }

  public getTicketsAdmin() {
    this._movie.getAllTicketsAdmin().subscribe(
      (res) => {
        this.ticket = res;
        console.log(res);
        console.log('Tickets ....');
        console.log(this.ticket);
      },
      (error) => {
        this.errorMessage = error.error.errorMessage;
        console.log(error);
      }
    );
  }
  public isCheck() {
    if (this.login.isLoggedIn() && this.login.getRole() == 'ADMIN') {
      this.getTicketsAdmin();
    } else if (this.login.isLoggedIn() && this.login.getRole() == 'USER') {
      this.getTicketsUser();
    }
    return;
  }
  public getTicketsUser() {
    this._movie
      .getTicketsUserBasedOnUserId(this.login.getCustomer().loginId)
      .subscribe(
        (res) => {
          this.ticket = res;
          console.log(this.ticket);
        },
        (error) => {
          this.errorMessage = error.error.errorMessage;
          console.log(error);
        }
      );
  }
  openDialog(seat1: any): void {
    const dialogRef = this.dialog.open(SeatDialogComponent, {
      width: '400px',
      data: { seats: seat1 }, // Pass the seat data to the dialog component
    });

    dialogRef.afterClosed().subscribe((result) => {
      console.log('The dialog was closed');
      // Perform any necessary actions after the dialog is closed
    });
  }
}
