package projekti.projhallinta;

public class Asiakas {
	private int ID;
	private String nimi;
	private String ayritys;
	private String ayhteishenkilo;
	
	public Asiakas(int iD, String nimi, String ayritys, String ayhteishenkilo) {
		ID = iD;
		this.nimi = nimi;
		this.ayritys = ayritys;
		this.ayhteishenkilo = ayhteishenkilo;
	}

	public int getID() {
		return ID;
	}

	@Override
	public String toString() {
		return nimi + " :  " + ayritys;
	}

	public String getNimi() {
		return nimi;
	}

	public String getAyritys() {
		return ayritys;
	}

	public String getAyhteishenkilo() {
		return ayhteishenkilo;
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
