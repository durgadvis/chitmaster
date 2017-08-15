import { Component , OnInit } from '@angular/core';
import { Validators, FormGroup, FormArray, FormBuilder } from '@angular/forms';
import { ChitmasterGroupModel } from '../../chitmasterModel/chitmasterGroupModel';
 

@Component({
    selector:'chitmaster-creategroup',
    templateUrl:'/app/chitmasterHtmls/chitmasterCreateGroup.html'
  })

export class ChitmasterCreateGroupComponent implements OnInit {
  
    public createGroupForm: FormGroup; // our form model

    // we will use form builder to simplify our syntax
    constructor(private _fb: FormBuilder) { }
  
  ngOnInit() {
       this.createGroupForm = this._fb.group({
            groupname: ['', [Validators.required, Validators.minLength(5)]],
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
    }
}


