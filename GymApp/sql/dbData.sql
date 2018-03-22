insert into tip_korisnika (naziv,opis) values
('Voditelj','Osoba zadužena za vođenje teretane'),
('Korisnik','Osoba koja radi u teretani');

insert into korisnik(ime,prezime,tip_korisnika,kor_ime,zaporka,aktivan) values 
('Hrvoje','Spanja',1,'Hrki',md5('1234'),1),
('Marko','Havoić',2,'Marko',md5('12345'),1);

insert into tip_treninga(naziv) values ("Kardio"),("Masa"),("Rehabilitacija");
insert into trening (naziv, tip_treniga, tezina) values ("Mrsavljenje",1,5),("Trening 1",2,3);
insert into clanarina(naziv, aktivna, trajanje_u_danima, br_treninga_u_tjednu, cijena) values
("Gold",1,30,7,300.00),("Silver",1,30,5,250.00),("Bronze",1,30,3,200.00),("Dark",0,30,2,150.00);
insert into clan (ime, prezime, datum_rodenja, adresa, clanarina, trening) values
("Hrvoje","Spanja",'1991-09-06',"Vrtic 21",1,1),("Marko","Havoic",'1990-01-01',"Zagorje 1",1,2);

