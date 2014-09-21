package projekti.projhallinta;
/**
 * Customer object class.
 * @author s1200508
 *
 */
public class Asiakas {
	
	private int ID;
	private String nimi;
	private String ayritys;
	private String ayhteishenkilo;
	/**
	 * Creates a new Customer with the info given a paremeters.
	 * @param iD
	 * @param nimi
	 * @param ayritys
	 * @param ayhteishenkilo
	 */
	public Asiakas(int iD, String nimi, String ayritys, String ayhteishenkilo) {
		ID = iD;
		this.nimi = nimi;
		this.ayritys = ayritys;
		this.ayhteishenkilo = ayhteishenkilo;
	}
	/** 
	 * Returns the ID of the customer.
	 * @return Customer ID.
	 */
	public int getID() {
		return ID;
	}

	@Override
	public String toString() {
		return nimi + " :  " + ayritys;
	}
	
	/**
	 * Returns the name of the customer.
	 * @return customer name
	 */

	public String getNimi() {
		return nimi;
	}
	
	/**
	 * Returns the company of the customer.
	 * @return customers company.
	 */

	public String getAyritys() {
		return ayritys;
	}
	
	/**
	 * Returns the contact person of the customer
	 * @return contact person
	 */

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
