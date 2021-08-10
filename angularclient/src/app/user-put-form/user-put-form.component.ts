import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from '../model/user';
import { UserService } from '../service/user-service.service';

@Component({
  selector: 'app-user-put-form',
  templateUrl: './user-put-form.component.html',
  styleUrls: ['./user-put-form.component.css']
})
export class UserPutFormComponent {

  user: User;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private userService: UserService) {
     this.user = new User();
     }

  onSubmit() {
    this.userService.put(this.user).subscribe(result => this.gotoUserList());
  }

  gotoUserList() {
    this.router.navigate(['/getusers']);
  }
}
