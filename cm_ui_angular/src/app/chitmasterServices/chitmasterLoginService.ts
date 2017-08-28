import { Injectable } from '@angular/core';
import { Headers,Http, Response }  from '@angular/http';
import { FormGroup} from '@angular/forms'; 
import 'rxjs/add/operator/map';

@Injectable()
export class chitmasterLoginService {
    private headers = new Headers({'content-type': 'application/json'});

    constructor(private _http : Http){}
    
    authenticate(loginFormValue:FormGroup){
        console.log("login service received  "+JSON.stringify(loginFormValue));
        
        const url:string="http://localhost:8080/chitmaster/login";
        return this._http.post(url,JSON.stringify(loginFormValue),{headers:this.headers,withCredentials:false}).
        map((response:Response) => {
        let user = response.json();
        if(user && user.token){
            localStorage.setItem('curruser',JSON.stringify(response.json().token));
        }
        
        });
    }
     
}