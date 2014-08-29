package projekti.projhallinta;

public class Vaihe {
	private int id;
	private int vaihenro;
	private String nimi;
	private String alkupvm;
	private String loppupvm;
	private String selite;
	
	public Vaihe(int id, String nimi, String alkupvm, String loppupvm,
			String selite) {

		this.id = id;
		this.vaihenro = vaihenro;
		this.nimi = nimi;
		this.alkupvm = alkupvm;
		this.loppupvm = loppupvm;
		this.selite = selite;
	}

	public Vaihe(int id,int vaihenro, String nimi, String alkupvm, String loppupvm,
			String selite) {

		this.id = id;
		this.vaihenro = vaihenro;
		this.nimi = nimi;
		this.alkupvm = alkupvm;
		this.loppupvm = loppupvm;
		this.selite = selite;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vaihe other = (Vaihe) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNimi() {
		return nimi;
	}

	public void setNimi(String nimi) {
		this.nimi = nimi;
	}

	public String getAlkupvm() {
		return alkupvm;
	}

	public void setAlkupvm(String alkupvm) {
		this.alkupvm = alkupvm;
	}

	public String getLoppupvm() {
		return loppupvm;
	}

	public void setLoppupvm(String loppupvm) {
		this.loppupvm = loppupvm;
	}

	public String getSelite() {
		return selite;
	}

	public void setSelite(String selite) {
		this.selite = selite;
	}

	@Override
	public String toString() {
		return "Vaihe: " + id + " " + nimi;
	}

}
