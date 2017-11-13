import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { ChitmasterGroupDetailsService } from '../../chitmasterServices/ChitmasterGroupDetailsService';


@Component({
    selector:'group-detail',
    templateUrl:'/app/chitmasterHtmls/chitmasterGroupDetail.html',
    providers : [ChitmasterGroupDetailsService]
  })

export class ChitmasterGroupDetailComponent implements OnInit {

    public groupDetail:any;
    
    constructor(private groupDetailService:ChitmasterGroupDetailsService,
        private router:Router,private route:ActivatedRoute){ }

    ngOnInit() {
        let id = this.route.snapshot.params['chitGroupId'];
        this.groupDetailService.getChitGroupDetail(id).subscribe(
            groupDetailsResponse => this.groupDetail = groupDetailsResponse
        );
    }

    onClick(group:any) {
        this.router.navigate(["biddinghome", group.chitGroupId]);
    }

}