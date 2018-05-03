package gym.db;

import java.util.Date;

public class Dolasci {
	private int id;
	private Clan clan;
	private Date dolazak;
	private Date odlazak;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Clan getClan() {
		return clan;
	}
	public void setClan(Clan clan) {
		this.clan = clan;
	}
	public Date getDolazak() {
		return dolazak;
	}
	public void setDolazak(Date dolazak) {
		this.dolazak = dolazak;
	}
	public Date getOdlazak() {
		return odlazak;
	}
	public void setOdlazak(Date odlazak) {
		this.odlazak = odlazak;
	}
}
