package projekti.projhallinta;

import java.util.ArrayList;
import java.util.List;

/**
 * Object class to store customers.
 * 
 *
 */
public class Asiakkaat {
	List<Asiakas> asiakkaat;
	
	
	
	public Asiakkaat(){
		asiakkaat = new ArrayList<Asiakas>();
	}
	
	/**
	 * Adds a new customer in the customers object.
	 * @param asiakas
	 */
	public void lisaaAsiakas(Asiakas asiakas){
		if(!asiakkaat.contains(asiakas)){
			asiakkaat.add(asiakas);
		}

	}
	
	/**
	 * Returns the list of all the customers inside the customer object.
	 * @return customer list.
	 */
	public List<Asiakas> getAsiakkaat() {
		return asiakkaat;
	}
	/**
	 * Removes a customer from the customers object.
	 * @param asiakas
	 */
	public void poistaAsiakas(Asiakas asiakas){
		asiakkaat.remove(asiakas);
	}

}
