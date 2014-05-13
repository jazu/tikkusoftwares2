package projekti.projhallinta;

import java.util.ArrayList;
import java.util.List;



public class Projekti {
	private int ID;
	private String nimi;
	private String alkupvm;
	private String loppupvm;
	private List<Vaihe> vaiheet;
	private List<Tyontekija> tyontekijat;
	private String asiakas;
	private String selite;
	
	public Projekti(int iD, String nimi, String alkupvm, String loppupvm, String selite) {
		
		ID = iD;
		this.nimi = nimi;
		this.alkupvm = alkupvm;
		this.loppupvm = loppupvm;
		this.selite = selite;
		this.vaiheet = new ArrayList<Vaihe>();
		this.tyontekijat = new ArrayList<Tyontekija>();
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getNimi() {
		return nimi;
	}

	public void setNimi(String nimi) {
		this.nimi = nimi;
	}

	public String getAlkupvm() {
		return alkupvm;
	}

	public void setAlkupvm(String alkupvm) {
		this.alkupvm = alkupvm;
	}

	public String getLoppupvm() {
		return loppupvm;
	}

	public void setLoppupvm(String loppupvm) {
		this.loppupvm = loppupvm;
	}

	public List<Vaihe> getVaiheet() {
		return vaiheet;
	}

	public void setVaiheet(List<Vaihe> vaiheet) {
		this.vaiheet = vaiheet;
	}

	public String getAsiakas() {
		return asiakas;
	}

	public void setAsiakas(String asiakas) {
		this.asiakas = asiakas;
	}

	public String getSelite() {
		return selite;
	}

	public void setSelite(String selite) {
		this.selite = selite;
	}

	public List<Tyontekija> getTyontekijat() {
		return tyontekijat;
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
	public void lisaaVaihe(Vaihe vaihe){
	//	if(!this.vaiheet.contains(vaihe)){
			this.vaiheet.add(vaihe);
	//	}

	}
	public void lisaaTyontekija(Tyontekija tyontekija){
	//	if(!this.tyontekijat.contains(tyontekija)){
			this.tyontekijat.add(tyontekija);
	//	}

	}
	public void lisaaVaiheet(List<Vaihe> vaiheet){
		this.vaiheet = vaiheet;
	}
	public void lisaaTyontekijat(List<Tyontekija> vaiheet){
		this.tyontekijat = vaiheet;
	}
	public void lisaaAsiakas(String string){
		this.asiakas = string;
	}

	@Override
	public String toString() {
		return "Projekti ID: " + ID + " " + nimi;
	}
	
	
	

}
