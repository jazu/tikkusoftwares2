package projekti.projhallinta;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to store the projects as objects.
 * @author s1200508
 *
 */

public class Projekti {
	private int ID;
	private String nimi;
	private String alkupvm;
	private String loppupvm;
	private List<Vaihe> vaiheet;
	private List<Tyontekija> tyontekijat;
	private List<Asiakas> asiakkaat;
	private String selite;
	private ProjektinStatus status;
	private Asiakas asiakas;
	
	/**
	 * 
	 * @param nimi
	 * @param alkupvm
	 * @param loppupvm
	 * @param selite
	 */
	
	public Projekti(String nimi, String alkupvm, String loppupvm, String selite) {
		
		this.nimi = nimi;
		this.alkupvm = alkupvm;
		this.loppupvm = loppupvm;
		this.selite = selite;
		this.vaiheet = new ArrayList<Vaihe>();
		this.tyontekijat = new ArrayList<Tyontekija>();
		this.status = status;
		this.asiakkaat = new ArrayList<Asiakas>();
	}

	
	/**
	 * 
	 * @param iD
	 * @param nimi
	 * @param alkupvm
	 * @param loppupvm
	 * @param selite
	 */
	

	
	public Projekti(int iD, String nimi, String alkupvm, String loppupvm, String selite) {
		
		ID = iD;
		this.nimi = nimi;
		this.alkupvm = alkupvm;
		this.loppupvm = loppupvm;
		this.selite = selite;
		this.vaiheet = new ArrayList<Vaihe>();
		this.tyontekijat = new ArrayList<Tyontekija>();
		this.status = status;
		this.asiakkaat = new ArrayList<Asiakas>();
	}

	
	/**
	 * Returns the status object of a project.
	 * @return project status.
	 */
	public ProjektinStatus getStatus() {
		return status;
	}
	
	/**
	 * Sets the status of the project as the status given as paremeter.
	 * @param status
	 */

	public void setStatus(ProjektinStatus status) {
		this.status = status;
	}
	
	/**
	 * Returns the ID of the project.
	 * @return project ID
	 */

	public int getID() {
		return ID;
	}
	
	/**
	 * Sets the ID of the project to the ID given as paremeter.
	 * @param iD
	 */

	public void setID(int iD) {
		ID = iD;
	}
	/**
	 * Returns the name of the project
	 * @return name.
	 */

	public String getNimi() {
		return nimi;
	}
	
	/**
	 * Sets the name of the project as a name given as parameter.
	 * @param nimi
	 */

	public void setNimi(String nimi) {
		this.nimi = nimi;
	}
	
	/**
	 * Returns the start date of the project.
	 * @return start date.
	 */

	public String getAlkupvm() {
		return alkupvm;
	}
	
	/**
	 * Sets the start date of the project as a start date given as parameter.
	 * @param alkupvm
	 */

	public void setAlkupvm(String alkupvm) {
		this.alkupvm = alkupvm;
	}
	
	/**
	 * Returns the end date of the project.
	 * @return end date.
	 */

	public String getLoppupvm() {
		return loppupvm;
	}
	
	/**
	 * Sets the end date of the project as a end date given as parameter.
	 * @param loppupvm
	 */

	public void setLoppupvm(String loppupvm) {
		this.loppupvm = loppupvm;
	}
	
	/**
	 * Returns the phases of the project as an arraylist from the object.
	 * @return Arraylist containing phases.
	 */

	public List<Vaihe> getVaiheet() {
		return vaiheet;
	}
	
	/**
	 * Removes a specified phase from the project.
	 * @param vaihe
	 */
	public void poistaVaihe(Vaihe vaihe){
		if(vaiheet.contains(vaihe)){
			this.vaiheet.remove(vaihe);
		}

	}
	/**
	 * Replaces the arraylist containing all the phases in the project with a new arraylist containing phases.
	 * @param vaiheet
	 */

	public void setVaiheet(List<Vaihe> vaiheet) {
		this.vaiheet = vaiheet;
	}
	
	/**
	 * Returns the customer of the project.
	 * @return customer.
	 */

	public Asiakas getAsiakas() {
		return asiakas;
	}
	/**
	 * Sets the customer of the project as the customer given as parameter.
	 * @param asiakas
	 */

	public void setAsiakas(Asiakas asiakas) {
		this.asiakas = asiakas;
	}
	/**
	 * Returns the definition of the project.
	 * @return definition.
	 */

	public String getSelite() {
		return selite;
	}
	
	/**
	 * Sets the definition of the project as definition given as parameter.
	 * @param selite
	 */

	public void setSelite(String selite) {
		this.selite = selite;
	}
	
	/**
	 * Returns a list containing the workers in the project.
	 * @return Arraylist containing workers.
	 */

	public List<Tyontekija> getTyontekijat() {
		return tyontekijat;
	}
	
	/**
	 * Adds a customer for the project object.
	 * @param asiakas
	 */
	public void lisaaAsiakas(Asiakas asiakas){
		if(!this.asiakkaat.contains(asiakas)){
			this.asiakkaat.add(asiakas);
		}

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
		Projekti other = (Projekti) obj;
		if (ID != other.ID)
			return false;
		return true;
	}
	/**
	 * Adds a phase for the project object.
	 * @param vaihe
	 */
	public void lisaaVaihe(Vaihe vaihe){
		if(!this.vaiheet.contains(vaihe)){
			this.vaiheet.add(vaihe);
		}

	}
	/**
	 * Adds a worker for the project object.
	 * @param tyontekija
	 */
	public void lisaaTyontekija(Tyontekija tyontekija){
		if(!this.tyontekijat.contains(tyontekija)){
			this.tyontekijat.add(tyontekija);
		}

	}
	/**
	 * Removes a worker from the project object.
	 * @param poistettavatyontekija
	 */
	public void poistaTyontekija(Tyontekija poistettavatyontekija){
		Tyontekija poistettava = null;
		for(Tyontekija tyontekija : this.getTyontekijat()){
			if(tyontekija.equals(poistettavatyontekija)){
				poistettava = tyontekija;
			}
		}
		this.tyontekijat.remove(poistettava);
	}
	/**
	 * Adds an arraylist of phases for the project.
	 * @param vaiheet
	 */
	public void lisaaVaiheet(List<Vaihe> vaiheet){
		this.vaiheet = vaiheet;
	}
	/**
	 * Adds an arraylist of workers for the project.
	 * @param vaiheet
	 */
	public void lisaaTyontekijat(List<Tyontekija> vaiheet){
		this.tyontekijat = vaiheet;
	}


	@Override
	public String toString() {
		return "Projekti ID: " + ID + " " + nimi;
	}
	
	
	

}
