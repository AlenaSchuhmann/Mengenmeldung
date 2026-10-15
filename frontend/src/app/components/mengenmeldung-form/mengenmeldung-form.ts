
import { Component, EventEmitter, Output, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, ReactiveFormsModule, Validators, ValidatorFn, ValidationErrors } from '@angular/forms';

import { MengenmeldungService } from '../../services/mengenmeldung';
import { MengenmeldungRequest } from '../../models/mengenmeldung.model';
import { HttpErrorResponse } from '@angular/common/http';

const maxNachkommastellenValidator: ValidatorFn = (
  control
): ValidationErrors | null => {
  const value = control.value;

  if (value === null || value === undefined || value === '') {
    return null;
  }

  const einheit = control.parent?.get('einheit')?.value;
  const maxNachkommastellen = einheit === 1 ? 3 : einheit === 3 ? 6 : 0;

  const nachkommastellen = String(value).split('.')[1]?.length ?? 0;

  return nachkommastellen > maxNachkommastellen
    ? {
        maxNachkommastellen: {
          erlaubt: maxNachkommastellen,
          eingegeben: nachkommastellen
        }
      }
    : null;
};

@Component({
  selector: 'app-mengenmeldung-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './mengenmeldung-form.html',
  styleUrl: './mengenmeldung-form.scss',
})
export class MengenmeldungFormComponent {
  private readonly fb = inject(FormBuilder);
  private readonly service = inject(MengenmeldungService);

  @Output() saved = new EventEmitter<void>();

  loading = false;
  successMessage = '';
  errorMessage = '';

  constructor() {
  this.form.controls.einheit.valueChanges.subscribe(() => {
    this.form.controls.menge.updateValueAndValidity();
  });
}

  readonly form = this.fb.nonNullable.group({
    menge: [0, [Validators.required, Validators.min(0.01), maxNachkommastellenValidator]
],
    einheit: [1, [Validators.required, Validators.pattern(/^(1|3)$/)]],
    jahr: [
      new Date().getFullYear(),
      [Validators.required, Validators.min(2000), Validators.max(2100)],
    ],
    monat: [
      new Date().getMonth() + 1,
      [Validators.required, Validators.min(1), Validators.max(12)],
    ],
    geraeteartNummer: [0, [Validators.required, Validators.min(1)]],
    registrierungsnummer: [0, [Validators.required, Validators.min(1)]],
    wahrheitserklaerung: [false, Validators.requiredTrue],
  });

  submit(): void {
    this.successMessage = '';
    this.errorMessage = '';

    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    const request = this.form.getRawValue() as MengenmeldungRequest;

    this.loading = true;

    this.service.submit(request).subscribe({
      next: () => {
        this.loading = false;
        this.successMessage = 'Die Mengenmeldung wurde erfolgreich abgegeben.';

        this.form.reset({
          menge: 0,
          einheit: 1,
          jahr: new Date().getFullYear(),
          monat: new Date().getMonth() + 1,
          geraeteartNummer: 0,
          registrierungsnummer: 0,
          wahrheitserklaerung: false,
        });

        this.saved.emit();
      },
      error: (error: HttpErrorResponse) => {
        this.loading = false;
        this.errorMessage =
          error?.error?.message ??
          'Die Mengenmeldung konnte nicht abgegeben werden. Bitte Backend-Verbindung prüfen.';
      },
    });
  }

}
