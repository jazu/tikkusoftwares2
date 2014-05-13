package projekti.projhallinta;

import java.util.ArrayList;
import java.util.List;


public class Tyontekijat {
	List<Tyontekija> tyontekijat;
	
	public Tyontekijat(){
		tyontekijat = new ArrayList<Tyontekija>();
	}
	public void lisaaTyontekija(Tyontekija tyontekija){
		tyontekijat.add(tyontekija);
	}
	public void poistaTyontekija(Tyontekija tyontekija){
		Tyontekija poistettava = null;
		for(Tyontekija haettava : tyontekijat){
			if(haettava.equals(tyontekija)){
				poistettava = tyontekija;
			}
			
		}
		tyontekijat.remove(poistettava);
	}

}
