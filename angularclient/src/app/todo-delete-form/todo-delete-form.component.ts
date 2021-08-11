import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Todo } from '../model/todo';
import { TodoService } from '../service/todo-service.service';
import { ResponseService } from '../service/response-service.service';
import { ResponseContentComponent } from '../response-content/response-content.component';

@Component({
  selector: 'app-todo-delete-form',
  templateUrl: './todo-delete-form.component.html',
  styleUrls: ['./todo-delete-form.component.css']
})
export class TodoDeleteFormComponent {

  todo: Todo;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private todoService: TodoService,
        private responseService: ResponseService,
        private responseContent: ResponseContentComponent) {
     this.todo = new Todo();
     }

  setResponseData(message: string, statusCode: number, body: string){
    this.responseService.message = message;
    this.responseService.statusCode = statusCode;
    this.responseService.body = body;
  }

  onSubmit() {
    this.todoService.delete(this.todo).subscribe(result => {
          this.setResponseData("Todo was deleted successfully",200,JSON.stringify(result));
          this.router.navigate(['/displayresponse']);
    });
  }

  gotoTodoList() {
    this.router.navigate(['/gettodos']);
  }
}
