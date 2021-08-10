import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PhotoPutFormComponent } from './photo-put-form.component';

describe('PhotoPutFormComponent', () => {
  let component: PhotoPutFormComponent;
  let fixture: ComponentFixture<PhotoPutFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PhotoPutFormComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PhotoPutFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
