import { Component, inject, OnInit, signal } from '@angular/core';
import { MengenmeldungList } from './components/mengenmeldung-list/mengenmeldung-list';
import { MengenmeldungService } from './services/mengenmeldung';
import { MengenmeldungResponse } from './models/mengenmeldung.model';
import { MengenmeldungFormComponent } from './components/mengenmeldung-form/mengenmeldung-form';

@Component({
  selector: 'app-root',
  imports: [MengenmeldungList, MengenmeldungFormComponent],
  templateUrl: './app.html',
  styleUrl: './app.scss'
})
export class App implements OnInit {
  private readonly service = inject(MengenmeldungService);

  meldungen = signal<MengenmeldungResponse[]>([]);
  loading = signal(false);
  errorMessage = signal('');

  ngOnInit(): void {
    this.loadMeldungen();
  }

  loadMeldungen(): void {
    this.loading.set(true);
    this.errorMessage.set('');

    this.service.getAll().subscribe({
      next: (meldungen) => {
        this.meldungen.set(meldungen);
        this.loading.set(false);
      },
      error: (error) => {
        console.error('Fehler beim Laden der Mengenmeldungen:', error);

        this.errorMessage.set(
          'Mengenmeldungen konnten nicht geladen werden. Bitte Backend-Verbindung prüfen.'
        );
        this.loading.set(false);
      }
    });
  }
}