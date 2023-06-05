import { TestBed } from '@angular/core/testing';

import { AdminGuard } from './admin.guard';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { LoginService } from './login.service';
import { RouterTestingModule } from '@angular/router/testing';

describe('AdminGuard', () => {
  let guard: AdminGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({

      imports:[HttpClientTestingModule,RouterTestingModule], 
      providers:[  LoginService ],
     
    });
    guard = TestBed.inject(AdminGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});
