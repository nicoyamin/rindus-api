import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PostListComponent } from './post-list/post-list.component';
import { PostFormComponent } from './post-form/post-form.component';
import { CommentListComponent } from './comment-list/comment-list.component';
import { CommentFormComponent } from './comment-form/comment-form.component';
import { UserListComponent } from './user-list/user-list.component';
import { UserFormComponent } from './user-form/user-form.component';
import { TodoListComponent } from './todo-list/todo-list.component';
import { TodoFormComponent } from './todo-form/todo-form.component';
import { PhotoListComponent } from './photo-list/photo-list.component';
import { PhotoFormComponent } from './photo-form/photo-form.component';
import { AlbumListComponent } from './album-list/album-list.component';
import { AlbumFormComponent } from './album-form/album-form.component';

const routes: Routes = [
  { path: 'getposts', component: PostListComponent },
  { path: 'addposts', component: PostFormComponent },
  { path: 'getcomments', component: CommentListComponent },
  { path: 'addcomments', component: CommentFormComponent },
  { path: 'getusers', component: UserListComponent },
  { path: 'addusers', component: UserFormComponent },
  { path: 'gettodos', component: TodoListComponent },
  { path: 'addtodos', component: TodoFormComponent },
  { path: 'getphotos', component: PhotoListComponent },
  { path: 'addphotos', component: PhotoFormComponent },
  { path: 'getalbums', component: AlbumListComponent },
  { path: 'addalbums', component: AlbumFormComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
