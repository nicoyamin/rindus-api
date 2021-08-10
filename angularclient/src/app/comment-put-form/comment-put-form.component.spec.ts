import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CommentPutFormComponent } from './comment-put-form.component';

describe('CommentPutFormComponent', () => {
  let component: CommentPutFormComponent;
  let fixture: ComponentFixture<CommentPutFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CommentPutFormComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CommentPutFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
