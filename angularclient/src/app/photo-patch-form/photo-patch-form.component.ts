import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Photo } from '../model/photo';
import { PhotoService } from '../service/photo-service.service';

@Component({
  selector: 'app-photo-patch-form',
  templateUrl: './photo-patch-form.component.html',
  styleUrls: ['./photo-patch-form.component.css']
})
export class PhotoPatchFormComponent {
  photo: Photo;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private photoService: PhotoService) {
     this.photo = new Photo();
     }

  onSubmit() {
    this.photoService.patch(this.photo).subscribe(result => this.gotoPhotoList());
    //this.postService.save(this.post).subscribe(result => console.log(result));

  }

  gotoPhotoList() {
    this.router.navigate(['/getphotos']);
  }


}
