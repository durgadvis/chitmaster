import { Injectable } from '@angular/core';
import { Headers, Response }  from '@angular/http';
import { FormGroup} from '@angular/forms'; 
import { AuthHttp } from 'angular2-jwt';
import 'rxjs/add/operator/map';

@Injectable()
export class ChitmasterGroupDashboardService {
    private headers = new Headers({'content-type': 'application/json'});

    constructor(private authHttp: AuthHttp){}
    
    getChitGroups(){
        console.log('Calling the service using AuthHttp');
        const url:string="http://localhost:8080/chitmaster/getGroups";
        return this.authHttp.get(url,{headers:this.headers,withCredentials:false}).
        map((response:Response) => response.json());
    }
     
}