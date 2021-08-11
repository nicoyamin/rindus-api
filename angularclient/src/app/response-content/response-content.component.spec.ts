import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ResponseContentComponent } from './response-content.component';

describe('ResponseContentComponent', () => {
  let component: ResponseContentComponent;
  let fixture: ComponentFixture<ResponseContentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ResponseContentComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ResponseContentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
