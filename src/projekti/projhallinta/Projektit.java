package projekti.projhallinta;

import java.util.ArrayList;
import java.util.List;


public class Projektit {
	
	List<Projekti> projektit;
	
	public Projektit(){
		projektit = new ArrayList<Projekti>();
	}
	public void lisaaProjekti(Projekti projekti){
		projektit.add(projekti);
	}
	public void poistaProjekti(Projekti projekti){
		Projekti poistettava = null;
		for(Projekti haettava: projektit){
			if(haettava.equals(projekti)){
				poistettava = projekti;
			}
			
		}
		projektit.remove(poistettava);
	}
	public void muokkaaProjektia(Projekti projekti, String nimi, String alkupvm, List<Tyontekija> tyontekijat, String asiakas, String selite){
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
	public List<Projekti> palautaLista(){
		return this.projektit;
	}

}
