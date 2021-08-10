import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Photo } from '../model/photo';
import { PhotoService } from '../service/photo-service.service';

@Component({
  selector: 'app-photo-delete-form',
  templateUrl: './photo-delete-form.component.html',
  styleUrls: ['./photo-delete-form.component.css']
})
export class PhotoDeleteFormComponent {

  photo: Photo;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private photoService: PhotoService) {
     this.photo = new Photo();
     }

  onSubmit() {
    this.photoService.delete(this.photo).subscribe(result => this.gotoPhotoList());
    //this.postService.save(this.post).subscribe(result => console.log(result));

  }

  gotoPhotoList() {
    this.router.navigate(['/getphotos']);
  }

}
