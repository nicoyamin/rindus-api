import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Album } from '../model/album';
import { AlbumService } from '../service/album-service.service';
import { ResponseService } from '../service/response-service.service';
import { ResponseContentComponent } from '../response-content/response-content.component';

@Component({
  selector: 'app-album-delete-form',
  templateUrl: './album-delete-form.component.html',
  styleUrls: ['./album-delete-form.component.css']
})
export class AlbumDeleteFormComponent {

  album: Album;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private albumService: AlbumService,
        private responseService: ResponseService,
        private responseContent: ResponseContentComponent) {
     this.album = new Album();
     }

    setResponseData(message: string, statusCode: number, body: string){
      this.responseService.message = message;
      this.responseService.statusCode = statusCode;
      this.responseService.body = body;
    }

  onSubmit() {
    this.albumService.delete(this.album).subscribe(result => {
          this.setResponseData("Album was deleted successfully",200,JSON.stringify(result));
          this.router.navigate(['/displayresponse']);
    });

  }

  gotoAlbumList() {
    this.router.navigate(['/getalbums']);
  }
}
