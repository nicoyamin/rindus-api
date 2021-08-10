import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Comment } from '../model/comment';
import { CommentService } from '../service/comment-service.service';

@Component({
  selector: 'app-comment-delete-form',
  templateUrl: './comment-delete-form.component.html',
  styleUrls: ['./comment-delete-form.component.css']
})
export class CommentDeleteFormComponent {

  comment: Comment;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private commentService: CommentService) {
     this.comment = new Comment();
     }

  onSubmit() {
    this.commentService.delete(this.comment).subscribe(result => this.gotoCommentList());
  }

  gotoCommentList() {
    this.router.navigate(['/getcomments']);
  }


}
