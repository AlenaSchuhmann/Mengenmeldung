export interface MengenmeldungRequest {
  menge: number;
  einheit: number;
  jahr: number;
  monat: number;
  geraeteartNummer: number;
  registrierungsnummer: number;
  wahrheitserklaerung: boolean;
}

export interface MengenmeldungResponse {
  id: number;
  menge: number;
  einheit: number;
  jahr: number;
  monat: number;
  geraeteartNummer: number;
  registrierungsnummer: number;
  erstelltAm: string;
}
