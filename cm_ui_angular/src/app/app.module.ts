import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpModule } from '@angular/http';
import { FormsModule ,ReactiveFormsModule } from '@angular/forms';

import { AppRoutingModule} from './app.routing.module'
import { AuthModule } from './auth.module'

import { AppComponent }  from './app.component';
import { NavBarComponent } from './chitmasterComponent/chitmasterNavBar/chitmaster-navbar.component';
import { routingComponents } from './app.routing.module';
import { KeysPipe } from './chitmasterComponent/chitmasterBidding/cm-pipe';


@NgModule({
  imports:      [ BrowserModule, FormsModule, ReactiveFormsModule,HttpModule, AppRoutingModule, AuthModule ],
  
  declarations: [ AppComponent, NavBarComponent, routingComponents, KeysPipe ],
  bootstrap:    [ AppComponent ]
})
export class AppModule { }
