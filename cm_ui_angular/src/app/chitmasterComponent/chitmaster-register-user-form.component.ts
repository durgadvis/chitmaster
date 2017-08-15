import {Component, OnInit} from '@angular/core';
import { RegisterUser} from '../chitmasterModel/chitmasterRegisterUser'; 
import { chitmasterRegisterService } from '../chitmasterServices/chitmasterRegisterService';

@Component({
    selector:'chitmaster-register-user-form',
    templateUrl:'/app/chitmasterHtmls/chitmasterRegister.html',
    providers : [chitmasterRegisterService]

})

export class ChitmasterRegisterUserComponent implements OnInit{

submitted=false;

constructor(private registerUserService : chitmasterRegisterService ){
}

ngOnInit(){
       
}


onSubmit(value:RegisterUser){
    this.registerUserService.pushUser(value).subscribe( 
        
    );
    console.log(value) ;
}

}