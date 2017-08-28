import { Component , OnInit } from '@angular/core';
import { Validators, FormGroup, FormArray, FormBuilder } from '@angular/forms';
import { ChitmasterGroupModel } from '../../chitmasterModel/chitmasterGroupModel';
import {ChitmasterCreateGroupService } from '../../chitmasterServices/chitmasterCreateGroupService';
 

@Component({
    selector:'chitmaster-creategroup',
    templateUrl:'/app/chitmasterHtmls/chitmasterCreateGroup.html',
    providers : [ChitmasterCreateGroupService]
  })

export class ChitmasterCreateGroupComponent implements OnInit {
  
    public createGroupForm: FormGroup; // our form model

    // we will use form builder to simplify our syntax
    constructor(private _fb: FormBuilder, private chitmasterCreateGroupService:ChitmasterCreateGroupService) { }
  
  ngOnInit() {
       this.createGroupForm = this._fb.group({
            chitGroupName: ['', [Validators.required, Validators.minLength(5)]],
            chitValue: ['', [Validators.required]],
            commissionPercent: ['', [Validators.required]],
            minPercentBidding: ['', [Validators.required]],
            maxPercentBidding: ['', [Validators.required]],
            dateofBidding: ['', [Validators.required]],
            startDate: ['', [Validators.required]],
            endDate: ['', [Validators.required]],
            members: this._fb.array([  this.initMembers() ,         
            ])
        });
    }

initMembers(){
    return this._fb.group({
        emailId:['',Validators.required]
    });

}

    addMembers() {
    // add address to the list
    const control = <FormArray>this.createGroupForm.controls['members'];
    control.push(this.initMembers());
}

removeMembers(i: number) {
    // remove address from the list
    const control = <FormArray>this.createGroupForm.controls['members'];
    control.removeAt(i);
}


    createGroup() {
        // call API to save customer
        console.log(JSON.stringify(this.createGroupForm.value));
        this.chitmasterCreateGroupService.createGroup(this.createGroupForm.value).subscribe();
        console.log(JSON.stringify(this.createGroupForm.value));
    }
}


