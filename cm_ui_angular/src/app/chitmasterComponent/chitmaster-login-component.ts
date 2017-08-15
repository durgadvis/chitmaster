import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms'; 
import { Router, ActivatedRoute } from '@angular/router';
import { chitmasterLoginService } from '../chitmasterServices/chitmasterLoginService';

@Component({
    selector:'chitmaster-login-form',
    templateUrl:'/app/chitmasterHtmls/chitmasterLogin.html',
    providers : [chitmasterLoginService]

})

export class ChitmasterLoginComponent implements OnInit{

submitted=false;
loginForm: FormGroup;

constructor(private formBuilder: FormBuilder,
private loginService: chitmasterLoginService,
private route: ActivatedRoute,
private router: Router,){
}

ngOnInit(){
       this.loginForm=this.formBuilder.group(
        {
            username : ['vishl',[Validators.required]],
            password : ['',[Validators.required]]
        }
    )       
}


onSubmit(){
        console.log("hi it is here"+  JSON.stringify(this.loginForm.value)) ;
       // this.loginService.authenticate(this.loginForm.value).subscribe(
      //      data  => {
                        this.router.navigate(["dashboard"]);
        //    },
          //  error => {
            //    console.log("Error for the User");
            //});
       

}

}