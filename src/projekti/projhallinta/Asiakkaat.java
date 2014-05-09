package projekti.projhallinta;

import java.util.ArrayList;
import java.util.List;

public class Asiakkaat {
	List<Asiakas> asiakkaat;
	
	public Asiakkaat(){
		asiakkaat = new ArrayList<Asiakas>();
	}
	public void lisaaAsiakas(Asiakas asiakas){
		asiakkaat.add(asiakas);
	}

}
