package gym.db;

public class Glavni_meni {
	private int pozicija;
	private String naslov;
	private String opis;
	private String controller;
	private String fxml;
	private boolean aktivna;
	private int korisnik;
	public int getPozicija() {
		return pozicija;
	}
	public void setPozicija(int pozicija) {
		this.pozicija = pozicija;
	}
	public String getNaslov() {
		return naslov;
	}
	public void setNaslov(String naslov) {
		this.naslov = naslov;
	}
	public String getOpis() {
		return opis;
	}
	public void setOpis(String opis) {
		this.opis = opis;
	}
	public String getController() {
		return controller;
	}
	public void setController(String controller) {
		this.controller = controller;
	}
	public String getFxml() {
		return fxml;
	}
	public void setFxml(String fxml) {
		this.fxml = fxml;
	}
	public boolean isAktivna() {
		return aktivna;
	}
	public void setAktivna(boolean aktivna) {
		this.aktivna = aktivna;
	}
	public int getKorisnik() {
		return korisnik;
	}
	public void setKorisnik(int korisnik) {
		this.korisnik = korisnik;
	}
}
