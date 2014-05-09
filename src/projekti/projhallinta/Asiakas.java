package projekti.projhallinta;

public class Asiakas {
	private int ID;
	private int nimi;
	
	public Asiakas(int iD, int nimi) {
		ID = iD;
		this.nimi = nimi;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ID;
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
		Asiakas other = (Asiakas) obj;
		if (ID != other.ID)
			return false;
		return true;
	}
	
	

}
