import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Comment } from '../model/comment';
import { Observable } from 'rxjs';

@Injectable()
export class CommentService {

  private commentsUrl: string;

  constructor(private http: HttpClient) {
    this.commentsUrl = 'http://localhost:8080/comments';
  }

  public findAll(): Observable<Comment[]> {
    return this.http.get<Comment[]>(this.commentsUrl);
  }

  public save(comment: Comment) {
    return this.http.post<Comment>(this.commentsUrl, comment);
  }
}
