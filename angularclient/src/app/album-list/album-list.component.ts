import { Component, OnInit } from '@angular/core';
import { Album } from '../model/album';
import { AlbumService } from '../service/album-service.service';

@Component({
  selector: 'app-album-list',
  templateUrl: './album-list.component.html',
  styleUrls: ['./album-list.component.css']
})
export class AlbumListComponent implements OnInit {

  albums: Album[];

  constructor(private albumService: AlbumService) {
   }

  ngOnInit() {
    this.albumService.findAll().subscribe(data => {
      this.albums= data;
    })
  }

}
