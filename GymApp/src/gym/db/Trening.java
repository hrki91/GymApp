package gym.db;

public class Trening {
	private int id;
	private String naziv;
	private Tip_treninga tip_treninga;
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
	public Tip_treninga getTip_treninga() {
		return tip_treninga;
	}
	public void setTip_treninga(Tip_treninga tip_treninga) {
		this.tip_treninga = tip_treninga;
	}
	public int getTezina() {
		return tezina;
	}
	public void setTezina(int tezina) {
		this.tezina = tezina;
	}
	@Override
	public boolean equals(Object obj) {
		if (obj != null) {
			Trening tt = (Trening) obj;
			if(tt.getId() == this.id)
				return true;
		}
		return false;
	}
	@Override
	public String toString() {
		return this.naziv;
	}
}
