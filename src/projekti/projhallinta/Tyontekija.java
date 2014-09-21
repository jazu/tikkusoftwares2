package projekti.projhallinta;

/**
 * Class used to create and store data about the workers.
 * @author s1200508
 *
 */

public class Tyontekija {
	private int tyontekijaID;
	private String etunimi;
	private String sukunimi;
	private int syntymavuosi;
	private String Osaaminen;


	/**
	 * Creates a new worker with the given parameters
	 * @param kayttajaID
	 * @param etunimi
	 * @param sukunimi
	 * @param syntymavuosi
	 * @param Osaaminen
	 */

	public Tyontekija(int kayttajaID, String etunimi, String sukunimi,
		int syntymavuosi, String Osaaminen) {
		this.tyontekijaID = kayttajaID;
		this.etunimi = etunimi;
		this.sukunimi = sukunimi;
		this.syntymavuosi = syntymavuosi;
		this.Osaaminen = Osaaminen;
	}

	public int getKayttajaID() {
		return tyontekijaID;
	}

	public String getEtunimi() {
		return etunimi;
	}

	public String getSukunimi() {
		return sukunimi;
	}

	public int getSyntymavuosi() {
		return syntymavuosi;
	}

	public String getOsaaminen() {
		return Osaaminen;
	}

	@Override
	public String toString() {
		return etunimi + " " + sukunimi + " : " + Osaaminen
				;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + tyontekijaID;
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
		Tyontekija other = (Tyontekija) obj;
		if (tyontekijaID != other.tyontekijaID)
			return false;
		return true;
	}
	

}
