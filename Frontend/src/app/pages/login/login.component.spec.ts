import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LoginComponent } from './login.component';
import { LoginService } from 'src/app/services/login.service';
import { MatCardModule } from '@angular/material/card';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { RouterTestingModule } from '@angular/router/testing';

describe('LoginComponent', () => {
  let component: LoginComponent;
  let fixture: ComponentFixture<LoginComponent>;
  let loginService: jasmine.SpyObj<LoginService>;

  beforeEach(async () => {
    const loginSpy = jasmine.createSpyObj('loginService', ['getCustomer','isLoggedIn']);
    await TestBed.configureTestingModule({
      imports:[MatCardModule,HttpClientTestingModule,MatSnackBarModule,RouterTestingModule], 
      providers:[ { provide: LoginService, useValue: loginSpy }],
      declarations: [ LoginComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(LoginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
