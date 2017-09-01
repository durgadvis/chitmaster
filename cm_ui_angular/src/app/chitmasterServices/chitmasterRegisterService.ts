import { Injectable } from '@angular/core';
import { Headers,Http, Response }  from '@angular/http';
import { RegisterUser} from '../chitmasterModel/chitmasterRegisterUser';
import 'rxjs/add/operator/map';

@Injectable()
export class chitmasterRegisterService {
    private headers = new Headers({'content-type': 'application/json'});

    constructor(private _http : Http){}
    
    pushUser(registerUser:RegisterUser){
        console.log("submittin user  "+JSON.stringify(registerUser));
        const url:string="http://localhost:8080/chitmaster/signup";
        return this._http.post(url,JSON.stringify(registerUser),{headers:this.headers,withCredentials:false}).
        map((response:Response) => {
            let user = response.json();
            if(user && user.token){
                localStorage.setItem('curruser',JSON.stringify(response.json().token));
            }
            
            });
    }
     
}