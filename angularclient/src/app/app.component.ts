import { Component } from '@angular/core';
import {Router} from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title : string;
  verbSelect: any;
  httpVerbs : string[];
  httpActions : any[];
  resourceSelect: string;
  resources : string[];

  constructor(private route:Router){
    this.title = 'Rindus Coding Task - Angular Application';
    this.httpVerbs = ['GET', 'POST','PUT','PATCH','DELETE'];
    this.httpActions = [
    {verb:"GET", action:"/get"},
    {verb:"POST", action:"/add"},
    {verb:"PUT", action:"/put"},
    {verb:"PATCH", action:"/patch"},
    {verb:"DELETE", action:"/delete"}
    ];
    this.verbSelect = this.httpVerbs[0];
    this.resources = ['posts', 'comments','users','albums','photos','todos'];
    this.resourceSelect = this.resources[0];
  }

  changeResource(e) {
    let selectedAction = this.httpActions.find(x => x.verb === this.verbSelect)
    console.log(selectedAction.action + e.target.value);
    this.route.navigate([selectedAction.action + e.target.value]);
  }

   changeVerb(e) {
      let selectedAction = this.httpActions.find(x => x.verb === e.target.value)
      console.log(selectedAction.action + this.resourceSelect);
      this.route.navigate([selectedAction.action + this.resourceSelect]);
   }
}
