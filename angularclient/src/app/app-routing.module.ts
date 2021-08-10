import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CommentService } from './service/comment-service.service';
import { PostService } from './service/post-service.service';
import { UserService } from './service/user-service.service';
import { TodoService } from './service/todo-service.service';
import { PhotoService } from './service/photo-service.service';
import { AlbumService } from './service/album-service.service';
import { ResponseService } from './service/response-service.service';


import { PostListComponent } from './post-list/post-list.component';
import { CommentListComponent } from './comment-list/comment-list.component';
import { UserListComponent } from './user-list/user-list.component';
import { TodoListComponent } from './todo-list/todo-list.component';
import { PhotoListComponent } from './photo-list/photo-list.component';
import { AlbumListComponent } from './album-list/album-list.component';

import { PostFormComponent } from './post-form/post-form.component';
import { CommentFormComponent } from './comment-form/comment-form.component';
import { UserFormComponent } from './user-form/user-form.component';
import { PhotoFormComponent } from './photo-form/photo-form.component';
import { AlbumFormComponent } from './album-form/album-form.component';
import { TodoFormComponent } from './todo-form/todo-form.component';

import { UserPutFormComponent } from './user-put-form/user-put-form.component';
import { TodoPutFormComponent } from './todo-put-form/todo-put-form.component';
import { PostPutFormComponent } from './post-put-form/post-put-form.component';
import { PhotoPutFormComponent } from './photo-put-form/photo-put-form.component';
import { CommentPutFormComponent } from './comment-put-form/comment-put-form.component';
import { AlbumPutFormComponent } from './album-put-form/album-put-form.component';

import { UserPatchFormComponent } from './user-patch-form/user-patch-form.component';
import { TodoPatchFormComponent } from './todo-patch-form/todo-patch-form.component';
import { PhotoPatchFormComponent } from './photo-patch-form/photo-patch-form.component';
import { PostPatchFormComponent } from './post-patch-form/post-patch-form.component';
import { CommentPatchFormComponent } from './comment-patch-form/comment-patch-form.component';
import { AlbumPatchFormComponent } from './album-patch-form/album-patch-form.component';

import { UserDeleteFormComponent } from './user-delete-form/user-delete-form.component';
import { TodoDeleteFormComponent } from './todo-delete-form/todo-delete-form.component';
import { PostDeleteFormComponent } from './post-delete-form/post-delete-form.component';
import { PhotoDeleteFormComponent } from './photo-delete-form/photo-delete-form.component';
import { CommentDeleteFormComponent } from './comment-delete-form/comment-delete-form.component';
import { AlbumDeleteFormComponent } from './album-delete-form/album-delete-form.component';

import { ResponseContentComponent } from './response-content/response-content.component';


const routes: Routes = [
  { path: 'getposts', component: PostListComponent },
  { path: 'addposts', component: PostFormComponent },
  { path: 'putposts', component: PostPutFormComponent },
  { path: 'patchposts', component: PostPatchFormComponent },
  { path: 'deleteposts', component: PostDeleteFormComponent },
  { path: 'getcomments', component: CommentListComponent },
  { path: 'addcomments', component: CommentFormComponent },
  { path: 'putcomments', component: CommentPutFormComponent },
  { path: 'patchcomments', component: CommentPatchFormComponent },
  { path: 'deletecomments', component: CommentDeleteFormComponent },
  { path: 'getusers', component: UserListComponent },
  { path: 'addusers', component: UserFormComponent },
  { path: 'putusers', component: UserPutFormComponent },
  { path: 'patchusers', component: UserPatchFormComponent },
  { path: 'deleteusers', component: UserDeleteFormComponent },
  { path: 'gettodos', component: TodoListComponent },
  { path: 'addtodos', component: TodoFormComponent },
  { path: 'puttodos', component: TodoPutFormComponent },
  { path: 'patchtodos', component: TodoPatchFormComponent },
  { path: 'deletetodos', component: TodoDeleteFormComponent },
  { path: 'getphotos', component: PhotoListComponent },
  { path: 'addphotos', component: PhotoFormComponent },
  { path: 'putphotos', component: PhotoPutFormComponent },
  { path: 'patchphotos', component: PhotoPatchFormComponent },
  { path: 'deletephotos', component: PhotoDeleteFormComponent },
  { path: 'getalbums', component: AlbumListComponent },
  { path: 'addalbums', component: AlbumFormComponent },
  { path: 'putalbums', component: AlbumPutFormComponent },
  { path: 'patchalbums', component: AlbumPatchFormComponent },
  { path: 'deletealbums', component: AlbumDeleteFormComponent },
  { path: 'displayresponse', component: ResponseContentComponent }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
