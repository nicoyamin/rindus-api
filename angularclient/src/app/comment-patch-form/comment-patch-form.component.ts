import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Comment } from '../model/comment';
import { CommentService } from '../service/comment-service.service';

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
    private commentService: CommentService) {
     this.comment = new Comment();
     }

  onSubmit() {
    this.commentService.patch(this.comment).subscribe(result => this.gotoCommentList());
  }

  gotoCommentList() {
    this.router.navigate(['/getcomments']);
  }

}
