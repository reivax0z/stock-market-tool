import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RemoveCashComponent } from './remove-cash.component';

describe('RemoveCashComponent', () => {
  let component: RemoveCashComponent;
  let fixture: ComponentFixture<RemoveCashComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RemoveCashComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RemoveCashComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
