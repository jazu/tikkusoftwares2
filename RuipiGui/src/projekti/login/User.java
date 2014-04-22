package projekti.login;

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
			  onkoAdmin VARCHAR(255) NOT NULL
			);
			
			insert into loginusers (kayttajaID, kayttajanimi, salasana, onkoAdmin) values(1,'Admin','admin','1');
insert into loginusers (kayttajaID, kayttajanimi, salasana, onkoAdmin) values(2,'User','user','0');
insert into loginusers (kayttajaID, kayttajanimi, salasana, onkoAdmin) values(3,'Kulho','superninja','0');
			
			*/
	
	
	public User(int userID, String nimi, String password, int isAdmin) {
		this.userID=userID;
		this.nimi = nimi;
		this.password = password;
		this.isAdmin = isAdmin;
	}
	public int getUserID() {
		return userID;
	}



	public String getNimi() {
		return nimi;
	}



	public String getPassword() {
		return password;
	}



	public int getIsAdmin() {
		return isAdmin;
	}


	@Override
	public String toString() {
		return "Username: "+nimi + " Password: " + password +" isAdmin: "+ isAdmin;
	}




}
