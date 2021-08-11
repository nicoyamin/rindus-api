import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from '../model/user';
import { UserService } from '../service/user-service.service';
import { ResponseService } from '../service/response-service.service';
import { ResponseContentComponent } from '../response-content/response-content.component';

@Component({
  selector: 'app-user-patch-form',
  templateUrl: './user-patch-form.component.html',
  styleUrls: ['./user-patch-form.component.css']
})
export class UserPatchFormComponent {

  user: User;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private userService: UserService,
    private responseService: ResponseService,
    private responseContent: ResponseContentComponent) {
     this.user = new User();
     }

       setResponseData(message: string, statusCode: number, body: string){
         this.responseService.message = message;
         this.responseService.statusCode = statusCode;
         this.responseService.body = body;
       }


  onSubmit() {
    this.userService.patch(this.user).subscribe(result => {
      this.setResponseData("User was patched successfully",200,JSON.stringify(result));
      this.router.navigate(['/displayresponse']);
    });
  }

  gotoUserList() {
    this.router.navigate(['/getusers']);
  }


}
