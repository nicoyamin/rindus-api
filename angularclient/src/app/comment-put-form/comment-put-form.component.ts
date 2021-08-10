import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Comment } from '../model/comment';
import { CommentService } from '../service/comment-service.service';

@Component({
  selector: 'app-comment-put-form',
  templateUrl: './comment-put-form.component.html',
  styleUrls: ['./comment-put-form.component.css']
})
export class CommentPutFormComponent {

  comment: Comment;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private commentService: CommentService) {
     this.comment = new Comment();
     }

  onSubmit() {
    this.commentService.put(this.comment).subscribe(result => this.gotoCommentList());
  }

  gotoCommentList() {
    this.router.navigate(['/getcomments']);
  }


}
