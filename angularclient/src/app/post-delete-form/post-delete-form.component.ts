import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Post } from '../model/post';
import { PostService } from '../service/post-service.service';

@Component({
  selector: 'app-post-delete-form',
  templateUrl: './post-delete-form.component.html',
  styleUrls: ['./post-delete-form.component.css']
})
export class PostDeleteFormComponent {

  post: Post;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private postService: PostService) {
     this.post = new Post();
     }

  onSubmit() {
    this.postService.delete(this.post).subscribe(result => this.gotoPostList());
    //this.postService.save(this.post).subscribe(result => console.log(result));

  }

  gotoPostList() {
    this.router.navigate(['/getposts']);
  }
}
