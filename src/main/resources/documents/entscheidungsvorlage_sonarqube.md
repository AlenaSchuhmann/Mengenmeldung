# Entscheidungsvorlage: Einsatz von SonarQube

## 1. Zielsetzung

Ziel ist die Evaluation eines Einsatzes von **SonarQube zur automatisierten Sicherstellung der Code-Qualität** des geplanten Mengenmeldesystems.

## 2. Nutzen von SonarQube

SonarQube analysiert Quellcode statisch und erkennt unter anderem potenzielle Programmierfehler, Sicherheitsprobleme und Code-Smells. Die Analyse kann in die CI/CD-Pipeline integriert werden, sodass Probleme frühzeitig erkannt werden.

| Anforderung | Nutzen |
|---|---|
| Web-Frontend und Java-Backend | Einheitliche, automatisierte Code-Qualitätsprüfungen |
| REST- und SOAP-Schnittstellen | Erkennung bestimmter fehleranfälliger Programmiermuster |
| Persistierung und Fehlerbehandlung | Hinweise auf potenzielle Fehler und problematische Konstruktionen |
| Automatisierte Tests | Anzeige der Testabdeckung bei eingebundenen Coverage-Berichten |
| Hohe Last und Verfügbarkeit | Indirekte Unterstützung durch bessere Code-Qualität; Lasttests bleiben zusätzlich erforderlich |

SonarQube ersetzt weder automatisierte Tests noch Last-, Integrations- oder manuelle Architekturprüfungen.

## 3. Vor- und Nachteile

### Vorteile
- Frühzeitige Erkennung potenzieller Fehler und Sicherheitsprobleme.
- Einheitliche Qualitätsregeln für das Entwicklungsteam.
- Verbesserte Wartbarkeit und Transparenz durch Qualitätsberichte.
- Integration in Build- und CI/CD-Prozesse.
- Quality Gates können Mindestanforderungen an die Code-Qualität verbindlich machen.

### Nachteile
- Aufwand für Einrichtung, Konfiguration und laufenden Betrieb.
- Zusätzlicher Aufwand zur Bewertung und Behebung gefundener Probleme.
- Mögliche Fehlalarme und viele Befunde in bestehendem Code.
- Je nach benötigten Funktionen können Lizenzkosten entstehen.
- SonarQube erkennt nicht alle fachlichen Fehler oder Laufzeit- und Performanceprobleme.

## 4. Kosten und Aufwand

Zu berücksichtigen sind:
- **Software:** Auswahl einer passenden SonarQube-Edition abhängig vom Funktionsbedarf.
- **Infrastruktur:** Ressourcen für Installation und Betrieb.
- **Einrichtung:** Integration in die Build-Pipeline und Konfiguration der Qualitätsregeln.
- **Betrieb:** Updates, Wartung und regelmäßige Überprüfung der Regeln.

Der Einführung und dem Betrieb stehen mögliche Einsparungen durch frühere Fehlererkennung, geringeren Wartungsaufwand und verbesserte Code-Qualität gegenüber.

## 5. Empfehlung

**Empfehlung: SonarQube wird dauerhaft eingeführt.**

Die automatisierte Codeanalyse verbessert die Transparenz und unterstützt die frühzeitige Erkennung von Fehlern und Sicherheitsproblemen. Für ein System, das Mengenmeldungen zuverlässig entgegennehmen, speichern und weiterleiten muss, ist dies ein sinnvoller Bestandteil der Qualitätssicherung.
