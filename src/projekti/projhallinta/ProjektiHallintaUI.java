package projekti.projhallinta;

import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import projekti.sql.Tietovarasto;


public class ProjektiHallintaUI {
	JFrame frame = new JFrame();
    JTabbedPane tabbedPane = new JTabbedPane();
    Projektit projektit = new Projektit();
    Tyontekijat tyontekijat = new Tyontekijat();
    Asiakkaat asiakkaat = new Asiakkaat();
    Tietovarasto rekisteri = new Tietovarasto();
    
    
    ProjektiNakymaUI alkuruutu = new ProjektiNakymaUI(projektit,tyontekijat, tabbedPane);
    ProjektinlisaysUI projlisayspaneeli = new ProjektinlisaysUI(projektit,tyontekijat, asiakkaat);
    VaiheenLuontiUI vaiheidenluontipaneeli = new VaiheenLuontiUI(projektit,tyontekijat);
    TyontekijoidenHallintaUI tyontekhallintapaneeli = new TyontekijoidenHallintaUI(projektit,tyontekijat);
    Help helppaneeli = new Help(); 
    ProjektinMuokkausUI projmuokkauspaneeli = new ProjektinMuokkausUI(projektit,tyontekijat, asiakkaat, tabbedPane);
    AsiakasUI asiakasui = new AsiakasUI(projektit,tyontekijat, asiakkaat);
    private static String kirjautujannimi;
    
    
    
	public ProjektiHallintaUI(String kirjautujannimi) {
		this.kirjautujannimi = kirjautujannimi;

		tabbedPane.setTabPlacement(JTabbedPane.LEFT);
		tabbedPane.add("Projekti Näkymä",alkuruutu );
		tabbedPane.add("Lisaa projekti", projlisayspaneeli);
		tabbedPane.add("Muokkaa projektia", projmuokkauspaneeli);
	    tabbedPane.add("Hallitse vaiheita", vaiheidenluontipaneeli);
		tabbedPane.add("Hallitse tyontekijoita", tyontekhallintapaneeli);
		tabbedPane.add("Asiakkaiden hallinta", asiakasui);
		tabbedPane.add("Help", helppaneeli);
		
        frame.getContentPane().add(tabbedPane);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(950, 500);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
		frame.setTitle("Tikku Project Management - Kirjautunut sisaan kayttajana: "+kirjautujannimi);
		URL url = ClassLoader.getSystemResource("data/32x32kuvake.png");
		Toolkit kit = Toolkit.getDefaultToolkit();
		Image img = kit.createImage(url);
	    frame.setIconImage(img);


        jasutus();
        tabbedPane.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                paivitaKaikki();
   
            }
        });
		projlisayspaneeli.paivitaAsiakkaat();
		alkuruutu.paivitaTiedot();
		
	}
	
	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {	
				new ProjektiHallintaUI(kirjautujannimi);
			}
			
		});
	}
	public void stealthmode(){
		frame.setVisible(false);
	}
	public void paivitaKaikki(){
		alkuruutu.paivitaTiedot();
		projlisayspaneeli.paivitaAsiakkaat();
		projmuokkauspaneeli.paivitaProjektit();
		projmuokkauspaneeli.paivitaAsiakkaat();
		projmuokkauspaneeli.paivitaTiedot();
		vaiheidenluontipaneeli.paivitaProjektit();
		tyontekhallintapaneeli.paivitaProjektit();
		asiakasui.paivitaTiedot();
		vaiheidenluontipaneeli.kentatPaalla();

	}
	public JTabbedPane getTabbedPane() {
		return tabbedPane;
	}



	public void jasutus(){
		Projekti projekti1 = new Projekti(1,"Testiprojekti","12.2.2012","23.5.2012","Testaillaan projektia");
		Projekti projekti2 = new Projekti(2,"Miron Salainen Projekti","14.6.2012","23.8.2012","Makkaraperunat");
		Vaihe vaihe1 = new Vaihe (1,1,"Suunnittelu","13.2.2012","30.2.2012","Projektin Suunnittelu");
		Vaihe vaihe2 = new Vaihe (2,2,"Ohjelmointi","1.3.2012","30.4.2012","Ohjelmoidaan projektia");
		Vaihe vaihe3 = new Vaihe (3,3,"Hiominen","1.5.2012","23.5.2012","Hiotaan miroa");
		Vaihe lolz = new Vaihe (4,1,"Usercaset","1.5.1993","23.5.2012","Luodaan usercaseja");
		Vaihe sofunni = new Vaihe (5,2,"Lisää usercaseja","23.5.2012","23.1.2094","Luodaan enemmän usercaseja");
		Tyontekija tyontekija = new Tyontekija(1,"Robin","Jakara",1998,"Hevoset");
		Tyontekija tyontekija2 = new Tyontekija(2,"Miro","Helenius",1994,"CSS Ohjelmointi");
		Tyontekija tyontekija3 = new Tyontekija(3,"Make","Siwa",2000,"Java Ohjelmointi");
		Tyontekija tyontekija4 = new Tyontekija(4,"Jay","Zu",1993,"Musiikki");
		Asiakas asiakas1 = new Asiakas(0,"Kalle Töyrylä", "Jasun hotpics Oy", "Robin Jakkara");
		Asiakas asiakas2 = new Asiakas(1, "Juuso Korpela", "Jakkaramyynti Oy","Juusi Lampela");
		Asiakas asiakas3 = new Asiakas(2, "Tuomas Salami", "Siwa Oy", "Jaakko Metsola");
	    asiakkaat.lisaaAsiakas(asiakas1);
	    asiakkaat.lisaaAsiakas(asiakas2);
	    asiakkaat.lisaaAsiakas(asiakas3);
	    projekti1.setAsiakas(asiakas3);
	    projekti2.setAsiakas(asiakas2);
		projekti1.lisaaTyontekija(tyontekija3);
		projekti1.lisaaTyontekija(tyontekija4);
		projekti2.lisaaTyontekija(tyontekija);
		projekti2.lisaaTyontekija(tyontekija2);
		tyontekijat.lisaaTyontekija(tyontekija);
		tyontekijat.lisaaTyontekija(tyontekija2);
		tyontekijat.lisaaTyontekija(tyontekija4);
		tyontekijat.lisaaTyontekija(tyontekija3);
		projekti1.setStatus(ProjektinStatus.KAYNNISSA);
		projekti2.setStatus(ProjektinStatus.TARJOTTU);
		projekti1.lisaaVaihe(vaihe1);
		projekti1.lisaaVaihe(vaihe2);
		projekti1.lisaaVaihe(vaihe3);
		projekti2.lisaaVaihe(lolz);
		projekti2.lisaaVaihe(sofunni);
		this.projektit.lisaaProjekti(projekti1);
		this.projektit.lisaaProjekti(projekti2);
		System.out.println(projekti1.getVaiheet().toString());
		System.out.println(projekti1.getTyontekijat().toString());
		System.out.println(projekti2.getTyontekijat().toString());
		System.out.println(rekisteri.haeKaikkiAsiakkaat().toString());
		
	}
	public Projektit getProjektit(){
		return this.projektit;
	}

}
