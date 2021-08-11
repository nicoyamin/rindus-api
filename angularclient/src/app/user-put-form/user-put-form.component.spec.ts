import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserPutFormComponent } from './user-put-form.component';

describe('UserPutFormComponent', () => {
  let component: UserPutFormComponent;
  let fixture: ComponentFixture<UserPutFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UserPutFormComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UserPutFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
