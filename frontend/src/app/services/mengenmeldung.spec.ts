import { TestBed } from '@angular/core/testing';

import { Mengenmeldung } from './mengenmeldung';

describe('Mengenmeldung', () => {
  let service: Mengenmeldung;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(Mengenmeldung);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
