import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TodoPutFormComponent } from './todo-put-form.component';

describe('TodoPutFormComponent', () => {
  let component: TodoPutFormComponent;
  let fixture: ComponentFixture<TodoPutFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TodoPutFormComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TodoPutFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
