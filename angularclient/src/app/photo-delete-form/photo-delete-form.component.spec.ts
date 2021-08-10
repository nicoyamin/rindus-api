import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PhotoDeleteFormComponent } from './photo-delete-form.component';

describe('PhotoDeleteFormComponent', () => {
  let component: PhotoDeleteFormComponent;
  let fixture: ComponentFixture<PhotoDeleteFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PhotoDeleteFormComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PhotoDeleteFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
