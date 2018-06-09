package gym.db;

import java.util.Date;

public class Korisnik {
	private int id;
	private String ime;
	private String prezime;
	private Tip_korisnika tip_korisnika;
	private String kor_ime;
	private String zaporka;
	private Date datum_rodenja;
	private boolean aktivan;
	
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
	
	public String getKor_ime() {
		return kor_ime;
	}
	public void setKor_ime(String kor_ime) {
		this.kor_ime = kor_ime;
	}
	public String getZaporka() {
		return zaporka;
	}
	public void setZaporka(String zaporka) {
		this.zaporka = zaporka;
	}
	public boolean isAktivan() {
		return aktivan;
	}
	public void setAktivan(boolean aktivan) {
		this.aktivan = aktivan;
	}
	public Tip_korisnika getTip_korisnika() {
		return tip_korisnika;
	}
	public void setTip_korisnika(Tip_korisnika tip_korisnika) {
		this.tip_korisnika = tip_korisnika;
	}
	public String getPrezime() {
		return prezime;
	}
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	public Date getDatum_rodenja() {
		return datum_rodenja;
	}
	public void setDatum_rodenja(Date datum_rodenja) {
		this.datum_rodenja = datum_rodenja;
	}
}
