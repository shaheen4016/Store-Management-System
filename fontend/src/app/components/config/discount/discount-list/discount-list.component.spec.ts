import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DiscountListComponent } from './discount-list.component';

describe('DiscountListComponent', () => {
  let component: DiscountListComponent;
  let fixture: ComponentFixture<DiscountListComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DiscountListComponent]
    });
    fixture = TestBed.createComponent(DiscountListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
