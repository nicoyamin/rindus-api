import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable()
export class ResponseService {

  message: string;
  statusCode: number;
  body: string;

  constructor() { }
}
