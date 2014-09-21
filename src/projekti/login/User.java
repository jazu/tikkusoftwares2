package projekti.login;

/**
 * Object used to store information about the users in the database.
 * @author s1200508
 *
 */

public class User {
	private int userID;
	private String nimi;
	private String password;
	private int isAdmin;
	/*
	CREATE TABLE loginusers (
			  kayttajaID INTEGER NOT NULL PRIMARY KEY,
			  kayttajanimi VARCHAR(45) NOT NULL,
			  salasana VARCHAR(45) NOT NULL,
			  onkoAdmin INTEGER(1) NOT NULL
			);
			
			insert into loginusers (kayttajaID, kayttajanimi, salasana, onkoAdmin) values(1,'Admin','admin','1');
insert into loginusers (kayttajaID, kayttajanimi, salasana, onkoAdmin) values(2,'User','user','0');
insert into loginusers (kayttajaID, kayttajanimi, salasana, onkoAdmin) values(3,'Kulho','superninja','0');
			
			*/
	
	/**
	 * Creates a new user with the info given as parameters.
	 * @param userID
	 * @param nimi
	 * @param password
	 * @param isAdmin
	 */
	
	
	public User(int userID, String nimi, String password, int isAdmin) {
		this.userID=userID;
		this.nimi = nimi;
		this.password = password;
		this.isAdmin = isAdmin;
	}
	
	/**
	 * Returns the user ID.
	 * @return user ID
	 */
	public int getUserID() {
		return userID;
	}
	
	/**
	 * Returns the user name.
	 * @return name
	 */



	public String getNimi() {
		return nimi;
	}


	/**
	 * Returns the user password.
	 * @return password
	 */

	public String getPassword() {
		return password;
	}


	/**
	 * Returns 1 if the user is a project manager and 0 if the user is a worker.
	 * @return integer 1 or 0
	 */

	public int getIsAdmin() {
		return isAdmin;
	}


	@Override
	public String toString() {
		return "Username: "+nimi + " Password: " + password +" isAdmin: "+ isAdmin;
	}




}
