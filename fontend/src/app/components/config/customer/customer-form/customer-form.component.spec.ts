import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomerFormComponent } from './customer-form.component';

describe('CustomerFormComponent', () => {
  let component: CustomerFormComponent;
  let fixture: ComponentFixture<CustomerFormComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CustomerFormComponent]
    });
    fixture = TestBed.createComponent(CustomerFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

function beforeEach(arg0: () => void) {
  throw new Error('Function not implemented.');
}

function expect(component: CustomerFormComponent) {
  throw new Error('Function not implemented.');
}
