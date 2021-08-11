import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Photo } from '../model/photo';
import { PhotoService } from '../service/photo-service.service';
import { ResponseService } from '../service/response-service.service';
import { ResponseContentComponent } from '../response-content/response-content.component';

@Component({
  selector: 'app-photo-put-form',
  templateUrl: './photo-put-form.component.html',
  styleUrls: ['./photo-put-form.component.css']
})
export class PhotoPutFormComponent {

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
    this.photoService.put(this.photo).subscribe(result => {
          this.setResponseData("Photo was updated successfully",200,JSON.stringify(result));
          this.router.navigate(['/displayresponse']);
    });

  }

  gotoPhotoList() {
    this.router.navigate(['/getphotos']);
  }


}
