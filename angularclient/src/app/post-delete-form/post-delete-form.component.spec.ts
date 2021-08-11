import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PostDeleteFormComponent } from './post-delete-form.component';

describe('PostDeleteFormComponent', () => {
  let component: PostDeleteFormComponent;
  let fixture: ComponentFixture<PostDeleteFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PostDeleteFormComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PostDeleteFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
