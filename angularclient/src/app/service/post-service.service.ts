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
        let extractJson = ((document.getElementById("extractJson") as HTMLInputElement).value);
        let extractXml = ((document.getElementById("extractXml") as HTMLInputElement).value);
        let getPostsUrl = this.postsUrl+"?extractJson="+extractJson+"&extractXml="+extractXml
        console.log(getPostsUrl);
        return this.http.get<Post[]>(getPostsUrl);
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
