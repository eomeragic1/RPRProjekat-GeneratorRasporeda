BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS "korisnici" (
	"id"	INTEGER PRIMARY KEY AUTOINCREMENT,
	"korisnicko_ime"	TEXT UNIQUE,
	"lozinka"	TEXT,
	"tip"	INTEGER,
	"ime"	TEXT,
	"prezime"	TEXT,
	"jmbg"	TEXT UNIQUE
);
INSERT INTO "korisnici" VALUES (1,'admin','admin',0,NULL,NULL,NULL);
COMMIT;
