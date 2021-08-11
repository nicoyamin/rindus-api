import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PostPutFormComponent } from './post-put-form.component';

describe('PostPutFormComponent', () => {
  let component: PostPutFormComponent;
  let fixture: ComponentFixture<PostPutFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PostPutFormComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PostPutFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
