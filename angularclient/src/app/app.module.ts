import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AppComponent } from './app.component';
import { PostListComponent } from './post-list/post-list.component';
import { PostFormComponent } from './post-form/post-form.component';
import { PostService } from './service/post-service.service';
import { CommentListComponent } from './comment-list/comment-list.component';
import { CommentFormComponent } from './comment-form/comment-form.component';
import { CommentService } from './service/comment-service.service';
import { UserListComponent } from './user-list/user-list.component';
import { AlbumListComponent } from './album-list/album-list.component';
import { PhotoListComponent } from './photo-list/photo-list.component';
import { TodoListComponent } from './todo-list/todo-list.component';
import { UserFormComponent } from './user-form/user-form.component';
import { PhotoFormComponent } from './photo-form/photo-form.component';
import { AlbumFormComponent } from './album-form/album-form.component';
import { TodoFormComponent } from './todo-form/todo-form.component';

@NgModule({
  declarations: [
    AppComponent,
    PostListComponent,
    PostFormComponent,
    CommentListComponent,
    CommentFormComponent,
    UserListComponent,
    AlbumListComponent,
    PhotoListComponent,
    TodoListComponent,
    UserFormComponent,
    PhotoFormComponent,
    AlbumFormComponent,
    TodoFormComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [
  PostService,
  CommentService,
  UserService,
  TodoService,
  PhotoService,
  AlbumService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
