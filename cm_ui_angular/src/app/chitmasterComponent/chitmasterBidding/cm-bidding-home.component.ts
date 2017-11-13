import { Component, OnInit, Pipe, PipeTransform } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { ChitMasterBiddingService } from '../../chitmasterServices/chitMasterBiddingService';
import {Subscription} from 'rxjs/Subscription';

@Component({
    selector:'biddinghome',
    templateUrl:'/app/chitmasterHtmls/chitmasterBiddingHome.html',
    providers : [ChitMasterBiddingService],

  })

export class ChitmasterBiddingHomeComponent implements OnInit {

    item: boolean;
    subscription:Subscription;  
    initialBiddingMap:any;
    id:any;
    username:string;
    curruser:string;
    bidValue = new Array(25);
    constructor(private biddingService:ChitMasterBiddingService,
        private router:Router,private route:ActivatedRoute){ 
            this.curruser=JSON.parse(localStorage.getItem('emailId'));
        }

    ngOnInit() {
         this.id = this.route.snapshot.params['chitGroupId'];
        this.biddingService.getInitialBiddingMap(this.id).subscribe(
            biddingMapResp => this.initialBiddingMap = biddingMapResp);
        this.subscription = this.biddingService.mapUpdatedFlag$
            .subscribe(item => {
                this.biddingService.getUpdatedBidMap(this.id).subscribe(
                    updatedBiddingMapResp => this.initialBiddingMap = updatedBiddingMapResp);
                    console.log("successfully updated map");
            });
    }


    bidSubmit(updatedValue:any) {
        this.username = JSON.parse(localStorage.getItem("emailId"));
        console.log("updated value on click"+updatedValue);
        this.biddingService.updateBidMap(this.id, this.username, updatedValue).subscribe();
    }

    ngOnDestroy() {
        // prevent memory leak when component is destroyed
        this.subscription.unsubscribe();
      }
}
