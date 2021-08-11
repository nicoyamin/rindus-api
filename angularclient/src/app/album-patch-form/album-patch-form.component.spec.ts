import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AlbumPatchFormComponent } from './album-patch-form.component';

describe('AlbumPatchFormComponent', () => {
  let component: AlbumPatchFormComponent;
  let fixture: ComponentFixture<AlbumPatchFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AlbumPatchFormComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AlbumPatchFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
