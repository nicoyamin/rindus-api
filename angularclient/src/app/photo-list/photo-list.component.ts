import { Component, OnInit } from '@angular/core';
import { Photo } from '../model/photo';
import { PhotoService } from '../service/photo-service.service';

@Component({
  selector: 'app-photo-list',
  templateUrl: './photo-list.component.html',
  styleUrls: ['./photo-list.component.css']
})
export class PhotoListComponent implements OnInit {

  photos: Photo[];

  constructor(private photoService: PhotoService) {
   }

  ngOnInit() {
    this.photoService.findAll().subscribe(data => {
      this.photos = data;
    })
  }

}
