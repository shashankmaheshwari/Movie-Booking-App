import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TicketBookingComponent } from './ticket-booking.component';
import { RouterTestingModule } from '@angular/router/testing';
import { MovieService } from 'src/app/services/movie.service';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

describe('TicketBookingComponent', () => {
  let component: TicketBookingComponent;
  let fixture: ComponentFixture<TicketBookingComponent>;
  let mockActivatedRoute: any;
  beforeEach(async () => {
    mockActivatedRoute = {
      params: of({
        movieName: 'Mock Movie',
        theatreName: 'Mock Theatre',
        customerId: 123,
        movieId: 456,
      }),
    };
    await TestBed.configureTestingModule({

      imports:[ HttpClientTestingModule,RouterTestingModule], 
      providers:[MovieService,
        { provide: ActivatedRoute, useValue: mockActivatedRoute },
      
      ],
      declarations: [ TicketBookingComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TicketBookingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
