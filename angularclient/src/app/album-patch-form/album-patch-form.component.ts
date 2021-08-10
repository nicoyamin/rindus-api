import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Album } from '../model/album';
import { AlbumService } from '../service/album-service.service';

@Component({
  selector: 'app-album-patch-form',
  templateUrl: './album-patch-form.component.html',
  styleUrls: ['./album-patch-form.component.css']
})
export class AlbumPatchFormComponent {
  album: Album;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private albumService: AlbumService) {
     this.album = new Album();
     }

  onSubmit() {
    this.albumService.patch(this.album).subscribe(result => this.gotoAlbumList());
    //this.postService.save(this.post).subscribe(result => console.log(result));

  }

  gotoAlbumList() {
    this.router.navigate(['/getalbums']);
  }
}
