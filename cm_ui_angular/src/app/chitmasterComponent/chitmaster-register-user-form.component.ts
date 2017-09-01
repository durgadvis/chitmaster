import {Component, OnInit} from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { RegisterUser} from '../chitmasterModel/chitmasterRegisterUser'; 
import { chitmasterRegisterService } from '../chitmasterServices/chitmasterRegisterService';

@Component({
    selector:'chitmaster-register-user-form',
    templateUrl:'/app/chitmasterHtmls/chitmasterRegister.html',
    providers : [chitmasterRegisterService]

})

export class ChitmasterRegisterUserComponent implements OnInit{

submitted=false;

constructor(private registerUserService : chitmasterRegisterService,
            private router: Router) {
}

ngOnInit(){
       
}


onSubmit(value:RegisterUser){
    this.registerUserService.pushUser(value).subscribe( 
        data  => {
            this.router.navigate(["dashboard"]);
        },
        error => {
            console.log("Error for the User");
        });
    console.log(value) ;
}

}