import { Component, OnInit } from '@angular/core';
import { Comment } from '../model/comment';
import { CommentService } from '../service/comment-service.service';

@Component({
  selector: 'app-comment-list',
  templateUrl: './comment-list.component.html',
  styleUrls: ['./comment-list.component.css']
})
export class CommentListComponent implements OnInit {

  comments: Comment[];

  constructor(private commentService: CommentService) {
   }

  ngOnInit() {
    this.commentService.findAll().subscribe(data => {
      this.comments = data;
    })
  }

}
