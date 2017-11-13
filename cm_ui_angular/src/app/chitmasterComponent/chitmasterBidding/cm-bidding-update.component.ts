import {Component}    from '@angular/core';
import {Subscription} from 'rxjs/Subscription';
import { ChitMasterBiddingService } from '../../chitmasterServices/chitMasterBiddingService';

@Component({
  selector: 'obs-comp',
  template: `<p> item: {{item}} </p>`
})
export class BiddingObservingComponent {
  item: boolean;
  subscription:Subscription;
  constructor(private biddingService:ChitMasterBiddingService) {}

  ngOnInit() {
    this.subscription = this.biddingService.mapUpdatedFlag$
       .subscribe(item => this.item = item)
  }
  ngOnDestroy() {
    // prevent memory leak when component is destroyed
    this.subscription.unsubscribe();
  }
}