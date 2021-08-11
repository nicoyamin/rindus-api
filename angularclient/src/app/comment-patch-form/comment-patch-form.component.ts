import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Comment } from '../model/comment';
import { CommentService } from '../service/comment-service.service';
import { ResponseService } from '../service/response-service.service';
import { ResponseContentComponent } from '../response-content/response-content.component';

@Component({
  selector: 'app-comment-patch-form',
  templateUrl: './comment-patch-form.component.html',
  styleUrls: ['./comment-patch-form.component.css']
})
export class CommentPatchFormComponent {

  comment: Comment;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private commentService: CommentService,
        private responseService: ResponseService,
        private responseContent: ResponseContentComponent) {
     this.comment = new Comment();
     }

  setResponseData(message: string, statusCode: number, body: string){
    this.responseService.message = message;
    this.responseService.statusCode = statusCode;
    this.responseService.body = body;
  }

  onSubmit() {
    this.commentService.patch(this.comment).subscribe(result => {
          this.setResponseData("Comment was patched successfully",200,JSON.stringify(result));
          this.router.navigate(['/displayresponse']);
    });
  }

  gotoCommentList() {
    this.router.navigate(['/getcomments']);
  }

}
