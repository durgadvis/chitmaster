import { Injectable } from '@angular/core';
import { Headers, Response }  from '@angular/http';
import { FormGroup} from '@angular/forms'; 
import { AuthHttp } from 'angular2-jwt';
import 'rxjs/add/operator/map';

@Injectable()
export class ChitmasterGroupDetailsService {

    constructor(private authHttp: AuthHttp){}
    
    getChitGroupDetail(id:number){
        console.log('Calling the service using AuthHttp');
        const url:string="http://localhost:8080/chitmaster/getGroupDetail?id=" + id;
        console.log(url);
        return this.authHttp.get(url,{withCredentials:false}).
        map((response:Response) => response.json());
    }
     
}