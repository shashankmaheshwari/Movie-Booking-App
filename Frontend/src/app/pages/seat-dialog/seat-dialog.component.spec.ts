import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SeatDialogComponent } from './seat-dialog.component';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { MAT_DIALOG_DATA, MatDialogModule, MatDialogRef } from '@angular/material/dialog';

describe('SeatDialogComponent', () => {
  let component: SeatDialogComponent;
  let fixture: ComponentFixture<SeatDialogComponent>;
  let mockDialogRef: MatDialogRef<SeatDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports:[MatDialogModule, HttpClientTestingModule,RouterTestingModule], 
      providers: [
        { provide: MatDialogRef, useValue: mockDialogRef },
        { provide: MAT_DIALOG_DATA, useValue: { seats: [] } },
      ],
      declarations: [ SeatDialogComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SeatDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
