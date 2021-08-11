import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { User } from '../model/user';
import { Observable } from 'rxjs';

@Injectable()
export class UserService {
  private usersUrl: string;

  constructor(private http: HttpClient) {
    this.usersUrl = 'http://localhost:8080/users';
  }

  public findAll(): Observable<User[]> {
    let extractJson = ((document.getElementById("extractJson") as HTMLInputElement).value);
    let extractXml = ((document.getElementById("extractXml") as HTMLInputElement).value);
    let getUsersUrl = this.usersUrl+"?extractJson="+extractJson+"&extractXml="+extractXml
    console.log(getUsersUrl);
    return this.http.get<User[]>(getUsersUrl);  }

  public save(user: User) {
    return this.http.post<User>(this.usersUrl, user);
  }

  public put(user: User) {
    return this.http.put<User>(this.usersUrl, user);

  }

  public patch(user: User) {
    return this.http.patch<User>(this.usersUrl, user);
  }

  public delete(user: User) {
    return this.http.delete<User>(this.usersUrl+"?userId="+user.id);
  }
}
