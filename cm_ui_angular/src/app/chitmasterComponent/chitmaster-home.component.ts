import { Component, OnInit } from '@angular/core';
import { RegisterUser } from '../chitmasterModel/chitmasterRegisterUser'; 
import { NavBarComponent } from './chitmasterNavBar/chitmaster-navbar.component';
import { chitmasterLoginService } from '../chitmasterServices/chitmasterLoginService';
import { Router } from '@angular/router';

@Component({
    selector:'chitmaster-home',
    templateUrl:'/app/chitmasterHtmls/chitmasterHome.html',
    providers : [chitmasterLoginService]
  })

export class chitmasterHomeComponent implements OnInit {

  private loggedIn : any;

  constructor(private loginService: chitmasterLoginService, private router: Router) { }

  ngOnInit() {
    this.loginService.isLoggedIn
    .subscribe(data  => {
      if (data) {
        this.router.navigate(["dashboard"]);
      }
    },
    error => {
      console.log("Error for the User");
    });
    //console.log(this.loggedIn);
   }

}