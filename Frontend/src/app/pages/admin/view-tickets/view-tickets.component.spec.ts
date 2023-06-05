import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewTicketsComponent } from './view-tickets.component';
import { MovieService } from 'src/app/services/movie.service';
import { HttpClientTestingModule } from '@angular/common/http/testing';
// import { MatDialogModule } from '@angular/material/dialog/dialog-module';
import { MatDialogModule } from '@angular/material/dialog';
import { LoginService } from 'src/app/services/login.service';

describe('ViewTicketsComponent', () => {
  let component: ViewTicketsComponent;
  let fixture: ComponentFixture<ViewTicketsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports:[MatDialogModule,HttpClientTestingModule], 
      providers:[MovieService,LoginService],
   
      declarations: [ ViewTicketsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewTicketsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
