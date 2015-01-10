DROP DATABASE IF EXISTS oop_db;

CREATE DATABASE oop_db;

USE oop_db;

CREATE TABLE corso (
  id         INT          NOT NULL PRIMARY KEY AUTO_INCREMENT,
  nome       VARCHAR(100) NOT NULL,
  livello    INT          NOT NULL,
  totale_cfu INT
);

CREATE TABLE utente (
  matricola INT         NOT NULL PRIMARY KEY,
  nome      VARCHAR(60) NOT NULL,
  cognome   VARCHAR(50) NOT NULL,
  email     VARCHAR(50) NOT NULL,
  corso     INT         NOT NULL,
  FOREIGN KEY (corso) REFERENCES corso (id)
);

CREATE TABLE docente (
  id      INT         NOT NULL PRIMARY KEY AUTO_INCREMENT,
  nome    VARCHAR(60) NOT NULL,
  cognome VARCHAR(50) NOT NULL,
  email   VARCHAR(50)
);

CREATE TABLE insegnamento (
  id        INT          NOT NULL PRIMARY KEY AUTO_INCREMENT,
  nome      VARCHAR(100) NOT NULL,
  cfu       INT          NOT NULL,
  anno      INT          NOT NULL,
  semestre  INT          NOT NULL,
  opzionale BOOLEAN                           DEFAULT FALSE,
  corso     INT                               DEFAULT NULL,
  docente   INT                               DEFAULT NULL,
  FOREIGN KEY (corso) REFERENCES corso (id)
    ON DELETE CASCADE,
  FOREIGN KEY (docente) REFERENCES docente (id)
    ON DELETE SET NULL
);

CREATE TABLE insegnamento_utente (
  id           INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  utente       INT,
  insegnamento INT NOT NULL,
  voto         INT,
  lode         BOOLEAN                  DEFAULT FALSE,
  data         DATE,
  FOREIGN KEY (utente) REFERENCES utente (matricola)
    ON UPDATE CASCADE
    ON DELETE CASCADE,
  FOREIGN KEY (insegnamento) REFERENCES insegnamento (id)
);

CREATE TABLE ciclo (
  id     INT          NOT NULL PRIMARY KEY AUTO_INCREMENT,
  label  VARCHAR(250) NOT NULL,
  inizio DATE         NOT NULL,
  fine   DATE         NOT NULL,
  utente INT,
  FOREIGN KEY (utente) REFERENCES utente (matricola)
    ON UPDATE CASCADE
    ON DELETE CASCADE
);

CREATE TABLE attivita (
  id                      INT                                                              NOT NULL PRIMARY KEY AUTO_INCREMENT,
  ora_inizio              TIME                                                             NOT NULL,
  ora_fine                TIME                                                             NOT NULL,
  luogo                   VARCHAR(150)                                                     NOT NULL,
  categoria               ENUM('lezione', 'laboratorio', 'progetto', 'esame', 'seminario') NOT NULL,
  ripetizione_settimanale BOOLEAN                                                          NOT NULL,
  giorno                  INT,
  data                    DATE,
  tipologia_prova         ENUM('scritto', 'orale', 'laboratorio')                          NOT NULL,
  docente                 INT,
  ruolo_docente           ENUM('docente', 'assistente', 'tutor')                           NOT NULL,
  ciclo                   INT,
  insegnamento_utente     INT,
  FOREIGN KEY (docente) REFERENCES docente (id),
  FOREIGN KEY (ciclo) REFERENCES ciclo (id)
    ON DELETE CASCADE,
  FOREIGN KEY (insegnamento_utente) REFERENCES insegnamento_utente (id)
    ON DELETE CASCADE
);

CREATE TABLE iu_ciclo (
  ciclo               INT NOT NULL,
  insegnamento_utente INT NOT NULL,
  FOREIGN KEY (ciclo) REFERENCES ciclo (id)
    ON DELETE CASCADE,
  FOREIGN KEY (insegnamento_utente) REFERENCES insegnamento_utente (id)
    ON DELETE CASCADE,
  PRIMARY KEY (ciclo, insegnamento_utente)
);

CREATE TABLE ui_attivita_ciclo (
  ciclo               INT NOT NULL,
  insegnamento_utente INT NOT NULL,
  attivita            INT NOT NULL,
  FOREIGN KEY (ciclo) REFERENCES ciclo (id)
    ON DELETE CASCADE,
  FOREIGN KEY (insegnamento_utente) REFERENCES insegnamento_utente (id)
    ON DELETE CASCADE,
  FOREIGN KEY (attivita) REFERENCES attivita (id)
    ON DELETE CASCADE
);

CREATE TABLE tassa (
  id       INT           NOT NULL PRIMARY KEY AUTO_INCREMENT,
  importo  DECIMAL(6, 2) NOT NULL,
  scadenza DATE          NOT NULL,
  pagata   BOOLEAN                            DEFAULT FALSE,
  anno_accademico INT NOT NULL,
  utente   INT           NOT NULL,
  FOREIGN KEY (utente) REFERENCES utente (matricola)
    ON UPDATE CASCADE
    ON DELETE CASCADE
);
