import { Component, OnInit } from '@angular/core';
import { chitmasterLoginService } from '../../chitmasterServices/chitmasterLoginService';
import { Observable } from 'rxjs/Rx';

@Component({
  selector: 'navbar',
  templateUrl: '/app/chitmasterHtmls/chitmasterNav.html',
  providers : [chitmasterLoginService]
})
export class NavBarComponent { 
  
  constructor(private loginService: chitmasterLoginService) { }

  ngOnInit() { }

  get isLoggedIn() {
      return this.loginService.is$LoggedIn;  
  }

}

