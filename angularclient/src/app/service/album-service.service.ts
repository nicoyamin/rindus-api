import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Album } from '../model/album';
import { Observable } from 'rxjs';

@Injectable()
export class AlbumService{
  private albumsUrl: string;

  constructor(private http: HttpClient) {
    this.albumsUrl = 'http://localhost:8080/albums';
  }

  public findAll(): Observable<Album[]> {
    let extractJson = ((document.getElementById("extractJson") as HTMLInputElement).value);
    let extractXml = ((document.getElementById("extractXml") as HTMLInputElement).value);
    let getAlbumsUrl = this.albumsUrl+"?extractJson="+extractJson+"&extractXml="+extractXml
    console.log(getAlbumsUrl);
    return this.http.get<Album[]>(getAlbumsUrl);
  }

  public save(album: Album) {
    return this.http.post<Album>(this.albumsUrl, album);
  }

    public put(album: Album) {
      return this.http.put<Album>(this.albumsUrl, album);

    }

    public patch(album: Album) {
      return this.http.patch<Album>(this.albumsUrl, album);
    }

    public delete(album: Album) {
      return this.http.delete<Album>(this.albumsUrl+"?albumId="+album.id);
    }
}
