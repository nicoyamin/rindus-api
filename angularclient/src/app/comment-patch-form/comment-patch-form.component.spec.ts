import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CommentPatchFormComponent } from './comment-patch-form.component';

describe('CommentPatchFormComponent', () => {
  let component: CommentPatchFormComponent;
  let fixture: ComponentFixture<CommentPatchFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CommentPatchFormComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CommentPatchFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
