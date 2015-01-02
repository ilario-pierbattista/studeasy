Drop database if exists oop_db;

Create database oop_db;

Use oop_db;

Create table corso(
	id int not null primary key auto_increment,
	nome varchar(100) not null,
	livello int not null,
	totale_cfu int
);

Create table utente(
	matricola int not null primary key,
	nome varchar(60) not null,
	cognome varchar(50) not null,
	email varchar(50) not null,
	corso int not null,
	foreign key (corso) references corso(id)
);

Create table docente(
	id int not null primary key auto_increment,
	nome varchar(60) not null,
	cognome varchar(50) not null,
	email varchar(50)
);

Create table insegnamento(
	id int not null primary key auto_increment,
	nome varchar(100) not null,
	cfu int not null,
	anno int not null,
	semestre int not null,
	opzionale boolean default false,
	corso int default null,
	docente int default null,
	foreign key (corso) references corso(id)
		on delete cascade,
	foreign key (docente) references docente(id)
		on delete set null
);

Create table insegnamento_utente(
	id int not null primary key auto_increment,
	utente int not null, 
	insegnamento int not null,
	voto int,
	lode boolean default false,
	data date,
	foreign key (utente) references utente(matricola),
	foreign key (insegnamento) references insegnamento(id)
);

Create table ciclo(
	id int not null primary key auto_increment,
	label varchar(250) not null,
	inizio date not null,
	fine date not null,
	utente int,
	foreign key (utente) references utente(matricola)
);

Create table attivita(
	id int not null primary key auto_increment,
	ora_inizio time not null,
	ora_fine time not null,
	luogo varchar(150) not null,
	categoria enum('lezione', 'laboratorio', 'progetto', 'esame', 'seminario', 'altro'),
	ripetizione_settimanale boolean not null,
	giorno enum('lun', 'mar', 'mer', 'gio', 'ven', 'sab'),
	data date,
	tipologia_prova enum('scritto', 'orale', 'laboratorio', 'altro') not null,
	docente int,
	ruolo_docente enum('docente', 'assistente', 'tutor') not null,
	ciclo int not null,
	insegnamento_utente int not null,
	foreign key (docente) references docente(id),
	foreign key (ciclo) references ciclo(id),
	foreign key (insegnamento_utente) references insegnamento_utente(id)
);

Create table iu_ciclo(
	ciclo int not null,
	insegnamento_utente int not null,
	foreign key (ciclo) references ciclo(id),
	foreign key (insegnamento_utente) references insegnamento_utente(id),
	primary key(ciclo, insegnamento_utente)
);

Create table tassa(
	id int not null primary key auto_increment,
	importo decimal(6, 2) not null,
	scadenza date not null,
	pagata boolean default false,
	utente int not null, 
	foreign key (utente) references utente(matricola)
);
