package gym.db;

import java.util.Date;

public class Clan {
	private int id;
	private String ime;
	private String prezime;
	private Date datum_rodenja;
	private String adresa;
	private Clanarina clanarina;
	private Trening trening;
	
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPrezime() {
		return prezime;
	}
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	public String getAdresa() {
		return adresa;
	}
	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}
	public Date getDatum_rodenja() {
		return datum_rodenja;
	}
	public void setDatum_rodenja(Date datum_rodenja) {
		this.datum_rodenja = datum_rodenja;
	}
	public Clanarina getClanarina() {
		return clanarina;
	}
	public void setClanarina(Clanarina clanarina) {
		this.clanarina = clanarina;
	}
	public Trening getTrening() {
		return trening;
	}
	public void setTrening(Trening trenig) {
		this.trening = trenig;
	}
	
}
