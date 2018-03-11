create table clanarina (
id int primary key auto_increment,
naziv varchar(20) not null,
aktivna boolean not null,
trajanje_u_danima int not null,
br_treninga_u_tjednu int not null,
cijena decimal (10,4) not null);

create table tip_treninga(
id int primary key auto_increment,
naziv varchar(20) not null);

create table skupina_tijela(
id int primary key auto_increment,
naziv varchar(20) not null,
opis varchar(200)
);

create table vjezba(
id int primary key auto_increment,
naziv varchar(20) not null,
tip_treniga int,
skupina int,
tezina int,
FOREIGN KEY (tip_treniga)
        REFERENCES tip_treninga(id),
FOREIGN KEY (skupina)
        REFERENCES skupina_tijela(id));

create table trening(
id int primary key auto_increment,
naziv varchar(20) not null,
tip_treniga int,
tezina int,
FOREIGN KEY (tip_treniga)
        REFERENCES tip_treninga(id));

create table plan_treniga(
id_treninga int,
id_vjezbe int,
broj_ponavljanja int,
tezina int,
dan int,
FOREIGN KEY (id_vjezbe)
        REFERENCES vjezba(id),
FOREIGN KEY (id_treninga)
        REFERENCES trening(id));

create table clan(
id int primary key auto_increment,
ime varchar(30),
prezime varchar(30),
datum_rodenja date,
adresa varchar(20),
clanarina int,
trening int,
FOREIGN KEY (clanarina)
        REFERENCES clanarina(id),
FOREIGN KEY (trening)
        REFERENCES trening(id));

create table dolasci(
id int primary key auto_increment,
clan int,
dolazak datetime,
odlazak datetime,
FOREIGN KEY (clan)
        REFERENCES clan(id));

create table tip_korisnika(
id int primary key auto_increment,
naziv varchar(20) not null,
opis varchar (200));

create table korisnik(
id int primary key auto_increment,
ime varchar(30) not null ,
prezime varchar (30),
tip_korisnika int,
kor_ime varchar(30),
zaporka char(32),
aktivan boolean,
FOREIGN KEY (tip_korisnika)
	REFERENCES tip_korisnika(id));