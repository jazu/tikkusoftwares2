package projekti.projhallinta;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class ProjektiHallintaUI {
	JFrame frame = new JFrame();
    JTabbedPane tabbedPane = new JTabbedPane();
    Projektit projektit = new Projektit();
    Tyontekijat tyontekijat = new Tyontekijat();
    
    ProjektinlisaysUI projlisayspaneeli = new ProjektinlisaysUI(projektit,tyontekijat);
    VaiheenLuontiUI vaiheidenluontipaneeli = new VaiheenLuontiUI(projektit,tyontekijat);
    TyontekijoidenHallintaUI tyontekhallintapaneeli = new TyontekijoidenHallintaUI(projektit,tyontekijat);
    Help helppaneeli = new Help(); 
    ProjektinMuokkausUI projmuokkauspaneeli = new ProjektinMuokkausUI(projektit,tyontekijat);
    AsiakasUI asiakasui = new AsiakasUI(projektit,tyontekijat); //placeholder
    private static String kirjautujannimi;
    
	public ProjektiHallintaUI(String kirjautujannimi) {
		this.kirjautujannimi = kirjautujannimi;

		tabbedPane.setTabPlacement(JTabbedPane.LEFT);
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
		frame.setTitle("Kirjautunut sisaan kayttajana: "+kirjautujannimi);

        jasutus();
        tabbedPane.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                paivitaKaikki();
            }
        });
	}
	
	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {	
				new ProjektiHallintaUI(kirjautujannimi);
			}
			
		});
	}
	public void paivitaKaikki(){
		projmuokkauspaneeli.paivitaProjektit();
		vaiheidenluontipaneeli.paivitaProjektit();
		tyontekhallintapaneeli.paivitaProjektit();
		
	}
	public void jasutus(){
		Projekti projekti1 = new Projekti(1,"Testiprojekti","12.2.2012","23.5.2012","Testaillaan projektia");
		Projekti projekti2 = new Projekti(2,"Miron Salainen Projekti","14.6.2012","23.8.2012","Makkaraperunat");
		Vaihe vaihe1 = new Vaihe (1,"Suunnittelu","13.2.2012","30.2.2012","Projektin Suunnittelu");
		Vaihe vaihe2 = new Vaihe (2,"Ohjelmointi","1.3.2012","30.4.2012","Ohjelmoidaan projektia");
		Vaihe vaihe3 = new Vaihe (3,"Hiominen","1.5.2012","23.5.2012","Hiotaan miroa");
		Vaihe lolz = new Vaihe (1,"Usercaset","1.5.1993","23.5.2012","Luodaan usercaseja");
		Vaihe sofunni = new Vaihe (2,"Lis‰‰ usercaseja","23.5.2012","23.1.2094","Luodaan enemm‰n usercaseja");
		Tyontekija tyontekija = new Tyontekija(1,"Robin","Jakara",1998,"Hevoset");
		Tyontekija tyontekija2 = new Tyontekija(2,"Miro","Helenius",1994,"CSS Ohjelmointi");
		Tyontekija tyontekija3 = new Tyontekija(3,"Make","Siwa",2000,"Java Ohjelmointi");
		Tyontekija tyontekija4 = new Tyontekija(4,"Jay","Zu",1993,"Musiikki");
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
		
	}

}
