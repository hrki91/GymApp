package gym.db;

public class Tip_treninga {
	private int id;
	private String naziv;
	
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return this.naziv;
	}
		@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		Tip_treninga tt = null;
		if (obj != null) {
			tt = (Tip_treninga) obj;
			if(tt.getId() == this.id)
				return true;
		}
		return false;
	}
}
