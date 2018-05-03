package gym.db;

public class Plan_treninga {
	private Trening trening;
	private Vjezba vjezba;
	private int ponavljanja;
	private int tezina;
	private int dan;
	private int id;
	public Trening getTrening() {
		return trening;
	}
	public void setTrening(Trening trening) {
		this.trening = trening;
	}
	public Vjezba getVjezba() {
		return vjezba;
	}
	public void setVjezba(Vjezba vjezba) {
		this.vjezba = vjezba;
	}
	public int getPonavljanja() {
		return ponavljanja;
	}
	public void setPonavljanja(int ponavljanja) {
		this.ponavljanja = ponavljanja;
	}
	public int getTezina() {
		return tezina;
	}
	public void setTezina(int tezina) {
		this.tezina = tezina;
	}
	public int getDan() {
		return dan;
	}
	public void setDan(int dan) {
		this.dan = dan;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
