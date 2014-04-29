package projekti.tyontekija;

public class Tyontekija {
	private int kayttajaID;
	private String etunimi;
	private String sukunimi;
	private int syntymavuosi;
	private String Osaaminen;
	
	
	
	/*CREATE TABLE tyontekija (
			  kayttajaID INTEGER NOT NULL FOREIGN KEY,----- kayttaja_ID
			  etunimi VARCHAR(55) NOT NULL,
			  sukunimi VARCHAR(55) NOT NULL,
			  syntymavuosi INTEGER NOT NULL,
			  osaaminen VARCHAR(255) NOT NULL
			);
			
			insert into tyontekija (kayttajaID, etunimi,sukunimi, syntymavuosi, osaaminen) values(1,'Make','Makkaraperunat',1995,'Java-Ohjelmointi');
insert into tyontekija (kayttajaID, etunimi,sukunimi, syntymavuosi, osaaminen) values(2,'Jasu','Oureb',2001,'Web-Ohjelmointi');
insert into tyontekija (kayttajaID, etunimi,sukunimi, syntymavuosi, osaaminen) values(3,'Miro','Helenius',1985,'Java-Ohjelmointi');
insert into tyontekija (kayttajaID, etunimi,sukunimi, syntymavuosi, osaaminen) values(4,'Heikki','Heineken',1988,'Tietoturva');
insert into tyontekija (kayttajaID, etunimi,sukunimi, syntymavuosi, osaaminen) values(5,'Jaasu','Anttila',1993,'Helpdesk');
insert into tyontekija (kayttajaID, etunimi,sukunimi, syntymavuosi, osaaminen) values(6,'Muro','Kulho',1992,'Java-Ohjelmointi');
			
			
			*/
	
	public Tyontekija(int kayttajaID, String etunimi, String sukunimi, int syntymavuosi, String Osaaminen){
		this.kayttajaID = kayttajaID;
		this.etunimi = etunimi;
		this.sukunimi = sukunimi;
		this.syntymavuosi = syntymavuosi;
		this.Osaaminen = Osaaminen;
	}



	public int getKayttajaID() {
		return kayttajaID;
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
		return "Tyontekija [kayttajaID=" + kayttajaID + ", etunimi=" + etunimi
				+ ", sukunimi=" + sukunimi + ", syntymavuosi=" + syntymavuosi
				+ ", Osaaminen=" + Osaaminen + "]";
	}

}
