import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TodoDeleteFormComponent } from './todo-delete-form.component';

describe('TodoDeleteFormComponent', () => {
  let component: TodoDeleteFormComponent;
  let fixture: ComponentFixture<TodoDeleteFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TodoDeleteFormComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TodoDeleteFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
