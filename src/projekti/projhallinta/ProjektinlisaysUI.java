/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projekti.projhallinta;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import projekti.sql.Tietovarasto;

/**
 * User interface for adding new projects in the database.
 * @author s1200508
 */
public class ProjektinlisaysUI extends JPanel {
	private JTextField nimeaProjektiTextField;
	private JTextField loppupvmTextField;
	private JTextField alkupvmTextField;
	private Projektit projektit;
	private JTextArea seliteTextArea;
	private Tyontekijat tyontekijat;
	private JComboBox projStatusComboBox;
	private Asiakkaat asiakkaat;
	private JComboBox projAsiakasComboBox;
	private Tietovarasto rekisteri= new Tietovarasto();
	
	/**
	 * 
	 * @param projektit
	 * @param tyontekijat
	 * @param asiakkaat
	 */
	

	public ProjektinlisaysUI(Projektit projektit,Tyontekijat tyontekijat, Asiakkaat asiakkaat) {

		this.projektit = projektit;
		this.tyontekijat = tyontekijat;
		this.asiakkaat = asiakkaat;
		

		setLayout(null);

		JLabel nimeaProjektiLabel = new JLabel("Nimea Projekti: ");
		nimeaProjektiLabel.setBounds(10, 38, 105, 14);
		add(nimeaProjektiLabel);

		nimeaProjektiTextField = new JTextField();
		nimeaProjektiTextField.setBounds(125, 35, 170, 20);
		add(nimeaProjektiTextField);
		nimeaProjektiTextField.setColumns(10);

		JComboBox projTyontekijatComboBox = new JComboBox();
		projTyontekijatComboBox.setBounds(305, 35, 200, 20);
		add(projTyontekijatComboBox);
		projTyontekijatComboBox.addItem("Filter");

		JComboBox projVaiheetComboBox = new JComboBox();
		projVaiheetComboBox.setBounds(555, 35, 200, 20);
		add(projVaiheetComboBox);
		projVaiheetComboBox.addItem("Filter");

		DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
		
		loppupvmTextField = new JFormattedTextField(format);
		loppupvmTextField.setToolTipText("DD.MM.YYYY");
		loppupvmTextField.setColumns(10);
		loppupvmTextField.setBounds(125, 94, 170, 20);
		add(loppupvmTextField);

		alkupvmTextField = new JFormattedTextField(format);
		alkupvmTextField.setToolTipText("DD.MM.YYYY");
		alkupvmTextField.setColumns(10);
		alkupvmTextField.setBounds(125, 63, 170, 20);
		add(alkupvmTextField);

		JLabel alkupvmLabel = new JLabel("Alkupaivamaara:");
		alkupvmLabel.setBounds(10, 66, 105, 14);
		add(alkupvmLabel);

		JLabel loppupvmLabel = new JLabel("Loppupaivamaara:");
		loppupvmLabel.setBounds(10, 97, 106, 14);
		add(loppupvmLabel);

		projStatusComboBox = new JComboBox();
		projStatusComboBox.setBounds(125, 125, 170, 20);
		add(projStatusComboBox);
		projStatusComboBox.addItem(ProjektinStatus.TARJOTTU);
		projStatusComboBox.addItem(ProjektinStatus.KAYNNISSA);
		projStatusComboBox.addItem(ProjektinStatus.PAATTYNYT);

		projAsiakasComboBox = new JComboBox();
		projAsiakasComboBox.setBounds(125, 156, 170, 20);
		add(projAsiakasComboBox);
		for(Asiakas asiakas: rekisteri.haeKaikkiAsiakkaat()){
			projAsiakasComboBox.addItem(new Asiakas(asiakas.getID(),asiakas.getNimi(), asiakas.getAyritys(), asiakas.getAyhteishenkilo()));
		}
		

		JList projTyontekijatTextArea_1 = new JList();
		projTyontekijatTextArea_1.setBounds(305, 66, 200, 296);
		add(projTyontekijatTextArea_1);

		JList projVaiheetTextArea = new JList();
		projVaiheetTextArea.setBounds(555, 66, 200, 296);
		add(projVaiheetTextArea);

		JButton tallennaButton = new JButton("Tallenna");
		tallennaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lisaaProjekti();

			}
		});
		tallennaButton.setBounds(555, 373, 127, 23);
		add(tallennaButton);

		JButton peruutaButton = new JButton("Peruuta");
		peruutaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		peruutaButton.setBounds(378, 373, 127, 23);
		add(peruutaButton);

		seliteTextArea = new JTextArea();
		seliteTextArea.setBounds(10, 220, 285, 142);
		add(seliteTextArea);

		JLabel seliteLabel = new JLabel("Selite:");
		seliteLabel.setBounds(10, 197, 91, 14);
		add(seliteLabel);

		JLabel projStatusLabel = new JLabel("Projektin status:");
		projStatusLabel.setBounds(10, 128, 106, 14);
		add(projStatusLabel);

		JLabel projAsiakasLabel = new JLabel("Projektin asiakas:");
		projAsiakasLabel.setBounds(10, 159, 105, 14);
		add(projAsiakasLabel);

		JLabel projTyontekijatTextArea = new JLabel("Projektin tyontekijat:");
		projTyontekijatTextArea.setBounds(305, 11, 200, 14);
		add(projTyontekijatTextArea);

		JLabel projVaiheetLabel = new JLabel("Projektin vaiheet");
		projVaiheetLabel.setBounds(555, 10, 200, 14);
		add(projVaiheetLabel);

		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(530, 67, 2, 295);
		add(separator);
		
		
		nimeaProjektiTextField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	nimeaProjektiTextField.setBackground(Color.WHITE);
            }
        });
		
        alkupvmTextField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                alkupvmTextField.setBackground(Color.WHITE);
            }
        });
        loppupvmTextField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                loppupvmTextField.setBackground(Color.WHITE);
            }
        });
        seliteTextArea.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                seliteTextArea.setBackground(Color.WHITE);
            }
        });
		


	}
	
	/**
	 * Fills the combobox with selectable customers from the database.
	 */
	public void paivitaAsiakkaat(){
		
		for(Asiakas asiakas: rekisteri.haeKaikkiAsiakkaat()){
			if(projAsiakasComboBox.getItemCount() < asiakkaat.getAsiakkaat().size()){
				projAsiakasComboBox.addItem(asiakas);
			}
		}
	}

	/*public int tarkistaId() {

		Projekti projekti = projektit.palautaLista().get(rekisteri.haeKaikkiProjektit().size()-1);
		int id = projekti.getID();
		id++;
		return id;

	}
	*/
	
	/**
	 * Checks if the fields have data entered in them and creates a new project in the database,
	 * also creates a pop-up message letting the user know a new project has been created.
	 */
	public void lisaaProjekti() {
		if (nimeaProjektiTextField.getText() != null
				&& nimeaProjektiTextField.getText().equals("")) {
			nimeaProjektiTextField.setBackground(Color.PINK);
		}
		if (alkupvmTextField.getText() != null
				&& alkupvmTextField.getText().equals("")) {
			alkupvmTextField.setBackground(Color.PINK);
		}
		if (loppupvmTextField.getText() != null
				&& loppupvmTextField.getText().equals("")) {
			loppupvmTextField.setBackground(Color.PINK);
		}
		if (seliteTextArea.getText() != null
				&& seliteTextArea.getText().equals("")) {
			seliteTextArea.setBackground(Color.PINK);
		} else {
			
			String miro = seliteTextArea.getText();

			Projekti projekti = new Projekti(nimeaProjektiTextField.getText(),
					alkupvmTextField.getText(), loppupvmTextField.getText(),
					miro);
		    if(projStatusComboBox.getSelectedIndex() == 1){
		    	projekti.setStatus(ProjektinStatus.KAYNNISSA);
		    }else if(projStatusComboBox.getSelectedIndex() == 2){
		    	projekti.setStatus(ProjektinStatus.PAATTYNYT);
		    }else{
		    	projekti.setStatus(ProjektinStatus.TARJOTTU);
		    }
		    
		    projekti.setAsiakas((Asiakas) projAsiakasComboBox.getSelectedItem());
		    
		    rekisteri.lisaaProjekti(projekti);
		    Projekti nykyinen = null;
		    
		    for(Projekti forloop : rekisteri.haeKaikkiProjektit()){
		    	if(forloop.getNimi().equals(projekti.getNimi()) && forloop.getLoppupvm().equals(projekti.getLoppupvm())){
		    		nykyinen = forloop;
		    	}
		    }
		    	
			rekisteri.lisaaProjektinAsiakas( (Asiakas) projAsiakasComboBox.getSelectedItem(),nykyinen);
			rekisteri.lisaaProjektinStatus(new PStatus(nykyinen.getID(),nykyinen.getID(),projekti.getStatus().toString()));
			
			nimeaProjektiTextField.setText("");
			loppupvmTextField.setText("");
			alkupvmTextField.setText("");
			seliteTextArea.setText("");
			
			JOptionPane.showMessageDialog(this, "Projekti: "+projekti.getNimi()+" luotu onnistuneesti","Projekti luotu",JOptionPane.INFORMATION_MESSAGE);

		}
	}
}