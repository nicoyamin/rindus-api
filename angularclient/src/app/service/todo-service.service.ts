import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Todo } from '../model/todo';
import { Observable } from 'rxjs';

@Injectable()
export class TodoService {
  private todosUrl: string;

  constructor(private http: HttpClient) {
    this.todosUrl = 'http://localhost:8080/todos';
  }

  public findAll(): Observable<Todo[]> {
    return this.http.get<Todo[]>(this.todosUrl);
  }

  public save(todo: Todo) {
    return this.http.post<Todo>(this.todosUrl, todo);
  }
}
