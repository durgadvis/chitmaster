import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { ChitmasterRegisterUserComponent} from './chitmasterComponent/chitmaster-register-user-form.component';
import { chitmasterHomeComponent} from './chitmasterComponent/chitmaster-home.component';
import { ChitmasterLoginComponent } from './chitmasterComponent/chitmaster-login-component'; 
import { ChitmasterDashBoardComponent } from './chitmasterComponent/chitmasterDashBoard/chitmaster-dashboard.component';
import { ChitmasterCreateGroupComponent } from './chitmasterComponent/chitmasterGroup/chitmaster-creategroup.component';

const routes:Routes = [
    {
    path:'home',
    component:chitmasterHomeComponent
    },{
      path:'register',
      component:ChitmasterRegisterUserComponent
    },{
      path:'login',
      component:ChitmasterLoginComponent
    },
    {
      path:'dashboard',
      component:ChitmasterDashBoardComponent
    },
    {
        path:'creategroup',
        component: ChitmasterCreateGroupComponent
    }
  ];

  @NgModule({
    imports:[RouterModule.forRoot(routes)],
    exports:[RouterModule]
  })    

  export class AppRoutingModule {}
  export const routingComponents = [ChitmasterRegisterUserComponent,chitmasterHomeComponent,ChitmasterLoginComponent,ChitmasterDashBoardComponent,ChitmasterCreateGroupComponent]

