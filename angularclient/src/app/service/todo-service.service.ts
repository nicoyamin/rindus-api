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
        let extractJson = ((document.getElementById("extractJson") as HTMLInputElement).value);
        let extractXml = ((document.getElementById("extractXml") as HTMLInputElement).value);
        let getTodosUrl = this.todosUrl+"?extractJson="+extractJson+"&extractXml="+extractXml
        console.log(getTodosUrl);
        return this.http.get<Todo[]>(getTodosUrl);
  }

  public save(todo: Todo) {
    return this.http.post<Todo>(this.todosUrl, todo);
  }

   public put(todo: Todo) {
      return this.http.put<Todo>(this.todosUrl, todo);

    }

    public patch(todo: Todo) {
      return this.http.patch<Todo>(this.todosUrl, todo);
    }

    public delete(todo: Todo) {
      return this.http.delete<Todo>(this.todosUrl+"?todoId="+todo.id);
    }
}
