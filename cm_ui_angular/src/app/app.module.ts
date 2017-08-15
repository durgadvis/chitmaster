import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpModule } from '@angular/http';
import { FormsModule ,ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { AppRoutingModule} from './app.routing.module'

import { AppComponent }  from './app.component';
import { NavBarComponent } from './chitmasterComponent/chitmasterNavBar/chitmaster-navbar.component';
import { routingComponents } from './app.routing.module';


@NgModule({
  imports:      [ BrowserModule, FormsModule, ReactiveFormsModule,HttpModule, AppRoutingModule ],
  
  declarations: [ AppComponent, NavBarComponent, routingComponents ],
  bootstrap:    [ AppComponent ]
})
export class AppModule { }
