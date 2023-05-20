import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import baseUrl from './helper';

import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {
  requestHeader = new HttpHeaders(

     {"No-Auth":"True"}
    
    );
  constructor(private http:HttpClient) { }

  // add Customer
  public registerCustomer(user:any){
       return this.http.post(`${baseUrl}/register`,user,{ headers:this.requestHeader,
       "responseType":"text"
        }) 
  }
 // Forgot Password
  public changePassword(userName:String,password:String){
          console.log(localStorage.getItem("token"));
    return this.http.post(`${baseUrl}/${userName}/forgot`,password,{"headers":{
       
       "authorization":`Bearer ${localStorage.getItem("token")}`,
       
      // 'Access-Control-Allow-Origin': '*',
      
       },
       "responseType": "text"
      })
  }
  
}
