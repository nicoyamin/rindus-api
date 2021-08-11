import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TodoPatchFormComponent } from './todo-patch-form.component';

describe('TodoPatchFormComponent', () => {
  let component: TodoPatchFormComponent;
  let fixture: ComponentFixture<TodoPatchFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TodoPatchFormComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TodoPatchFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
