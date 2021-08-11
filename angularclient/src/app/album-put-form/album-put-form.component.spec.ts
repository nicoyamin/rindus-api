import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AlbumPutFormComponent } from './album-put-form.component';

describe('AlbumPutFormComponent', () => {
  let component: AlbumPutFormComponent;
  let fixture: ComponentFixture<AlbumPutFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AlbumPutFormComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AlbumPutFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
