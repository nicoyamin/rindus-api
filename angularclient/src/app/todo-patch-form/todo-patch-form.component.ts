import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Todo } from '../model/todo';
import { TodoService } from '../service/todo-service.service';

@Component({
  selector: 'app-todo-patch-form',
  templateUrl: './todo-patch-form.component.html',
  styleUrls: ['./todo-patch-form.component.css']
})
export class TodoPatchFormComponent {

   todo: Todo;

   constructor(
     private route: ActivatedRoute,
     private router: Router,
     private todoService: TodoService) {
      this.todo = new Todo();
      }

   onSubmit() {
     this.todoService.patch(this.todo).subscribe(result => this.gotoTodoList());
     //this.postService.save(this.post).subscribe(result => console.log(result));

   }

   gotoTodoList() {
     this.router.navigate(['/gettodos']);
   }

}
