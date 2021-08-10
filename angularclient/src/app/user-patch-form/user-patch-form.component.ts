import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from '../model/user';
import { UserService } from '../service/user-service.service';

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
    private userService: UserService) {
     this.user = new User();
     }

  onSubmit() {
    this.userService.patch(this.user).subscribe(result => this.gotoUserList());
  }

  gotoUserList() {
    this.router.navigate(['/getusers']);
  }


}
