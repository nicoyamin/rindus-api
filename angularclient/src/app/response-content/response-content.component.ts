import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ResponseService } from '../service/response-service.service';


@Component({
  selector: 'app-response-content',
  templateUrl: './response-content.component.html',
  styleUrls: ['./response-content.component.css']
})
export class ResponseContentComponent implements OnInit{

  message: string;
  statusCode: number;
  body: string;

  constructor(private route: ActivatedRoute,
                  private router: Router,
                  private responseService: ResponseService) {
  }

  getMessage():string {
    return this.responseService.message;
  }

  getStatusCode():number {
    return this.responseService.statusCode;
  }
  getBody():string {
   return this.responseService.body;
  }


  ngOnInit() {
    this.message = this.getMessage();
    this.statusCode = this.getStatusCode();
    this.body = this.getBody();
  }


}
