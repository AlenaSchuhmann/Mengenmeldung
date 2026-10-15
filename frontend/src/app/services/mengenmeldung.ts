
import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import {
  MengenmeldungRequest,
  MengenmeldungResponse
} from '../models/mengenmeldung.model';

@Injectable({
  providedIn: 'root'
})
export class MengenmeldungService {
  private readonly http = inject(HttpClient);

  private readonly apiUrl = '/api/mengenmeldungen';

  getAll(): Observable<MengenmeldungResponse[]> {
    return this.http.get<MengenmeldungResponse[]>(this.apiUrl);
  }

  submit(
    request: MengenmeldungRequest
  ): Observable<MengenmeldungResponse> {
    return this.http.post<MengenmeldungResponse>(
      this.apiUrl,
      request
    );
  }
}
