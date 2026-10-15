import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MengenmeldungResponse } from '../../models/mengenmeldung.model';

@Component({
  selector: 'app-mengenmeldung-list',
  imports: [CommonModule],
  templateUrl: './mengenmeldung-list.html',
  styleUrl: './mengenmeldung-list.scss',
})
export class MengenmeldungList {
 @Input() meldungen: MengenmeldungResponse[] = [];

 getEinheitName(einheit: number): string {
    switch (einheit) {
      case 1:
        return 'Kilogramm';
      case 3:
        return 'Tonnen';
      default:
        return 'Unbekannte Einheit';
    }
  }
}
