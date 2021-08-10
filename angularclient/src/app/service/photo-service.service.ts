import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Photo } from '../model/photo';
import { Observable } from 'rxjs';

@Injectable()
export class PhotoService {
  private photosUrl: string;

  constructor(private http: HttpClient) {
    this.photosUrl = 'http://localhost:8080/photos';
  }

  public findAll(): Observable<Photo[]> {
    return this.http.get<Photo[]>(this.photosUrl);
  }

  public save(photo: Photo) {
    return this.http.post<Photo>(this.photosUrl, photo);
  }

    public put(photo: Photo) {
      return this.http.put<Photo>(this.photosUrl, photo);

    }

    public patch(photo: Photo) {
      return this.http.patch<Photo>(this.photosUrl, photo);
    }

    public delete(photo: Photo) {
      return this.http.delete<Photo>(this.photosUrl+"?photoId="+photo.id);
    }
}
