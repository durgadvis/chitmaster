import { Injectable } from '@angular/core';
import { Headers, Response, URLSearchParams }  from '@angular/http';
import { FormGroup} from '@angular/forms'; 
import { AuthHttp } from 'angular2-jwt';
import { BehaviorSubject } from 'rxjs/BehaviorSubject';
import 'rxjs/add/operator/map';

@Injectable()
export class ChitMasterBiddingService {

    private mapUpdatedFlag = new BehaviorSubject<boolean>(false);
    mapUpdatedFlag$ = this.mapUpdatedFlag.asObservable();

    constructor(private authHttp: AuthHttp){}
    
        getInitialBiddingMap(id:number){
        console.log('Calling the service using AuthHttp');
        const url:string="http://localhost:8080/chitmaster/getInitialBidding?chitGroupId=" + id;
        console.log(url);
        return this.authHttp.get(url,{withCredentials:false}).
        map((response:Response) => response.json());
    }
     
    updateBidMap(chitGroupId:number, username:string, bidValue:number) {
        const url:string="http://localhost:8080/chitmaster/updateBidValue";
        console.log(url);
        return this.authHttp.post(url,JSON.stringify({
            "chitGroupId": chitGroupId,
            "userId": username,
            "bidValue": bidValue
          }),{withCredentials:false}).
          map((response:Response) => this.mapUpdatedFlag.next(true));

    }

    getUpdatedBidMap(id:number){
        console.log('Calling the service using AuthHttp');
        const url:string="http://localhost:8080/chitmaster/getUpdatedBidMap?chitGroupId=" + id;
        console.log(url);
        return this.authHttp.get(url,{withCredentials:false}).
        map((response:Response) => response.json());
    }

}