// import { TestBed } from '@angular/core/testing';

// import { LoginService } from './login.service';

// describe('LoginService', () => {
//   let service: LoginService;

//   beforeEach(() => {
//     TestBed.configureTestingModule({});
//     service = TestBed.inject(LoginService);
//   });

//   it('should be created', () => {
//     expect(service).toBeTruthy();
//   });
// });
import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { LoginService } from './login.service';
import baseUrl from './helper';


describe('LoginService', () => {
  let loginService: LoginService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [LoginService],
    });
    loginService = TestBed.inject(LoginService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should be created', () => {
    expect(loginService).toBeTruthy();
  });

  it('should generate token', () => {
    const mockLoginData = { username: 'testuser', password: 'testpassword' };
    const mockToken = 'mock-token';

    loginService.generateToken(mockLoginData).subscribe((response) => {
      expect(response).toEqual(mockToken);
    });

    const req = httpMock.expectOne(`${baseUrl}/login`);
    expect(req.request.method).toBe('POST');
    expect(req.request.body).toEqual(mockLoginData);
    req.flush(mockToken);
  });

  it('should login user and set token in local storage', () => {
    const mockToken = 'mock-token';

    loginService.loginUser(mockToken);

    const storedToken = localStorage.getItem('token');
    expect(storedToken).toBe(mockToken);
  });

  it('should check if user is logged in', () => {
    localStorage.setItem('token', 'mock-token');

    expect(loginService.isLoggedIn()).toBe(true);

    localStorage.removeItem('token');
    expect(loginService.isLoggedIn()).toBe(false);
  });

  it('should logout user and clear local storage', () => {
    localStorage.setItem('token', 'mock-token');
    localStorage.setItem('customer', JSON.stringify({}));

    loginService.logout();

    expect(localStorage.getItem('token')).toBeNull();
    expect(localStorage.getItem('customer')).toBeNull();
  });

  it('should get token from local storage', () => {
    localStorage.setItem('token', 'mock-token');

    const token = loginService.getToken();

    expect(token).toBe('mock-token');
  });

  it('should set customer in local storage', () => {
    const mockCustomer = { id: 1, name: 'Test User' };

    loginService.setCustomer(mockCustomer);

    const storedCustomer = JSON.parse(localStorage.getItem('customer') || '');
    expect(storedCustomer).toEqual(mockCustomer);
  });

  it('should get customer from local storage', () => {
    const mockCustomer = { id: 1, name: 'Test User' };
    localStorage.setItem('customer', JSON.stringify(mockCustomer));

    const customer = loginService.getCustomer();

    expect(customer).toEqual(mockCustomer);
  });

  it('should return role of the customer', () => {
    const mockCustomer = { id: 1, name: 'Test User', role: 'admin' };
    spyOn(loginService, 'getCustomer').and.returnValue(mockCustomer);

    const role = loginService.getRole();

    expect(loginService.getCustomer).toHaveBeenCalled();
    expect(role).toBe('admin');
  });
});
