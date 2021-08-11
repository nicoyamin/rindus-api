import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserPatchFormComponent } from './user-patch-form.component';

describe('UserPatchFormComponent', () => {
  let component: UserPatchFormComponent;
  let fixture: ComponentFixture<UserPatchFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UserPatchFormComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UserPatchFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
