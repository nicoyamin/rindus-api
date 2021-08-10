import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Todo } from '../model/todo';
import { TodoService } from '../service/todo-service.service';

@Component({
  selector: 'app-todo-put-form',
  templateUrl: './todo-put-form.component.html',
  styleUrls: ['./todo-put-form.component.css']
})
export class TodoPutFormComponent {
  todo: Todo;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private todoService: TodoService) {
     this.todo = new Todo();
     }

  onSubmit() {
    this.todoService.put(this.todo).subscribe(result => this.gotoTodoList());
    //this.postService.save(this.post).subscribe(result => console.log(result));

  }

  gotoTodoList() {
    this.router.navigate(['/gettodos']);
  }
}
