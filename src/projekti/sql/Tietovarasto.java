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
import projekti.projhallinta.Vaihe;
import projekti.tyontekija.*;

public class Tietovarasto {
	private String ajuri;
	private String url;
	private String kayttaja;
	private String salasana;
	/**
	 * 
	 * @param ajuri
	 * @param url
	 * @param kayttaja
	 * @param salasana
	 */

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
	
	
	/**
	 * Searches an user with username given as parameter.
	 * @param kayttajanimi
	 * @return User object.
	 */
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
	
	/**
	 * Returns list containing all the users in the database.
	 * @return Arraylist containing all the users.
	 */

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
	
	/**
	 * Removes a user from the database.
	 * @param kayttajaID
	 * @return true if the remove was successful, else returns false.
	 */

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
	/**
	 * Adds a new user in the database.
	 * @param user
	 * @return true if the adding was successful, else returns false.
	 */
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
	
	
	/**
	 * Adds a new worker in the database.
	 * @param tyontekija
	 * @return true if the adding was successful, else returns false.
	 */

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
	
	/**
	 * Returns a list of all the workers in the database.
	 * @return Arraylist of all the workers.
	 */
	
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
	
	/**
	 * Edits the data of a Worker given as parameter.
	 * @param tyontekija
	 * @return true if the Edit was successful, else returns false.
	 */
	
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
	
	
	
	
	/**
	 * Returns a list containing all the projects in the database.
	 * @return Arraylist containing all the projects in database.
	 */
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
	
	/**
	 * Get the status of a project that has the project ID given as parameter.
	 * @param projeID
	 * @return Project status object.
	 */
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
	/**
	 * Returns a customer of a project that has the project ID given as parameter.
	 * @param projeID
	 * @return Customer object.
	 */
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

				String haeKaikkiSql = "select nimi,yritys,yhteyshlo from projektiasiakkaat,asiakkaat where projektiasiakkaat.asiakasID=asiakkaat.asiakasID and projektiID=?";
				hakulause = yhteys.prepareStatement(haeKaikkiSql);
				hakulause.setInt(1,projeID);
				tulosjoukko = hakulause.executeQuery();

				while (tulosjoukko.next()) {
					String nimi = tulosjoukko.getString(1);
					String yritys = tulosjoukko.getString(2);
					String yhteyshlo = tulosjoukko.getString(3);
				

					asiakas = new Asiakas(projeID, nimi, yritys, yhteyshlo);
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
	
	/**
	 * Returns a list of all the customers found in the database.
	 * @return Arraylist containing all the customers.
	 */
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
					int asiakasID = tulosjoukko.getInt(1);
					String nimi = tulosjoukko.getString(2);
					String yritys = tulosjoukko.getString(3);
					String yhteyshlo = tulosjoukko.getString(4);
				

					asiakkaat.add(new Asiakas(asiakasID, nimi, yritys, yhteyshlo));
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
	
	
	/**
	 * Adds a new project in the database.
	 * @param projekti
	 * @return true if the adding was successful, else returns false.
	 */
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
	
	/**
	 * Adds a status for a project.
	 * @param pstatus
	 * @return true if the adding was successful, else returns false.
	 */
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
	
	/**
	 * Adds a customer for a project.
	 * @param asiakas
	 * @param projekti
	 * @return true if the adding was successful, else returns false.
	 */
	
	public boolean lisaaProjektinAsiakas(Asiakas asiakas, Projekti projekti) {
		Connection yhteys = null;
		PreparedStatement lisayslause = null;
		try {
			yhteys = Yhteydenhallinta
					.avaaYhteys(ajuri, url, kayttaja, salasana);
			if (yhteys == null)
				return false;

			String lisaaHenkiloSql = "insert into projektiasiakkaat (asiakasID, projektiID) values(?,?)";
			lisayslause = yhteys.prepareStatement(lisaaHenkiloSql);
			
			lisayslause.setInt(1, asiakas.getID());
			lisayslause.setInt(2, projekti.getID());


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
	
	/**
	 * Removes a project from the database.
	 * @param projektiID
	 * @return true if the remove was successful, else returns false.
	 */
	
	public boolean poistaProjekti(int projektiID) {
		Connection yhteys = null;
		PreparedStatement poistolause = null;
		try {
			yhteys = Yhteydenhallinta
					.avaaYhteys(ajuri, url, kayttaja, salasana);

			if (yhteys == null)
				return false;

			String poistaHenkiloSql = "delete from projekti where projektiID = ?";
			poistolause = yhteys.prepareStatement(poistaHenkiloSql);
			poistolause.setInt(1, projektiID);
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
	/**
	 * Removes a customer from a project
	 * @param projektiID
	 * @return true if the remove was successful, else returns false.
	 */
	public boolean poistaProjektinAsiakas(int projektiID) {
		Connection yhteys = null;
		PreparedStatement poistolause = null;
		try {
			yhteys = Yhteydenhallinta
					.avaaYhteys(ajuri, url, kayttaja, salasana);

			if (yhteys == null)
				return false;

			String poistaHenkiloSql = "delete from projektiasiakkaat where projektiID = ?";
			poistolause = yhteys.prepareStatement(poistaHenkiloSql);
			poistolause.setInt(1, projektiID);
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
	
	/**
	 * removes the status of a project.
	 * @param projektiID
	 * @return true if the remove was successful, else returns false.
	 */
	public boolean poistaProjektinStatus(int projektiID) {
		Connection yhteys = null;
		PreparedStatement poistolause = null;
		try {
			yhteys = Yhteydenhallinta
					.avaaYhteys(ajuri, url, kayttaja, salasana);

			if (yhteys == null)
				return false;

			String poistaHenkiloSql = "delete from statustaulu where projektiID = ?";
			poistolause = yhteys.prepareStatement(poistaHenkiloSql);
			poistolause.setInt(1, projektiID);
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

	/**
	 * Gets the projectID of a project.
	 * @param projekti
	 * @return project ID.
	 */
	public int haeProjektiID(Projekti projekti){
		Projekti pprojekti = null;
		
		Connection yhteys = null;
		PreparedStatement hakulause = null;
		ResultSet tulosjoukko = null;
		try {
			yhteys = Yhteydenhallinta
					.avaaYhteys(ajuri, url, kayttaja, salasana);
			if (yhteys != null) {

				String haeKaikkiSql = "select * from projekti where projektinimi="+projekti.getNimi();
				hakulause = yhteys.prepareStatement(haeKaikkiSql);
				tulosjoukko = hakulause.executeQuery();

				while (tulosjoukko.next()) {
					int iD = tulosjoukko.getInt(1);
					String nimi = tulosjoukko.getString(2);
					String alkupvm = tulosjoukko.getString(3);
					String loppupvm = tulosjoukko.getString(4);
					String selite = tulosjoukko.getString(5);
				

					pprojekti = new Projekti(iD, nimi, alkupvm, loppupvm, selite);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Yhteydenhallinta.suljeTulosjoukko(tulosjoukko);
			Yhteydenhallinta.suljeLause(hakulause);
			Yhteydenhallinta.suljeYhteys(yhteys);

		}
		return pprojekti.getID();
		
	}
	

	
	/**
	 * Edits a project in the database.
	 * @param projekti
	 * @return true if the edit was successful, else returns false.
	 */
	public boolean muokkaaProjektia(Projekti projekti){
		Connection yhteys= Yhteydenhallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
		if(yhteys==null) return false;
		
		PreparedStatement muutoslause=null;
		try{
			String muutoslauseSql ="update projekti set projektinimi=?, alkupvm=?, deadline=?, selite=? where projektiID=?";
			muutoslause = yhteys.prepareStatement(muutoslauseSql);
			muutoslause.setString(1, projekti.getNimi());
			muutoslause.setString(2, projekti.getAlkupvm());
			muutoslause.setString(3, projekti.getLoppupvm());
			muutoslause.setString(4, projekti.getSelite());
			muutoslause.setInt(5, projekti.getID());
			
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
	
	/**
	 * Edits a customer of a project.
	 * @param asiakas
	 * @param projekti
	 * @return true if the edit was successful, else returns false.
	 */
	public boolean muokkaaProjektinAsiakasta(Asiakas asiakas, Projekti projekti){
		Connection yhteys= Yhteydenhallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
		if(yhteys==null) return false;
		
		PreparedStatement muutoslause=null;
		try{
			String muutoslauseSql ="update projektiasiakkaat set asiakasID=? where projektiID=?";
			muutoslause = yhteys.prepareStatement(muutoslauseSql);
			muutoslause.setInt(1, asiakas.getID());
			muutoslause.setInt(2, projekti.getID());
			
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
	
	/**
	 * Edits the status of a project.
	 * @param status
	 * @param projekti
	 * @return true if the edit was successful, else returns false.
	 */
	
	public boolean muokkaaProjektinStatusta(PStatus status, Projekti projekti){
		Connection yhteys= Yhteydenhallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
		if(yhteys==null) return false;
		
		PreparedStatement muutoslause=null;
		try{
			String muutoslauseSql ="update statustaulu set status=? where projektiID=?";
			muutoslause = yhteys.prepareStatement(muutoslauseSql);
			muutoslause.setString(1, status.getStatus());
			muutoslause.setInt(2, projekti.getID());
			
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
	
	
	/**
	 * Adds a customer to database.
	 * @param asiakas
	 * @return true if the adding was successful, else returns false.
	 */
	
	public boolean lisaaAsiakas(Asiakas asiakas) {
		Connection yhteys = null;
		PreparedStatement lisayslause = null;
		
		try {
			yhteys = Yhteydenhallinta
					.avaaYhteys(ajuri, url, kayttaja, salasana);
			if (yhteys == null)
				return false;

			String lisaaHenkiloSql = "insert into asiakkaat (nimi, yritys, yhteyshlo) values(?,?,?)";
			lisayslause = yhteys.prepareStatement(lisaaHenkiloSql);
			
			lisayslause.setString(1, asiakas.getNimi());
			lisayslause.setString(2, asiakas.getAyritys());
			lisayslause.setString(3, asiakas.getAyhteishenkilo());

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
	
	/**
	 * Removes a customer from database.
	 * @param asiakas
	 * @return true if the remove was successful, else returns false.
	 */
	
	public boolean poistaAsiakas(Asiakas asiakas) {
		Connection yhteys = null;
		PreparedStatement poistolause = null;
		try {
			yhteys = Yhteydenhallinta
					.avaaYhteys(ajuri, url, kayttaja, salasana);

			if (yhteys == null)
				return false;

			String poistaHenkiloSql = "delete from asiakkaat where asiakasID = ?";
			poistolause = yhteys.prepareStatement(poistaHenkiloSql);
			poistolause.setInt(1, asiakas.getID());
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

	
	/**
	 * Adds a phase for a project.
	 * @param vaihe
	 * @param projekti
	 * @return true if the adding was successful, else returns false.
	 */
	
	public boolean lisaaVaihe(Vaihe vaihe, Projekti projekti) {
		Connection yhteys = null;
		PreparedStatement lisayslause = null;
		
		try {
			yhteys = Yhteydenhallinta
					.avaaYhteys(ajuri, url, kayttaja, salasana);
			if (yhteys == null)
				return false;

			String lisaaHenkiloSql = "insert into projektinvaiheet (vaiheennro, nimi,  alkupvm, loppupvm, selite, projektiID) values(?,?,?,?,?,?)";
			lisayslause = yhteys.prepareStatement(lisaaHenkiloSql);
			
			System.out.println(" lisättävän vaiheen nro; "+vaihe.getVaihenro());
			
			lisayslause.setInt(1, vaihe.getVaihenro());
			lisayslause.setString(2, vaihe.getNimi());
			lisayslause.setString(3, vaihe.getAlkupvm());
			lisayslause.setString(4, vaihe.getLoppupvm());
			lisayslause.setString(5, vaihe.getSelite());
			lisayslause.setInt(6, projekti.getID());

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
	
	/**
	 * Removes a phase from the database.
	 * @param vaihe
	 * @return true if the remove was successful, else returns false.
	 */
	
	public boolean poistaVaihe(Vaihe vaihe) {
		Connection yhteys = null;
		PreparedStatement poistolause = null;
		try {
			yhteys = Yhteydenhallinta
					.avaaYhteys(ajuri, url, kayttaja, salasana);

			if (yhteys == null)
				return false;

			String poistaHenkiloSql = "delete from projektinvaiheet where vaiheenID = ?";
			poistolause = yhteys.prepareStatement(poistaHenkiloSql);
			poistolause.setInt(1, vaihe.getId());
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
	
	/**
	 * Returns an arraylist containing the stages of the given project.
	 * @param projekti
	 * @return Arraylist with stages.
	 */
	
	public List<Vaihe> haeProjektinVaiheet(Projekti projekti) {
		
		List<Vaihe> asiakkaat = new ArrayList<Vaihe>();
		Connection yhteys = null;
		PreparedStatement hakulause = null;
		ResultSet tulosjoukko = null;
		try {
			yhteys = Yhteydenhallinta
					.avaaYhteys(ajuri, url, kayttaja, salasana);
			if (yhteys != null) {

				String haeKaikkiSql = "select * from projektinvaiheet where projektiID=?";
				hakulause = yhteys.prepareStatement(haeKaikkiSql);
				hakulause.setInt(1, projekti.getID());
				tulosjoukko = hakulause.executeQuery();
				
			
				while (tulosjoukko.next()) {
					int vaiheenID = tulosjoukko.getInt(1);
					int vaiheennro = tulosjoukko.getInt(2);
					String nimi = tulosjoukko.getString(3);
					String alkupvm = tulosjoukko.getString(4);
					String loppupvm = tulosjoukko.getString(5);
					String selite = tulosjoukko.getString(6);
				

					asiakkaat.add(new Vaihe(vaiheenID, vaiheennro, nimi, alkupvm, loppupvm, selite));
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
	
	/**
	 * Returns the an Arraylist containing the workers in the given project.
	 * @param projekti
	 * @return Arraylist of workers in project
	 */
	
public List<Tyontekija> haeProjektinTyontekijat(Projekti projekti) {
		
		List<Tyontekija> tyontekijat = new ArrayList<Tyontekija>();
		Connection yhteys = null;
		PreparedStatement hakulause = null;
		ResultSet tulosjoukko = null;
		try {
			yhteys = Yhteydenhallinta
					.avaaYhteys(ajuri, url, kayttaja, salasana);
			if (yhteys != null) {
				String haeKaikkiSql = "select tyontekija.tyontekijaID,etunimi,sukunimi,syntymavuosi,osaaminen from projektityontekijat,tyontekija where projektityontekijat.tyontekijaID=tyontekija.tyontekijaID and projektiID=?";
				hakulause = yhteys.prepareStatement(haeKaikkiSql);
				hakulause.setInt(1, projekti.getID());
				tulosjoukko = hakulause.executeQuery();
				
			
				while (tulosjoukko.next()) {
					int tyontekijaID = tulosjoukko.getInt(1);
					String etunimi = tulosjoukko.getString(2);
					String sukunimi = tulosjoukko.getString(3);
					int syntymavuosi = tulosjoukko.getInt(4);
					String osaaminen = tulosjoukko.getString(5);
				

					tyontekijat.add(new Tyontekija(tyontekijaID, etunimi,sukunimi,syntymavuosi,osaaminen));
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
/**
 * Edits information about the project a worker is working on.
 * @param tyontekija
 * @param projekti
 * @return true if the editing was successful, else returns false.
 */

public boolean muokkaaProjektinTyontekijaa(Tyontekija tyontekija,Projekti projekti){
	Connection yhteys= Yhteydenhallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
	if(yhteys==null) return false;
	
	PreparedStatement muutoslause=null;
	try{
		String muutoslauseSql ="update projektityontekijat set projektiID=? where tyontekijaID=?";
		muutoslause = yhteys.prepareStatement(muutoslauseSql);
		muutoslause.setInt(1, projekti.getID());
		muutoslause.setInt(2, tyontekija.getKayttajaID());
		
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
/**
 * Removes a worker from specified project.
 * @param tyontekija
 * @return true if the removal was successful, else returns false.
 */
	
public boolean poistaTyontekijaProjektista(Tyontekija tyontekija, Projekti projekti){
	Connection yhteys= Yhteydenhallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
	if(yhteys==null) return false;
	
	PreparedStatement muutoslause=null;
	try{
		String muutoslauseSql ="delete from projektityontekijat where tyontekijaID=? and projektiID=?";
		muutoslause = yhteys.prepareStatement(muutoslauseSql);
		muutoslause.setInt(1, tyontekija.getKayttajaID());
		muutoslause.setInt(2, projekti.getID());
		
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

/**
 * Adds worker to specified project given as parameter.
 * @param tyontekija
 * @param projekti
 * @return true if the adding was successful, else returns false.
 */

public boolean lisaaTyontekijaProjektiin(Tyontekija tyontekija, Projekti projekti){
		Connection yhteys = null;
		PreparedStatement lisayslause = null;
		
		try {
			yhteys = Yhteydenhallinta
					.avaaYhteys(ajuri, url, kayttaja, salasana);
			if (yhteys == null)
				return false;

			String lisaaHenkiloSql = "insert into projektityontekijat (tyontekijaID, projektiID) values(?,?)";
			lisayslause = yhteys.prepareStatement(lisaaHenkiloSql);
			
			lisayslause.setInt(1, tyontekija.getKayttajaID());
			lisayslause.setInt(2, projekti.getID());

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

public String haeHelpteksti(int helpid){
	User user=null;
	Connection yhteys = Yhteydenhallinta.avaaYhteys(ajuri, url, kayttaja, salasana);;
	PreparedStatement hakulause = null;
	ResultSet tulosjoukko = null;
	if (yhteys == null) return null;
	try {
			String hakuSql = "select * from help where helpID = ?";
			hakulause = yhteys.prepareStatement(hakuSql);
			hakulause.setInt(1, helpid);
			tulosjoukko = hakulause.executeQuery();

			if (tulosjoukko.next()) {
				return (tulosjoukko.getString(2));
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

/**
 * Edits the info of a customer in the customer table
 * @param asiakas
 * @return true if edit was succesfull, false if not
 */
public boolean muokkaaAsiakas(Asiakas asiakas){
	Connection yhteys= Yhteydenhallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
	if(yhteys==null) return false;
	
	PreparedStatement muutoslause=null;
	try{
		String muutoslauseSql ="update asiakkaat set nimi=?, yritys=?, yhteyshlo=? where asiakasID=?";
		muutoslause = yhteys.prepareStatement(muutoslauseSql);
		muutoslause.setString(1, asiakas.getNimi());
		muutoslause.setString(2, asiakas.getAyritys());
		muutoslause.setString(3, asiakas.getAyhteishenkilo());
		muutoslause.setInt(4, asiakas.getID());
		
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




	


	
	
	
	
}

