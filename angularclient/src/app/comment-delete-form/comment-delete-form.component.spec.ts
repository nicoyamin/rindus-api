import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CommentDeleteFormComponent } from './comment-delete-form.component';

describe('CommentDeleteFormComponent', () => {
  let component: CommentDeleteFormComponent;
  let fixture: ComponentFixture<CommentDeleteFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CommentDeleteFormComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CommentDeleteFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
