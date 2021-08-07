import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Album } from '../model/album';
import { AlbumService } from '../service/album-service.service';

@Component({
  selector: 'app-album-form',
  templateUrl: './album-form.component.html',
  styleUrls: ['./album-form.component.css']
})
export class AlbumFormComponent {

  album: Album;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private albumService: AlbumService) {
     this.album = new Album();
     }

  onSubmit() {
    this.albumService.save(this.album).subscribe(result => this.gotoAlbumList());
    //this.postService.save(this.post).subscribe(result => console.log(result));

  }

  gotoAlbumList() {
    this.router.navigate(['/getalbums']);
  }

}
