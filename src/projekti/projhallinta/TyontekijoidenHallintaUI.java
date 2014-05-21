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
	private JList projTyontekijatList;
	private JList vapaatTyontekijatList;
	private List<Tyontekija> jasu = new ArrayList<Tyontekija>();

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
    	projTyontekijatComboBox.addItem("Filter");
    	
    	projTyontekijatList = new JList();
    	projTyontekijatList.setBounds(10, 114, 201, 249);
    	add(projTyontekijatList);
    	
    	vapaatTyontekijatList = new JList();
    	vapaatTyontekijatList.setBounds(440, 114, 201, 249);
    	add(vapaatTyontekijatList);
    	
    	JComboBox vapaatTyontekijatComboBox = new JComboBox();
    	vapaatTyontekijatComboBox.setBounds(440, 84, 201, 20);
    	add(vapaatTyontekijatComboBox);
    	vapaatTyontekijatComboBox.addItem("Filter");
    	
    	JLabel vapaatTyontekijatLabel = new JLabel("Saatavilla olevat tyontekijat:");
    	vapaatTyontekijatLabel.setBounds(440, 59, 201, 14);
    	add(vapaatTyontekijatLabel);
    	
    	JButton lisaaProjektiinButton = new JButton("Lisaa projektiin");
    	lisaaProjektiinButton.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent arg0) {
    			lisaaProjektiin();
    			paivita();
    		}
    	});
    	lisaaProjektiinButton.setBounds(221, 178, 209, 23);
    	add(lisaaProjektiinButton);
    	
    	JButton poistaProjektistaButton = new JButton("Poista projektista");
    	poistaProjektistaButton.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent arg0) {
    			poistaProjektista();
    			paivita();
    		}
    	});
    	poistaProjektistaButton.setBounds(221, 212, 209, 23);
    	add(poistaProjektistaButton);
    	

    	
    	JSeparator separator = new JSeparator();
    	separator.setBounds(221, 114, 209, 2);
    	add(separator);
    	
    	JSeparator separator_1 = new JSeparator();
    	separator_1.setBounds(221, 361, 209, 2);
    	add(separator_1);
		projektiComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				paivitaAluksi();
				paivita();
			}
		});
		
		
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
    public void paivitaAluksi(){
    	Projekti proj = (Projekti) projektiComboBox.getSelectedItem();
    	List<Tyontekija> poistettavat = new ArrayList<Tyontekija>();
    	for(Projekti projekti : projektit.palautaLista()){
        	for(Tyontekija tyontekija : projekti.getTyontekijat()){
        		if(!jasu.contains(tyontekija)){
    				jasu.add(tyontekija);
        		}
        	}
    		
    	}
    	

		for(Tyontekija jasuttaja : jasu){
			
			if(proj.getTyontekijat().contains(jasuttaja)){
				poistettavat.add(jasuttaja);
			}	
		}
    	jasu.removeAll(poistettavat);
    	System.out.println(jasu.toString()+" PAIVITAALUKSI");
    	this.projTyontekijatList.setListData(proj.getTyontekijat().toArray());
    	this.vapaatTyontekijatList.setListData(jasu.toArray());
    	
    	
    }
    public void paivita(){
    	Projekti proj = (Projekti) projektiComboBox.getSelectedItem();
    	this.projTyontekijatList.setListData(proj.getTyontekijat().toArray());
    	this.vapaatTyontekijatList.setListData(jasu.toArray());
    	
    }
    public void lisaaProjektiin(){
    	Projekti proj = (Projekti) projektiComboBox.getSelectedItem();

    	int num = vapaatTyontekijatList.getSelectedIndex();
        if(!proj.getTyontekijat().contains(jasu.get(num))){
        	proj.lisaaTyontekija(jasu.get(num));
        	jasu.remove(num);
        	
        }


    	
    }

    public void poistaProjektista(){
    	Projekti proj = (Projekti) projektiComboBox.getSelectedItem();
    	int num = projTyontekijatList.getSelectedIndex();
    	Tyontekija tyontekija = proj.getTyontekijat().get(num);
    	proj.poistaTyontekija(tyontekija);
    	jasu.add(tyontekija);
    	
    	
    }
    
}
