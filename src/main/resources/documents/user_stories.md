# Umsetzungsschritte

## 1. Aufsetzen des Prototyps

- Initiales Java- und Angular-Projekt ohne Fachlichkeit erstellen
- Entwicklungsumgebung und Repository einrichten
- Datenbank einrichten

## 2. UI für die Abgabe von Mengenmeldungen

- Formular und Button zur Abgabe einer Mengenmeldung implementieren
- Formularvalidierung implementieren
- Folgende Attribute im Formular abbilden:

  | Attribut | Datentyp / Eingabeelement | Beschreibung |
    |---|---|---|
  | `menge` | Decimal | Menge |
  | `einheit` | Enum `Masseinheit` | Mengeneinheit |
  | `jahr` | Integer (`YYYY`) | Jahr |
  | `monat` | Integer (`MM`) | Monat |
  | `geraeteartnummer` | Dropdown, Integer | Geräteart; Dropdown mit Gerätearten befüllen |
  | `registrierungsnummer` | Integer | Registrierungsnummer |
  | `wahrheitserklaerung` | Checkbox, verpflichtend | Bestätigung der Wahrheitserklärung |

- Aufruf des Backend-REST-Services zur Abgabe einer Mengenmeldung implementieren

## 3. UI zur Anzeige von Mengenmeldungen

- Anzeige und Layout implementieren
- Aufruf des Backend-REST-Services zum Laden der Mengenmeldungen implementieren
- Laden der Mengenmeldungen bei jedem Aufruf bzw. Laden der Seite sicherstellen

## 4. REST-Service zur Abgabe von Mengenmeldungen inklusive Persistierung

- `POST`-Endpunkt implementieren
- Validierungen implementieren, insbesondere:
    - Prüfen, ob die Registrierungsnummer existiert (`checkRegistrierungsnummer`)
- Mengenmeldung in der Datenbank speichern

## 5. REST-Service zum Laden aller Mengenmeldungen

- `GET`-Endpunkt zum Abrufen aller Mengenmeldungen implementieren

## 6. Aufruf des SOAP-Services bei Abgabe einer Mengenmeldung

- WSDL importieren
- Client implementieren
- Bestehenden SOAP-Service nach erfolgreicher Abgabe einer Mengenmeldung aufrufen

## 7. Aufruf des SOAP-Services `getGeraetearten`

- SOAP-Service des Mitteilungsservices aufrufen, um das Dropdown in Schritt 2 mit Gerätearten zu befüllen

## 8. Allgemeines Errorhandling

- Einheitliches Fehlerhandling für UI, REST-Services, Datenbankzugriffe und SOAP-Aufrufe implementieren
- Fehlerfälle behandeln und geeignete Fehlermeldungen bereitstellen

## 9. Aufruf des SOAP-Services `checkRegistrierungsnummer`

- SOAP-Service des Mitteilungsservices zur Prüfung der Registrierungsnummer aufrufen