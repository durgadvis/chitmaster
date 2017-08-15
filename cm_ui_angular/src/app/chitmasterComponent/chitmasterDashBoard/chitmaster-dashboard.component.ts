import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
    selector:'dashboard',
    templateUrl:'/app/chitmasterHtmls/chitmasterDashBoard.html'
  })

export class ChitmasterDashBoardComponent {

    constructor(private router:Router,private route:ActivatedRoute){

    }

    callGroupComp(){
        console.log("call group function called");
        this.router.navigate(["creategroup"]);
    }

}