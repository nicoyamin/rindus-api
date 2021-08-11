import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AlbumDeleteFormComponent } from './album-delete-form.component';

describe('AlbumDeleteFormComponent', () => {
  let component: AlbumDeleteFormComponent;
  let fixture: ComponentFixture<AlbumDeleteFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AlbumDeleteFormComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AlbumDeleteFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
