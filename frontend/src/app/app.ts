import { Component, inject, OnInit, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { MengenmeldungList } from './components/mengenmeldung-list/mengenmeldung-list';
import { MengenmeldungService } from './services/mengenmeldung';
import { MengenmeldungResponse } from './models/mengenmeldung.model';
import { MengenmeldungFormComponent } from './components/mengenmeldung-form/mengenmeldung-form';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, MengenmeldungList, MengenmeldungFormComponent],
  templateUrl: './app.html',
  styleUrl: './app.scss'
})
export class App implements OnInit {
  private readonly service = inject(MengenmeldungService);

  meldungen: MengenmeldungResponse[] = [];
  loading = false;
  errorMessage = '';

  ngOnInit(): void {
    this.loadMeldungen();
  }

  loadMeldungen(): void {
    this.loading = true;
    this.errorMessage = '';

    this.service.getAll().subscribe({
      next: (meldungen) => {
        this.meldungen = meldungen;
        this.loading = false;
      },
      error: () => {
        this.errorMessage =
          'Mengenmeldungen konnten nicht geladen werden. Bitte Backend-Verbindung prüfen.';
        this.loading = false;
      }
    });
  }

}
