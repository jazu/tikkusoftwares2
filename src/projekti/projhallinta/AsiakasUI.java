/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projekti.projhallinta;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * 
 * @author s1200508
 */
public class AsiakasUI extends JPanel {
	private JTextField vaiheenNimiTextField;
	private JTextField vaiheenAlkupvmTextField;
	private JTextField vaiheenLoppupvmTextField;
	private JLabel seliteLabel;
	private JButton uusiButton;
	private JLabel projektiLabel;
	private JComboBox projektiComboBox;
	private JLabel vaiheLabel;
	private Projektit projektit;
	private List<Projekti> apulista = new ArrayList<Projekti>();
	private JList vaiheList;
	private JTextArea seliteTextArea;
	private Tyontekijat tyontekijat;
	private Asiakkaat asiakkaat;

	public AsiakasUI(Projektit projektit, Tyontekijat tyontekijat, Asiakkaat asiakkaat) {
		setLayout(null);
		this.projektit = projektit;
		this.tyontekijat = tyontekijat;
		this.asiakkaat = asiakkaat;

		vaiheenNimiTextField = new JTextField();
		vaiheenNimiTextField.setColumns(10);
		vaiheenNimiTextField.setBounds(562, 11, 216, 20);
		add(vaiheenNimiTextField);

		JLabel vaiheenNimiLabel = new JLabel("Asiakkaan nimi:");
		vaiheenNimiLabel.setBounds(389, 14, 114, 14);
		add(vaiheenNimiLabel);

		vaiheenAlkupvmTextField = new JTextField();
		vaiheenAlkupvmTextField.setColumns(10);
		vaiheenAlkupvmTextField.setBounds(562, 42, 216, 20);
		add(vaiheenAlkupvmTextField);

		JLabel vaiheenAlkupvmLabel = new JLabel("Asiakkaan yritys:");
		vaiheenAlkupvmLabel.setBounds(389, 45, 163, 14);
		add(vaiheenAlkupvmLabel);

		vaiheenLoppupvmTextField = new JTextField();
		vaiheenLoppupvmTextField.setColumns(10);
		vaiheenLoppupvmTextField.setBounds(562, 73, 216, 20);
		add(vaiheenLoppupvmTextField);

		JLabel vaiheenLoppupvmLabel = new JLabel("Asiakkaan yhteishenkilö:");
		vaiheenLoppupvmLabel.setBounds(389, 76, 163, 14);
		add(vaiheenLoppupvmLabel);



		uusiButton = new JButton("Uusi");
		uusiButton.setBounds(389, 279, 114, 23);
		add(uusiButton);
		uusiButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				kentatPaalla();
				vaiheenNimiTextField.setText("");
				vaiheenAlkupvmTextField.setText("");
				vaiheenLoppupvmTextField.setText("");
			}
		});

		vaiheLabel = new JLabel("Asiakkaat");
		vaiheLabel.setBounds(10, 14, 124, 14);
		add(vaiheLabel);

		JButton poistaButton = new JButton("Poista");
		poistaButton.setBounds(234, 279, 114, 23);
		add(poistaButton);

		JButton muokkaaButton = new JButton("Muokkaa");
		muokkaaButton.setBounds(664, 279, 114, 23);
		add(muokkaaButton);

		JButton tallennaButton = new JButton("Tallenna");
		tallennaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lisaaAsiakas();
				paivitaTiedot();
			}
		});

		tallennaButton.setBounds(528, 279, 114, 23);
		add(tallennaButton);

		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(367, 13, 2, 289);
		add(separator);

		vaiheList = new JList();
		vaiheList.setBounds(8, 33, 340, 182);
		add(vaiheList);

		

		
		vaiheList.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {


			}

			@Override
			public void mouseEntered(MouseEvent arg0) {

			}

			@Override
			public void mouseExited(MouseEvent arg0) {

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				paivitaVaiheenTiedot();

			}

			@Override
			public void mouseReleased(MouseEvent arg0) {

			}
		});
		
		

	}
	public void paivitaVaiheenTiedot(){
		int valittu = vaiheList.getSelectedIndex();
		if(valittu >= 0){
			Asiakas asiakas = (Asiakas) vaiheList.getModel()
					.getElementAt(valittu);
			vaiheenNimiTextField.setText(asiakas.getNimi());
			vaiheenAlkupvmTextField.setText(asiakas.getAyritys());
			vaiheenLoppupvmTextField.setText(asiakas.getAyhteishenkilo());
			
		}

		
	}

	public void paivitaTiedot() {
		this.vaiheList.setListData(asiakkaat.getAsiakkaat().toArray());
		kentatPaalla();

	}

	public void paivitaProjektit() {
		
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

	public int tarkistaId() {
		int id = 0;
		int selectedIx = 0;

		for (Asiakas asiakas : asiakkaat.getAsiakkaat()) {
			
			if (asiakas.equals(vaiheList.getModel().getElementAt(selectedIx))) {
				Asiakas asiakas1 = asiakkaat.getAsiakkaat().get(asiakkaat.getAsiakkaat().size() - 1);
				id = asiakas1.getID();
				id++;
				
			}
			selectedIx++;
		}

		return id;

	}

	public void lisaaAsiakas() {
		int id = 1;
		int selectedIx = vaiheList.getSelectedIndex();
		Asiakkaat zz = (Asiakkaat) vaiheList.getModel().getElementAt(selectedIx);
		if(zz.getAsiakkaat().size() == 0){
			id = 1;
		}else{
		    id = tarkistaId();
		}


		Asiakas asiakas = new Asiakas(id, vaiheenNimiTextField.getText(),
				vaiheenAlkupvmTextField.getText(),
				vaiheenLoppupvmTextField.getText());

			vaiheenNimiTextField.setText("");
			vaiheenAlkupvmTextField.setText("");
			vaiheenLoppupvmTextField.setText("");

		}
	public void kentatPaalla() {
		if(vaiheList.isSelectionEmpty()==true) {
		vaiheenNimiTextField.setEditable(false);
		vaiheenAlkupvmTextField.setEditable(false);
		vaiheenLoppupvmTextField.setEditable(false);
		} else {
			vaiheenNimiTextField.setEditable(true);
			vaiheenAlkupvmTextField.setEditable(true);
			vaiheenLoppupvmTextField.setEditable(true);
		}

	}
	
}
