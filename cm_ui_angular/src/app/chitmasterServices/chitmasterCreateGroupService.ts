import { Injectable } from '@angular/core';
import { Headers,Http, Response }  from '@angular/http';
import { FormGroup} from '@angular/forms'; 
import { AuthHttp } from 'angular2-jwt';
import 'rxjs/add/operator/map';

@Injectable()
export class ChitmasterCreateGroupService {    

    constructor(private authHttp: AuthHttp){}
    
    createGroup(createGroupForm:FormGroup){
        console.log("submittin user  "+JSON.stringify(createGroupForm));
        const url:string="http://localhost:8080/chitmaster/createGroup";
        return this.authHttp.post(url,JSON.stringify(createGroupForm),{headers:this.headers,withCredentials:false}).
        map((response:Response) => response.json());
    }
     
}