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

import projekti.sql.Tietovarasto;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * The user interface used to manage the workers in the projects.
 * 
 */
public class TyontekijoidenHallintaUI extends JPanel {
	private Projektit projektit;
	private List<Projekti> apulista = new ArrayList<Projekti>();
	private JComboBox projektiComboBox;
	private Tyontekijat tyontekijat;
	private JList projTyontekijatList;
	private JList vapaatTyontekijatList;
	private List<Tyontekija> tyontekijalista = new ArrayList<Tyontekija>();
	private Tietovarasto rekisteri = new Tietovarasto();
	
	/**
	 * 
	 * @param projektit
	 * @param tyontekijat
	 */

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
    	
    	projTyontekijatList = new JList();
    	projTyontekijatList.setBounds(10, 84, 201, 279);
    	add(projTyontekijatList);
    	
    	vapaatTyontekijatList = new JList();
    	vapaatTyontekijatList.setBounds(440, 84, 201, 279);
    	add(vapaatTyontekijatList);
    	
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
    	lisaaProjektiinButton.setBounds(221, 97, 209, 23);
    	add(lisaaProjektiinButton);
    	
    	JButton poistaProjektistaButton = new JButton("Poista projektista");
    	poistaProjektistaButton.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent arg0) {
    			poistaProjektista();
    			paivita();
    		}
    	});
    	poistaProjektistaButton.setBounds(221, 327, 209, 23);
    	add(poistaProjektistaButton);
    	

    	
    	JSeparator separator = new JSeparator();
    	separator.setBounds(221, 84, 209, 2);
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
    /**
     * Updates the available projects from the database to a JComboBox.
     */
    public void paivitaProjektit(){
		for (Projekti projekti : rekisteri.haeKaikkiProjektit()) {
			if (rekisteri.haeKaikkiProjektit() == null) {
			} else {
				if (!apulista.contains(projekti)) {
					apulista.add(projekti);
					projektiComboBox.addItem(projekti);
				}

			}
		}
    }
    /**
     * Updates the JLists containing the workers who are available and who are working in the selected project for the first
     * time the window is loaded.
     */
    public void paivitaAluksi(){
    	Projekti proj = (Projekti) projektiComboBox.getSelectedItem();
    	List<Tyontekija> poistettavat = new ArrayList<Tyontekija>();
    	for(Projekti projekti : rekisteri.haeKaikkiProjektit()){
        	for(Tyontekija tyontekija : rekisteri.haeProjektinTyontekijat(projekti)){
        		if(!tyontekijalista.contains(tyontekija)){
    				tyontekijalista.add(tyontekija);
        		}
        	}
    		
    	}
    	

		for(Tyontekija jasuttaja : tyontekijalista){
			
			if(rekisteri.haeProjektinTyontekijat(proj).contains(jasuttaja)){
				poistettavat.add(jasuttaja);
			}	
		}
    	tyontekijalista.removeAll(poistettavat);
    	System.out.println(tyontekijalista.toString()+" PAIVITAALUKSI");
    	this.projTyontekijatList.setListData(rekisteri.haeProjektinTyontekijat(proj).toArray());
    	this.vapaatTyontekijatList.setListData(rekisteri.haeKaikkiTyontekijat().toArray());
    	
    	
    }
    /**
     * Updates the workers in the JLists after changes have been made.
     */
    public void paivita(){
    	Projekti proj = (Projekti) projektiComboBox.getSelectedItem();
    	this.projTyontekijatList.setListData(rekisteri.haeProjektinTyontekijat(proj).toArray());
    	this.vapaatTyontekijatList.setListData(rekisteri.haeKaikkiTyontekijat().toArray());
    	
    }
    
    /**
     * Adds the selected worker in the selected project if the project doesn't already contain the worker.
     */
    public void lisaaProjektiin(){
    	Projekti proj = (Projekti) projektiComboBox.getSelectedItem();
    	Tyontekija tyontekija = (Tyontekija) vapaatTyontekijatList.getSelectedValue();
        if(!rekisteri.haeProjektinTyontekijat(proj).contains(tyontekija)){
     //   	rekisteri.muokkaaProjektinTyontekijaa(tyontekija, proj);
        	rekisteri.lisaaTyontekijaProjektiin(tyontekija, proj);
        }


    	
    }
    /**
     * Removes the selected worker from the selected project
     */

    public void poistaProjektista(){
    	Projekti projekti = (Projekti) projektiComboBox.getSelectedItem();
    	Tyontekija tyontekija = (Tyontekija) projTyontekijatList.getSelectedValue();
    	rekisteri.poistaTyontekijaProjektista(tyontekija, projekti);
    	
    	
    }
    
}
