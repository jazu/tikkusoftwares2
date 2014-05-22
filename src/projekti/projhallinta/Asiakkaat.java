package projekti.projhallinta;

import java.util.ArrayList;
import java.util.List;

public class Asiakkaat {
	List<Asiakas> asiakkaat;
	
	public Asiakkaat(){
		asiakkaat = new ArrayList<Asiakas>();
	}
	public void lisaaAsiakas(Asiakas asiakas){
		if(!asiakkaat.contains(asiakas)){
			asiakkaat.add(asiakas);
		}

	}
	public List<Asiakas> getAsiakkaat() {
		return asiakkaat;
	}
	public void poistaAsiakas(Asiakas asiakas){
		asiakkaat.remove(asiakas);
	}

}
