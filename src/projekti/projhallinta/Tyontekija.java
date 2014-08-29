package projekti.projhallinta;

public class Tyontekija {
	private int tyontekijaID;
	private String etunimi;
	private String sukunimi;
	private int syntymavuosi;
	private String Osaaminen;

	/*
	 * CREATE TABLE tyontekija ( kayttajaID INTEGER NOT NULL FOREIGN KEY,-----
	 * kayttaja_ID etunimi VARCHAR(55) NOT NULL, sukunimi VARCHAR(55) NOT NULL,
	 * syntymavuosi INTEGER NOT NULL, osaaminen VARCHAR(255) NOT NULL );
	 * 
	 * insert into tyontekija (kayttajaID, etunimi,sukunimi, syntymavuosi,
	 * osaaminen) values(1,'Make','Makkaraperunat',1995,'Java-Ohjelmointi');
	 * insert into tyontekija (kayttajaID, etunimi,sukunimi, syntymavuosi,
	 * osaaminen) values(2,'Jasu','Oureb',2001,'Web-Ohjelmointi'); insert into
	 * tyontekija (kayttajaID, etunimi,sukunimi, syntymavuosi, osaaminen)
	 * values(3,'Miro','Helenius',1985,'Java-Ohjelmointi'); insert into
	 * tyontekija (kayttajaID, etunimi,sukunimi, syntymavuosi, osaaminen)
	 * values(4,'Heikki','Heineken',1988,'Tietoturva'); insert into tyontekija
	 * (kayttajaID, etunimi,sukunimi, syntymavuosi, osaaminen)
	 * values(5,'Jaasu','Anttila',1993,'Helpdesk'); insert into tyontekija
	 * (kayttajaID, etunimi,sukunimi, syntymavuosi, osaaminen)
	 * values(6,'Muro','Kulho',1992,'Java-Ohjelmointi');
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
