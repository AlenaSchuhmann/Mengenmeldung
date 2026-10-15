import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MengenmeldungForm } from './mengenmeldung-form';

describe('MengenmeldungForm', () => {
  let component: MengenmeldungForm;
  let fixture: ComponentFixture<MengenmeldungForm>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MengenmeldungForm]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MengenmeldungForm);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
