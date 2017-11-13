import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { ChitmasterGroupDashboardService } from '../../chitmasterServices/ChitMasterGroupDashboardService';


@Component({
    selector:'dashboard',
    templateUrl:'/app/chitmasterHtmls/chitmasterDashBoard.html',
    providers : [ChitmasterGroupDashboardService]
  })

export class ChitmasterDashBoardComponent implements OnInit {

    chitGroupsList : any[];

    constructor(private dashboardService:ChitmasterGroupDashboardService,
        private router:Router,private route:ActivatedRoute){ }

    ngOnInit() {
        console.log("dash board component");
        this.dashboardService.getChitGroups().subscribe(
            groupListResponse => this.chitGroupsList = groupListResponse
        );
    }
    callGroupComp(){
        console.log("call group function called");
        this.router.navigate(["creategroup"]);
    }

    onClick(group:any) {
        this.router.navigate(["dashboard", group.chitGroupId]);
    }

}