import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Photo } from '../model/photo';
import { PhotoService } from '../service/photo-service.service';

@Component({
  selector: 'app-photo-form',
  templateUrl: './photo-form.component.html',
  styleUrls: ['./photo-form.component.css']
})
export class PhotoFormComponent {

  photo: Photo;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private photoService: PhotoService) {
     this.photo = new Photo();
     }

  onSubmit() {
    this.photoService.save(this.photo).subscribe(result => this.gotoPhotoList());
    //this.postService.save(this.post).subscribe(result => console.log(result));

  }

  gotoPhotoList() {
    this.router.navigate(['/getphotos']);
  }


}
