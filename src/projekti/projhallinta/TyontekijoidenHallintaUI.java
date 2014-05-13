/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projekti.projhallinta;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author s1200508
 */
public class TyontekijoidenHallintaUI extends JPanel {
	private Projektit projektit;
	private List<Projekti> apulista = new ArrayList<Projekti>();
	private JComboBox projektiComboBox;
	private Tyontekijat tyontekijat;

    public TyontekijoidenHallintaUI(Projektit projektit, Tyontekijat tyontekijat) {
    	setLayout(null);
    	this.projektit = projektit;
    	this.tyontekijat = tyontekijat;
    	
    	JLabel projektiLabel = new JLabel("Projekti: ");
    	projektiLabel.setBounds(10, 11, 74, 14);
    	add(projektiLabel);
    	
    	projektiComboBox = new JComboBox();
    	projektiComboBox.setBounds(94, 8, 547, 20);
    	add(projektiComboBox);
    	
    	JLabel projTyontekijatLabel = new JLabel("Projektin tyontekijat:");
    	projTyontekijatLabel.setBounds(10, 59, 153, 14);
    	add(projTyontekijatLabel);
    	
    	JComboBox projTyontekijatComboBox = new JComboBox();
    	projTyontekijatComboBox.setBounds(10, 84, 201, 20);
    	add(projTyontekijatComboBox);
    	
    	JList projTyontekijatList = new JList();
    	projTyontekijatList.setBounds(10, 114, 201, 249);
    	add(projTyontekijatList);
    	
    	JList vapaatTyontekijatList = new JList();
    	vapaatTyontekijatList.setBounds(440, 114, 201, 249);
    	add(vapaatTyontekijatList);
    	
    	JComboBox vapaatTyontekijatComboBox = new JComboBox();
    	vapaatTyontekijatComboBox.setBounds(440, 84, 201, 20);
    	add(vapaatTyontekijatComboBox);
    	
    	JLabel vapaatTyontekijatLabel = new JLabel("Saatavilla olevat tyontekijat:");
    	vapaatTyontekijatLabel.setBounds(440, 59, 201, 14);
    	add(vapaatTyontekijatLabel);
    	
    	JButton lisaaProjektiinButton = new JButton("Lisaa projektiin");
    	lisaaProjektiinButton.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent arg0) {
    		}
    	});
    	lisaaProjektiinButton.setBounds(221, 178, 209, 23);
    	add(lisaaProjektiinButton);
    	
    	JButton poistaProjektistaButton = new JButton("Poista projektista");
    	poistaProjektistaButton.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent arg0) {
    		}
    	});
    	poistaProjektistaButton.setBounds(221, 212, 209, 23);
    	add(poistaProjektistaButton);
    	
    	JButton valmisButton = new JButton("Valmis");
    	valmisButton.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent arg0) {
    		}
    	});
    	valmisButton.setBounds(221, 246, 209, 23);
    	add(valmisButton);
    	
    	JSeparator separator = new JSeparator();
    	separator.setBounds(221, 114, 209, 2);
    	add(separator);
    	
    	JSeparator separator_1 = new JSeparator();
    	separator_1.setBounds(221, 361, 209, 2);
    	add(separator_1);
    }
    public void paivitaProjektit(){
		for (Projekti projekti : projektit.palautaLista()) {
			if (projektit.palautaLista() == null) {
			} else {
				if (!apulista.contains(projekti)) {
					apulista.add(projekti);
					projektiComboBox.addItem(projekti);
				}

			}
		}
    }
}
