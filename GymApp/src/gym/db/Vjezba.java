package gym.db;

public class Vjezba {
	private int id;
	private String naziv;
	private Trening tip;
	private Skupina_tijela skupina;
	private int tezina;
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
	public Trening getTip() {
		return tip;
	}
	public void setTip(Trening tip) {
		this.tip = tip;
	}
	public Skupina_tijela getSkupina() {
		return skupina;
	}
	public void setSkupina(Skupina_tijela skupina) {
		this.skupina = skupina;
	}
	public int getTezina() {
		return tezina;
	}
	public void setTezina(int tezina) {
		this.tezina = tezina;
	}
	
}
