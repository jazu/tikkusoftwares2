package projekti.sql;

import java.util.List;
import java.util.Scanner;

import projekti.sql.*;
import projekti.login.*;
import projekti.tyontekija.*;
import projekti.projhallinta.*;

public class Main {
	
	
	public static void tulostaHenkilolista(List<Tyontekija> lista){
		for(Tyontekija tyontekija: lista){
			System.out.println(tyontekija);
		}
	}
	public static void main(String[] args) {
		Tietovarasto rekisteri = new Tietovarasto();
		Scanner lukija = new Scanner(System.in);
		tulostaHenkilolista(rekisteri.haeKaikkiTyontekijat());
		
		System.out.print("Anna useriD: ");
		int userID = Integer.parseInt(lukija.nextLine());
		System.out.print("Anna etunimi: ");
		String nimi = lukija.nextLine();
		System.out.print("Anna sukunimi: ");
		String snimi = lukija.nextLine();
		System.out.print("Anna syntymavuosi: ");
		int svuosi = Integer.parseInt(lukija.nextLine());
		System.out.print("Anna Osaaminen: ");
		String osaaminen = lukija.nextLine();

		
		rekisteri.lisaaTyontekija(new Tyontekija(userID,nimi,snimi,svuosi,osaaminen));
		
		tulostaHenkilolista(rekisteri.haeKaikkiTyontekijat());

	/*	System.out.print("Anna poistettavan ID: ");
		int pnimi = Integer.parseInt(lukija.nextLine());
		rekisteri.poistaKayttaja(pnimi); 
		tulostaHenkilolista(rekisteri.haeKaikki()); */

	}
}



