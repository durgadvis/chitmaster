import { Injectable } from '@angular/core';
import { Headers,Http, Response }  from '@angular/http';
import { FormGroup} from '@angular/forms'; 
import 'rxjs/add/operator/map';

@Injectable()
export class ChitmasterCreateGroupService {
    private headers = new Headers({'content-type': 'application/json'});

    constructor(private _http : Http){}
    
    createGroup(createGroupForm:FormGroup){
        console.log("submittin user  "+JSON.stringify(createGroupForm));
        const url:string="http://localhost:8080/chitmaster/createGroup";
        return this._http.post(url,JSON.stringify(createGroupForm),{headers:this.headers,withCredentials:false}).
        map((response:Response) => response.json());
    }
     
}