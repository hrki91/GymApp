package gym.db;

public class Tip_korisnika {
	private Integer id;
	private String naziv;
	private String opis;
	
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOpis() {
		return opis;
	}
	public void setOpis(String opis) {
		this.opis = opis;
	}
	@Override
	public String toString() {
		return this.naziv;
	}
	@Override
	public boolean equals(Object obj) {
		if(obj != null) {
			Tip_korisnika tk = (Tip_korisnika) obj;
			if(tk.getId() == this.id)
				return true;
		}
		
		return false;
	}
}
