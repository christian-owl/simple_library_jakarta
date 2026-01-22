Un' applicazione web per la gestione di una biblioteca. L'app permette la gestione (CRUD) di autori e libri attraverso un'interfaccia web reattiva.

Backend: Jakarta EE 11 
Application Server: Payara Full Server
Database: PostgreSQL (gestito tramite Docker)

Avvio dei servizi di database:
Bash
docker-compose up
PostgreSQL: localhost:5432 (User: admin, DB: postgres)

L'applicazione espone i seguenti endpoint REST:

Autori
GET /autori - Recupera la lista di tutti gli autori.
GET /autori/{id:[0-9]+} - Recupera un autore tramite id.
GET /autori/cerca/{cognome} -Recupera un autore tramite cognome.
POST /autori - Registra un nuovo autore.
DELETE /autori/{id} - Rimuove un autore tramite il suo ID.
PUT /autori - Aggiorna info autore.

Libri
GET /libri - Recupera la lista di tutti i libri.
GET /libri/{id:[0-9]+} - Recupera un libro tramite id.
GET /libri/cerca/{nome} - Recupera una lista di libri a partire dal nome.
POST /libri - Crea un libro.
DELETE /libri/{id} - Rimuove un libro.
PUT /libri - Aggiorna info libro.
