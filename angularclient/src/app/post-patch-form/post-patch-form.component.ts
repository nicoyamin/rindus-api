import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Post } from '../model/post';
import { PostService } from '../service/post-service.service';

@Component({
  selector: 'app-post-patch-form',
  templateUrl: './post-patch-form.component.html',
  styleUrls: ['./post-patch-form.component.css']
})
export class PostPatchFormComponent {

  post: Post;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private postService: PostService) {
     this.post = new Post();
     }

  onSubmit() {
    this.postService.patch(this.post).subscribe(result => this.gotoPostList());
    //this.postService.save(this.post).subscribe(result => console.log(result));

  }

  gotoPostList() {
    this.router.navigate(['/getposts']);
  }

}
