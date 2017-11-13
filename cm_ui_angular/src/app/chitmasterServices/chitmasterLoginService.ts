import { Injectable } from '@angular/core';
import { Headers,Http, Response }  from '@angular/http';
import { FormGroup} from '@angular/forms'; 
import { Observable } from 'rxjs/Rx';
import { Subject } from 'rxjs/Subject';
import { BehaviorSubject } from 'rxjs/BehaviorSubject';
import 'rxjs/add/operator/map';

@Injectable()
export class chitmasterLoginService {
    private headers = new Headers({'content-type': 'application/json'});
    private loggedIn: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
    private $loggedIn : boolean;

  constructor(private _http: Http){}
    
    authenticate(loginFormValue:FormGroup) : Observable<any> {
        console.log("login service received  "+JSON.stringify(loginFormValue));
        
        const url:string="http://localhost:8080/chitmaster/login";
        return this._http.post(url,JSON.stringify(loginFormValue),{headers:this.headers,withCredentials:false}).
        map((response:Response) => {
        let user = response.json();
        if(user && user.token){
            localStorage.setItem('curruser',JSON.stringify(response.json().token));
            localStorage.setItem('emailId',JSON.stringify(response.json().username));
            
         }
        });
    }

    get isLoggedIn() {
        console.log(!!localStorage.getItem('curruser'));
        this.loggedIn.next(!!localStorage.getItem('curruser'));
        return this.loggedIn.asObservable();
    }

    get is$LoggedIn() {
        console.log("$loggedIn" + !!localStorage.getItem('curruser'));
        this.$loggedIn=!!localStorage.getItem('curruser');
        return this.$loggedIn;
    }
     
}