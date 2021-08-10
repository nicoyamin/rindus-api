import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Post } from '../model/post';
import { Observable } from 'rxjs';

@Injectable()
export class PostService {
  private postsUrl: string;

  constructor(private http: HttpClient) {
    this.postsUrl = 'http://localhost:8080/posts';
  }

  public findAll(): Observable<Post[]> {
    return this.http.get<Post[]>(this.postsUrl);
  }

  public save(post: Post) {
    return this.http.post<Post>(this.postsUrl, post);
  }

    public put(post: Post) {
      return this.http.put<Post>(this.postsUrl, post);

    }

    public patch(post: Post) {
      return this.http.patch<Post>(this.postsUrl, post);
    }

    public delete(post: Post) {
      return this.http.delete<Post>(this.postsUrl+"?postId="+post.id);
    }
}
