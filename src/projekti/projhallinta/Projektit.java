package projekti.projhallinta;

import java.util.ArrayList;
import java.util.List;

/**
 * Object class containing an arraylist with projects.
 * @author s1200508
 *
 */

public class Projektit {
	
	List<Projekti> projektit;
	
	public Projektit(){
		projektit = new ArrayList<Projekti>();
	}
	
	/**
	 * Adds a new project in the arraylist.
	 * @param projekti
	 */
	public void lisaaProjekti(Projekti projekti){
		projektit.add(projekti);
	}
	/**
	 * Removes a project from the arraylist
	 * @param projekti
	 */
	public void poistaProjekti(Projekti projekti){
		Projekti poistettava = null;
		for(Projekti haettava: projektit){
			if(haettava.equals(projekti)){
				poistettava = projekti;
			}
			
		}
		projektit.remove(poistettava);
	}
	/**
	 * Edits a project in the arraylist.
	 * @param projekti
	 * @param nimi
	 * @param alkupvm
	 * @param tyontekijat
	 * @param asiakas
	 * @param selite
	 */
	public void muokkaaProjektia(Projekti projekti, String nimi, String alkupvm, List<Tyontekija> tyontekijat, Asiakas asiakas, String selite){
		Projekti muokattava = null;
		for(Projekti haettava: projektit){
			if(haettava.equals(projekti)){
				muokattava = projekti;
			}
			
		}
		muokattava.setNimi(nimi);
		muokattava.setAlkupvm(alkupvm);
		muokattava.setAsiakas(asiakas);
		muokattava.setSelite(selite);
	}
	/**
	 * Returns the projects arraylist
	 * @return
	 */
	public List<Projekti> palautaLista(){
		return this.projektit;
	}

}
