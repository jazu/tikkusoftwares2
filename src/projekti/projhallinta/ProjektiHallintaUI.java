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

/**
 * The main class of the project management user interface. Contains creation of the jTabbedPanes,
 * which include the functionality of the program.
 * @author s1200508
 *
 */

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
		/**
		 * 
		 */
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


        tabbedPane.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                paivitaKaikki();
   
            }
        });
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
	
	/**
	 * Sets visibility of a frame to false.
	 */
	public void stealthmode(){
		frame.setVisible(false);
	}
	
	/**
	 * Updates the data on all the panes.
	 */
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
	/**
	 * returns the JTabbedPane.
	 * @return tabbedPane
	 */
	public JTabbedPane getTabbedPane() {
		return tabbedPane;
	}

}
