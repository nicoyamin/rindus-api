import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PostPatchFormComponent } from './post-patch-form.component';

describe('PostPatchFormComponent', () => {
  let component: PostPatchFormComponent;
  let fixture: ComponentFixture<PostPatchFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PostPatchFormComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PostPatchFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
