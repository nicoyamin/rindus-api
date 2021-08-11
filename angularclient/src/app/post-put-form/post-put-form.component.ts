import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Post } from '../model/post';
import { PostService } from '../service/post-service.service';
import { ResponseService } from '../service/response-service.service';
import { ResponseContentComponent } from '../response-content/response-content.component';

@Component({
  selector: 'app-post-put-form',
  templateUrl: './post-put-form.component.html',
  styleUrls: ['./post-put-form.component.css']
})
export class PostPutFormComponent {
  post: Post;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private postService: PostService,
        private responseService: ResponseService,
        private responseContent: ResponseContentComponent) {
     this.post = new Post();
     }

  setResponseData(message: string, statusCode: number, body: string){
    this.responseService.message = message;
    this.responseService.statusCode = statusCode;
    this.responseService.body = body;
  }

  onSubmit() {
    this.postService.put(this.post).subscribe(result => {
          this.setResponseData("Post was updated successfully",200,JSON.stringify(result));
          this.router.navigate(['/displayresponse']);
    });
  }

  gotoPostList() {
    this.router.navigate(['/getposts']);
  }
}
