import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfileComponent } from './profile.component';
import { LoginService } from 'src/app/services/login.service';
import { MatCardModule } from '@angular/material/card';
import { HttpClientTestingModule } from '@angular/common/http/testing';

describe('ProfileComponent', () => {
  let component: ProfileComponent;
  let fixture: ComponentFixture<ProfileComponent>;
  let loginService: jasmine.SpyObj<LoginService>;
  
  beforeEach(async () => {
    const loginSpy = jasmine.createSpyObj('loginService', ['getCustomer','isLoggedIn']);
    await TestBed.configureTestingModule({
      imports:[MatCardModule,HttpClientTestingModule], 
      providers:[ { provide: LoginService, useValue: loginSpy }],
      declarations: [ ProfileComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    loginService = TestBed.inject(LoginService) as jasmine.SpyObj<LoginService>;
    loginService.getCustomer.and.returnValue({
      firstName:'firstName',
      lastName:'lastName',
      emailId:'email@email.com',
      loginId:1,
      userName:'userName',
      password:'password',
      confirmPassword:'password',
      contactNo:9999999999,
      role:'user'
    });
    fixture = TestBed.createComponent(ProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
