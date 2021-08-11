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
        let extractJson = ((document.getElementById("extractJson") as HTMLInputElement).value);
        let extractXml = ((document.getElementById("extractXml") as HTMLInputElement).value);
        let getCommentsUrl = this.commentsUrl+"?extractJson="+extractJson+"&extractXml="+extractXml
        console.log(getCommentsUrl);
        return this.http.get<Comment[]>(getCommentsUrl);
  }

  public save(comment: Comment) {
    return this.http.post<Comment>(this.commentsUrl, comment);
  }

    public put(comment: Comment) {
      return this.http.put<Comment>(this.commentsUrl, comment);

    }

    public patch(comment: Comment) {
      return this.http.patch<Comment>(this.commentsUrl, comment);
    }

    public delete(comment: Comment) {
      return this.http.delete<Comment>(this.commentsUrl+"?commentId="+comment.id);
    }
}
