package gym.db;

public class Clanarina {
	private int id;
	private String naziv;
	private int aktivna;
	private int trajanje_u_danima;
	private int br_treninga_u_tjednu;
	private double cijena;
	
	public int getTrajanje_u_danima() {
		return trajanje_u_danima;
	}
	public void setTrajanje_u_danima(int trajanje_u_danima) {
		this.trajanje_u_danima = trajanje_u_danima;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public int getAktivna() {
		return aktivna;
	}
	public void setAktivna(int aktivna) {
		this.aktivna = aktivna;
	}
	public int getBr_treninga_u_tjednu() {
		return br_treninga_u_tjednu;
	}
	public void setBr_treninga_u_tjednu(int br_treniga_u_tjednu) {
		this.br_treninga_u_tjednu = br_treniga_u_tjednu;
	}
	public double getCijena() {
		return cijena;
	}
	public void setCijena(double cijena) {
		this.cijena = cijena;
	}
}
