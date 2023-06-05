import { TestBed } from '@angular/core/testing';

import { CustomerGuard } from './customer.guard';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { LoginService } from './login.service';

describe('CustomerGuard', () => {
  let guard: CustomerGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports:[HttpClientTestingModule,RouterTestingModule], 
      providers:[  LoginService ],
    });
    guard = TestBed.inject(CustomerGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});
