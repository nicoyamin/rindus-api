import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PhotoPatchFormComponent } from './photo-patch-form.component';

describe('PhotoPatchFormComponent', () => {
  let component: PhotoPatchFormComponent;
  let fixture: ComponentFixture<PhotoPatchFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PhotoPatchFormComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PhotoPatchFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
