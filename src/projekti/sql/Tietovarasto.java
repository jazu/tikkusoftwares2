package projekti.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import projekti.*;
import projekti.login.User;
import projekti.projhallinta.Asiakas;
import projekti.projhallinta.Projekti;
import projekti.projhallinta.ProjektinStatus;
import projekti.projhallinta.PStatus;
import projekti.projhallinta.Tyontekija;
import projekti.tyontekija.*;

public class Tietovarasto {
	private String ajuri;
	private String url;
	private String kayttaja;
	private String salasana;

	public Tietovarasto(String ajuri, String url, String kayttaja,
			String salasana) {
		this.ajuri = ajuri;
		this.url = url;
		this.kayttaja = kayttaja;
		this.salasana = salasana;
	}

	public Tietovarasto() {
		this("org.apache.derby.jdbc.EmbeddedDriver", "jdbc:derby:tikkudb",
				"Admin", "admin");
	}
	
	//LOGINISSA KÄYTETTÄVÄT HAUT
	//######################################
	public User haeKayttaja(String kayttajanimi){
			User user=null;
			Connection yhteys = Yhteydenhallinta.avaaYhteys(ajuri, url, kayttaja, salasana);;
			PreparedStatement hakulause = null;
			ResultSet tulosjoukko = null;
			if (yhteys == null) return null;
			try {
					String hakuSql = "select * from loginusers where kayttajanimi = ?";
					hakulause = yhteys.prepareStatement(hakuSql);
					hakulause.setString(1, kayttajanimi);
					tulosjoukko = hakulause.executeQuery();

					if (tulosjoukko.next()) {
						return new User(tulosjoukko.getInt(1),
								tulosjoukko.getString(2),
								tulosjoukko.getString(3)
								,tulosjoukko.getInt(4));
					} else{
						return null;
					}
				

			} catch (Exception e) {
				e.printStackTrace();
				return null;
			} finally {
				Yhteydenhallinta.suljeTulosjoukko(tulosjoukko);
				Yhteydenhallinta.suljeLause(hakulause);
				Yhteydenhallinta.suljeYhteys(yhteys);

			}
			
	}

	public List<User> haeKaikkiKayttajat() {
		List<User> kayttajat = new ArrayList<User>();
		Connection yhteys = null;
		PreparedStatement hakulause = null;
		ResultSet tulosjoukko = null;
		try {
			yhteys = Yhteydenhallinta
					.avaaYhteys(ajuri, url, kayttaja, salasana);
			if (yhteys != null) {

				String haeKaikkiSql = "select * from loginusers";
				hakulause = yhteys.prepareStatement(haeKaikkiSql);
				tulosjoukko = hakulause.executeQuery();

				while (tulosjoukko.next()) {
					int userID = tulosjoukko.getInt(1);
					String nimi = tulosjoukko.getString(2);
					String password = tulosjoukko.getString(3);
					int isAdmin = tulosjoukko.getInt(4);

					kayttajat.add(new User(userID,nimi, password, isAdmin));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Yhteydenhallinta.suljeTulosjoukko(tulosjoukko);
			Yhteydenhallinta.suljeLause(hakulause);
			Yhteydenhallinta.suljeYhteys(yhteys);

		}
		return kayttajat;
	}

	public boolean poistaKayttaja(int kayttajaID) {
		Connection yhteys = null;
		PreparedStatement poistolause = null;
		try {
			yhteys = Yhteydenhallinta
					.avaaYhteys(ajuri, url, kayttaja, salasana);

			if (yhteys == null)
				return false;

			String poistaHenkiloSql = "delete from loginusers where kayttajaID = ?";
			poistolause = yhteys.prepareStatement(poistaHenkiloSql);
			poistolause.setInt(1, kayttajaID);
			poistolause.executeUpdate();
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			Yhteydenhallinta.suljeLause(poistolause);
			Yhteydenhallinta.suljeYhteys(yhteys);

		}
	}
	public boolean lisaaKayttaja(User user) {
		Connection yhteys = null;
		PreparedStatement lisayslause = null;
		try {
			yhteys = Yhteydenhallinta
					.avaaYhteys(ajuri, url, kayttaja, salasana);
			if (yhteys == null)
				return false;

			String lisaaHenkiloSql = "insert into loginusers (kayttajaID, kayttajanimi ,salasana, onkoAdmin) values(?,?,?,?)";
			lisayslause = yhteys.prepareStatement(lisaaHenkiloSql);
			
			lisayslause.setInt(1, user.getUserID());
			lisayslause.setString(2, user.getNimi());
			lisayslause.setString(3, user.getPassword());
			lisayslause.setInt(4, user.getIsAdmin());

			lisayslause.executeUpdate();
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			Yhteydenhallinta.suljeLause(lisayslause);
			Yhteydenhallinta.suljeYhteys(yhteys);

		}
	}
	
	// TYÖNTEKIJÄ ???
	//#####################################################

	public boolean lisaaTyontekija(Tyontekija tyontekija) {
		Connection yhteys = null;
		PreparedStatement lisayslause = null;
		try {
			yhteys = Yhteydenhallinta
					.avaaYhteys(ajuri, url, kayttaja, salasana);
			if (yhteys == null)
				return false;

			String lisaaHenkiloSql = "insert into tyontekija (kayttajaID, etunimi ,sukunimi, syntymavuosi, osaaminen) values(?,?,?,?,?)";
			lisayslause = yhteys.prepareStatement(lisaaHenkiloSql);
			
			lisayslause.setInt(1, tyontekija.getKayttajaID());
			lisayslause.setString(2, tyontekija.getEtunimi());
			lisayslause.setString(3, tyontekija.getSukunimi());
			lisayslause.setInt(4, tyontekija.getSyntymavuosi());
			lisayslause.setString(5, tyontekija.getOsaaminen());

			lisayslause.executeUpdate();
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			Yhteydenhallinta.suljeLause(lisayslause);
			Yhteydenhallinta.suljeYhteys(yhteys);

		}
	}
	
	public List<Tyontekija> haeKaikkiTyontekijat() {
		List<Tyontekija> tyontekijat = new ArrayList<Tyontekija>();
		Connection yhteys = null;
		PreparedStatement hakulause = null;
		ResultSet tulosjoukko = null;
		try {
			yhteys = Yhteydenhallinta
					.avaaYhteys(ajuri, url, kayttaja, salasana);
			if (yhteys != null) {

				String haeKaikkiSql = "select * from tyontekija";
				hakulause = yhteys.prepareStatement(haeKaikkiSql);
				tulosjoukko = hakulause.executeQuery();

				while (tulosjoukko.next()) {
					int userID = tulosjoukko.getInt(1);
					String nimi = tulosjoukko.getString(2);
					String password = tulosjoukko.getString(3);
					int isAdmin = tulosjoukko.getInt(4);
					String selite = tulosjoukko.getString(5);

					tyontekijat.add(new Tyontekija(userID,nimi, password, isAdmin,selite));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Yhteydenhallinta.suljeTulosjoukko(tulosjoukko);
			Yhteydenhallinta.suljeLause(hakulause);
			Yhteydenhallinta.suljeYhteys(yhteys);

		}
		return tyontekijat;
	}
	
	public boolean muutaTyontekijanTietoja(Tyontekija tyontekija){
		Connection yhteys= Yhteydenhallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
		if(yhteys==null) return false;
		PreparedStatement muutoslause=null;
		try{
			String muutoslauseSql ="update tyontekija set kayttajaID=?, etunimi=? ,sukunimi=?, syntymavuosi=?, osaaminenetunimi=?, sukunimi=?, syntymavuosi=? where henkiloID=?";
			muutoslause = yhteys.prepareStatement(muutoslauseSql);
			muutoslause.setInt(1, tyontekija.getKayttajaID());
			muutoslause.setString(2, tyontekija.getEtunimi());
			muutoslause.setString(3, tyontekija.getSukunimi());
			muutoslause.setInt(4, tyontekija.getSyntymavuosi());
			muutoslause.setString(5, tyontekija.getOsaaminen());
			
			muutoslause.executeUpdate();
			return true;
			
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		finally{
			Yhteydenhallinta.suljeLause(muutoslause);
			Yhteydenhallinta.suljeYhteys(yhteys);
		}
	}
	
	
	
	// PROJEKTIN TIETOJEN HAKEMINEN TIETOKANNASTA
	// ###########################################
	
	public List<Projekti> haeKaikkiProjektit() {
		List<Projekti> projektit = new ArrayList<Projekti>();
		Connection yhteys = null;
		PreparedStatement hakulause = null;
		ResultSet tulosjoukko = null;
		try {
			yhteys = Yhteydenhallinta
					.avaaYhteys(ajuri, url, kayttaja, salasana);
			if (yhteys != null) {

				String haeKaikkiSql = "select * from projekti";
				hakulause = yhteys.prepareStatement(haeKaikkiSql);
				tulosjoukko = hakulause.executeQuery();

				while (tulosjoukko.next()) {
					int iD = tulosjoukko.getInt(1);
					String nimi = tulosjoukko.getString(2);
					String alkupvm = tulosjoukko.getString(3);
					String loppupvm = tulosjoukko.getString(4);
					String selite = tulosjoukko.getString(5);
				

					projektit.add(new Projekti(iD, nimi, alkupvm, loppupvm, selite));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Yhteydenhallinta.suljeTulosjoukko(tulosjoukko);
			Yhteydenhallinta.suljeLause(hakulause);
			Yhteydenhallinta.suljeYhteys(yhteys);

		}
		return projektit;
	}
	


	
	public PStatus haeProjektinStatus(int projeID){
		int projID = projeID;
		PStatus projektistatus = null;
		
		Connection yhteys = null;
		PreparedStatement hakulause = null;
		ResultSet tulosjoukko = null;
		try {
			yhteys = Yhteydenhallinta
					.avaaYhteys(ajuri, url, kayttaja, salasana);
			if (yhteys != null) {

				String haeKaikkiSql = "select * from statustaulu where projektiID="+projID;
				hakulause = yhteys.prepareStatement(haeKaikkiSql);
				tulosjoukko = hakulause.executeQuery();

				while (tulosjoukko.next()) {
					int projektiID = tulosjoukko.getInt(1);
					int statusID = tulosjoukko.getInt(2);
					String status = tulosjoukko.getString(3);
				

					projektistatus = new PStatus(projektiID, statusID, status);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Yhteydenhallinta.suljeTulosjoukko(tulosjoukko);
			Yhteydenhallinta.suljeLause(hakulause);
			Yhteydenhallinta.suljeYhteys(yhteys);

		}
		return projektistatus;
		
	}
	public Asiakas haeProjektinAsiakas(int projeID){
		int projID = projeID;
		Asiakas asiakas = null;
		
		Connection yhteys = null;
		PreparedStatement hakulause = null;
		ResultSet tulosjoukko = null;
		try {
			yhteys = Yhteydenhallinta
					.avaaYhteys(ajuri, url, kayttaja, salasana);
			if (yhteys != null) {

				String haeKaikkiSql = "select * from asiakkaat where projektiID="+projID;
				hakulause = yhteys.prepareStatement(haeKaikkiSql);
				tulosjoukko = hakulause.executeQuery();

				while (tulosjoukko.next()) {
					int asiakasID = tulosjoukko.getInt(1);
					int projektiID = tulosjoukko.getInt(2);
					String nimi = tulosjoukko.getString(3);
					String yritys = tulosjoukko.getString(4);
					String yhteyshlo = tulosjoukko.getString(5);
				

					asiakas = new Asiakas(projektiID, nimi, yritys, yhteyshlo);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Yhteydenhallinta.suljeTulosjoukko(tulosjoukko);
			Yhteydenhallinta.suljeLause(hakulause);
			Yhteydenhallinta.suljeYhteys(yhteys);

		}
		return asiakas;
		
	}
	
	public List<Asiakas> haeKaikkiAsiakkaat() {
		List<Asiakas> asiakkaat = new ArrayList<Asiakas>();
		Connection yhteys = null;
		PreparedStatement hakulause = null;
		ResultSet tulosjoukko = null;
		try {
			yhteys = Yhteydenhallinta
					.avaaYhteys(ajuri, url, kayttaja, salasana);
			if (yhteys != null) {

				String haeKaikkiSql = "select * from asiakkaat";
				hakulause = yhteys.prepareStatement(haeKaikkiSql);
				tulosjoukko = hakulause.executeQuery();

				while (tulosjoukko.next()) {
					int asiakasiD = tulosjoukko.getInt(1);
					int projektiID = tulosjoukko.getInt(2);
					String nimi = tulosjoukko.getString(3);
					String yritys = tulosjoukko.getString(4);
					String yhteyshlo = tulosjoukko.getString(5);
				

					asiakkaat.add(new Asiakas(projektiID, nimi, yritys, yhteyshlo));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Yhteydenhallinta.suljeTulosjoukko(tulosjoukko);
			Yhteydenhallinta.suljeLause(hakulause);
			Yhteydenhallinta.suljeYhteys(yhteys);

		}
		return asiakkaat;
	}
	


	
	
	// PROJEKTIN JA SEN TIETOJEN LISÄÄMINEN TIETOKANTAAN 
	//###################################################
	public boolean lisaaProjekti(Projekti projekti) {
		Connection yhteys = null;
		PreparedStatement lisayslause = null;
		try {
			yhteys = Yhteydenhallinta
					.avaaYhteys(ajuri, url, kayttaja, salasana);
			if (yhteys == null)
				return false;

			String lisaaHenkiloSql = "insert into projekti (projektinimi, alkupvm, deadline, selite) values(?,?,?,?)";
			lisayslause = yhteys.prepareStatement(lisaaHenkiloSql);
			
			lisayslause.setString(1, projekti.getNimi());
			lisayslause.setString(2, projekti.getAlkupvm());
			lisayslause.setString(3, projekti.getLoppupvm());
			lisayslause.setString(4, projekti.getSelite());

			lisayslause.executeUpdate();
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			Yhteydenhallinta.suljeLause(lisayslause);
			Yhteydenhallinta.suljeYhteys(yhteys);

		}
	}
	public boolean lisaaProjektinStatus(PStatus pstatus) {
		Connection yhteys = null;
		PreparedStatement lisayslause = null;
		try {
			yhteys = Yhteydenhallinta
					.avaaYhteys(ajuri, url, kayttaja, salasana);
			if (yhteys == null)
				return false;

			String lisaaHenkiloSql = "insert into statustaulu (projektiID, statusID, status) values(?,?,?)";
			lisayslause = yhteys.prepareStatement(lisaaHenkiloSql);
			
			lisayslause.setInt(1, pstatus.getProjektiID());
			lisayslause.setInt(2, pstatus.getStatusID());
			lisayslause.setString(3, pstatus.getStatus());


			lisayslause.executeUpdate();
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			Yhteydenhallinta.suljeLause(lisayslause);
			Yhteydenhallinta.suljeYhteys(yhteys);

		}
	}
	
	public boolean lisaaProjektinAsiakas(Asiakas asiakas) {
		Connection yhteys = null;
		PreparedStatement lisayslause = null;
		try {
			yhteys = Yhteydenhallinta
					.avaaYhteys(ajuri, url, kayttaja, salasana);
			if (yhteys == null)
				return false;

			String lisaaHenkiloSql = "insert into asiakkaat (projektiID, nimi, yritys, yhteyshlo) values(?,?,?,?)";
			lisayslause = yhteys.prepareStatement(lisaaHenkiloSql);
			
			lisayslause.setInt(1, asiakas.getID());
			lisayslause.setString(2, asiakas.getNimi());
			lisayslause.setString(3, asiakas.getAyritys());
			lisayslause.setString(4, asiakas.getAyhteishenkilo());


			lisayslause.executeUpdate();
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			Yhteydenhallinta.suljeLause(lisayslause);
			Yhteydenhallinta.suljeYhteys(yhteys);

		}
	}
	

}

