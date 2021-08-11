import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Photo } from '../model/photo';
import { PhotoService } from '../service/photo-service.service';
import { ResponseService } from '../service/response-service.service';
import { ResponseContentComponent } from '../response-content/response-content.component';

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
    private photoService: PhotoService,
        private responseService: ResponseService,
        private responseContent: ResponseContentComponent) {
     this.photo = new Photo();
     }

  setResponseData(message: string, statusCode: number, body: string){
    this.responseService.message = message;
    this.responseService.statusCode = statusCode;
    this.responseService.body = body;
  }

  onSubmit() {
    this.photoService.patch(this.photo).subscribe(result => {
      this.setResponseData("Photo was patched successfully",200,JSON.stringify(result));
      this.router.navigate(['/displayresponse']);
    });

  }

  gotoPhotoList() {
    this.router.navigate(['/getphotos']);
  }


}
