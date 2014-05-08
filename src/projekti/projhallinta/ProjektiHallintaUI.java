package projekti.projhallinta;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;

public class ProjektiHallintaUI {
	JFrame frame = new JFrame();
    JTabbedPane tabbedPane = new JTabbedPane();
    
    ProjektinlisaysUI projlisayspaneeli = new ProjektinlisaysUI();
    VaiheenLuontiUI vaiheidenluontipaneeli = new VaiheenLuontiUI();
    TyontekijoidenHallintaUI tyontekhallintapaneeli = new TyontekijoidenHallintaUI();
    Help helppaneeli = new Help(); 
    ProjektinMuokkausUI projmuokkauspaneeli = new ProjektinMuokkausUI();

    ProjektinlisaysUI projlisayspaneeli6 = new ProjektinlisaysUI(); //placeholder

    
	public ProjektiHallintaUI() {
		tabbedPane.setTabPlacement(JTabbedPane.LEFT);
		tabbedPane.add("Lisaa projekti", projlisayspaneeli);
		tabbedPane.add("Muokkaa projektia", projmuokkauspaneeli);
		tabbedPane.add("Hallitse vaiheita", vaiheidenluontipaneeli);
		tabbedPane.add("Hallitse tyontekijoita", tyontekhallintapaneeli);
		tabbedPane.add("Asiakkaiden hallinta", projlisayspaneeli6);
		tabbedPane.add("Help", helppaneeli);
		
        frame.getContentPane().add(tabbedPane);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(950, 500);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new ProjektiHallintaUI();
			}
			
		});
	}

}
