package projekti.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Yhteydenhallinta {
	
	public static Connection avaaYhteys(String ajuri, String url, String kayttaja, String salasana){
		try{
			Class.forName(ajuri).newInstance();
			return DriverManager.getConnection(url, kayttaja, salasana);
			
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}

		
	}
	public static void suljeYhteys(Connection suljettavaYhteys){
		if(suljettavaYhteys != null){
			try{
				suljettavaYhteys.close();
			}
			catch(Exception e ){
			}
		}
	}
	public static void suljeLause(Statement suljettavaLause){
		if(suljettavaLause != null){
			try{
				suljettavaLause.close();
			}
			catch(Exception e ){
			}
		}
	}
	public static void suljeTulosjoukko(ResultSet suljettavaTulosjoukko){
		if(suljettavaTulosjoukko != null){
			try{
				suljettavaTulosjoukko.close();
			}
			catch(Exception e ){
			}
		}
	}

}
