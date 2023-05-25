import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import baseUrl from './helper';

@Injectable({
  providedIn: 'root',
})
export class LoginService {
  constructor(private http: HttpClient) {}

  // Generate Token
  public generateToken(loginData: any) {
    return this.http.post(`${baseUrl}/login`, loginData);
  }
  //login user:set token in local storage
  public loginUser(token: any) {
    localStorage.setItem('token', token);
    return true;
  }

  //is login:user is logged in or not
  public isLoggedIn() {
    let tokenStr = localStorage.getItem('token');
    if (tokenStr == undefined || tokenStr == '' || tokenStr == null)
      return false;
    else return true;
  }

  // log out :remove token from local storage
  public logout() {
    localStorage.removeItem('token');
    localStorage.removeItem('customer');
    localStorage.clear();
    return true;
  }
  /// get token
  public getToken() {
    return localStorage.getItem('token');
  }

  // set userDetails in local storage
  public setCustomer(customer: any) {
    localStorage.setItem('customer', JSON.stringify(customer));
  }
  // getUser
  public getCustomer(){
    let userStr=localStorage.getItem('customer');
    if(userStr!=null){
      return JSON.parse(userStr);
    }else{
      this.logout();
      return null;
    }
  }
  // //setRole
  // public setRole(role:any){

  //   localStorage.setItem("role",role);
  // }
  // getRole
  public getRole(){
    let user=this.getCustomer();
    return user.role;
    
  }
 
}
