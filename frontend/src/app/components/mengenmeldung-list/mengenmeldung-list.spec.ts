import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MengenmeldungList } from './mengenmeldung-list';

describe('MengenmeldungList', () => {
  let component: MengenmeldungList;
  let fixture: ComponentFixture<MengenmeldungList>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MengenmeldungList]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MengenmeldungList);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
