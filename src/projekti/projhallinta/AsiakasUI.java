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

import projekti.sql.Tietovarasto;

/**
 * User interface for adding, removing and managing the customers in the database.
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
	private JList asiakasList;
	private JTextArea seliteTextArea;
	private Tyontekijat tyontekijat;
	private Asiakkaat asiakkaat;
	private JButton tallennaButton;
	private Tietovarasto rekisteri = new Tietovarasto();
	
	/**
	 * Creates new AsiakasUI window
	 * @param projektit
	 * @param tyontekijat
	 * @param asiakkaat
	 */

	public AsiakasUI(Projektit projektit, Tyontekijat tyontekijat,final Asiakkaat asiakkaat) {
		
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
		uusiButton.setBounds(389, 378, 114, 23);
		add(uusiButton);
		uusiButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				kentatPaalla();
				tyhjennaKentat();
				tallennaButton.setEnabled(true);
			}
		});

		vaiheLabel = new JLabel("Asiakkaat");
		vaiheLabel.setBounds(10, 14, 124, 14);
		add(vaiheLabel);

		JButton poistaButton = new JButton("Poista");
		poistaButton.setBounds(234, 378, 114, 23);
		add(poistaButton);
		poistaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rekisteri.poistaAsiakas((Asiakas) asiakasList
						.getSelectedValue());
				kentatPaalla();
				tyhjennaKentat();
				paivitaTiedot();
				tallennaButton.setEnabled(true);

			}
		});

		JButton muokkaaButton = new JButton("Muokkaa");
		muokkaaButton.setBounds(663, 378, 114, 23);
		add(muokkaaButton);
		muokkaaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				kentatPaalla();
				tallennaButton.setEnabled(true);

			}
		});

		tallennaButton = new JButton("Tallenna");
		tallennaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lisaaAsiakas();
				paivitaTiedot();
			}
		});

		tallennaButton.setBounds(525, 378, 114, 23);
		add(tallennaButton);

		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(367, 13, 2, 388);
		add(separator);

		asiakasList = new JList();
		asiakasList.setBounds(8, 33, 340, 320);
		add(asiakasList);

		asiakasList.addMouseListener(new MouseListener() {

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
				paivitaAsiakkaanTiedot();
				kentatPois();
				tallennaButton.setEnabled(false);

			}

			@Override
			public void mouseReleased(MouseEvent arg0) {

			}
		});
		vaiheenNimiTextField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				vaiheenNimiTextField.setBackground(Color.WHITE);
			}
		});

		vaiheenAlkupvmTextField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				vaiheenAlkupvmTextField.setBackground(Color.WHITE);
			}
		});
		vaiheenLoppupvmTextField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				vaiheenLoppupvmTextField.setBackground(Color.WHITE);
			}
		});

	}

	public void paivitaAsiakkaanTiedot() {
		int valittu = asiakasList.getSelectedIndex();
		if (valittu >= 0) {
			Asiakas asiakas = (Asiakas) asiakasList.getModel().getElementAt(
					valittu);
			vaiheenNimiTextField.setText(asiakas.getNimi());
			vaiheenAlkupvmTextField.setText(asiakas.getAyritys());
			vaiheenLoppupvmTextField.setText(asiakas.getAyhteishenkilo());

		}

	}
	
	/**
	 * Places all the customers to the JList object from the database.
	 */

	public void paivitaTiedot() {
		this.asiakasList.setListData(rekisteri.haeKaikkiAsiakkaat().toArray());
		kentatPaalla();

	}
/*
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
	
	
	*/
	/**
	 * Generates the customerID for a new customer that is being added.
	 * @return customerID as int.
	 */

	public int tarkistaId() {
		int id = 0;
		int selectedIx = 0;

		for (Asiakas asiakas : asiakkaat.getAsiakkaat()) {

			if (asiakas.equals(asiakasList.getModel().getElementAt(selectedIx))) {
				Asiakas asiakas1 = asiakkaat.getAsiakkaat().get(
						asiakkaat.getAsiakkaat().size() - 1);
				id = asiakas1.getID();
				id++;

			}
			selectedIx++;
		}

		return id;

	}

	/**
	 * Checks if the textfields have data entered and adds a new customer in the database,
	 * if the fields have insufficient data, the fields will turn red.
	 */
	private void lisaaAsiakas() {
		if (vaiheenNimiTextField.getText() != null
				&& vaiheenNimiTextField.getText().equals("")) {
			vaiheenNimiTextField.setBackground(Color.PINK);
		}
		if (vaiheenAlkupvmTextField.getText() != null
				&& vaiheenAlkupvmTextField.getText().equals("")) {
			vaiheenAlkupvmTextField.setBackground(Color.PINK);
		}
		if (vaiheenLoppupvmTextField.getText() != null
				&& vaiheenLoppupvmTextField.getText().equals("")) {
			vaiheenLoppupvmTextField.setBackground(Color.PINK);
		} else {
			int id = 1;
			// int selectedIx = asiakasList.getSelectedIndex();
			// Asiakas zz = (Asiakas)
			// asiakasList.getModel().getElementAt(selectedIx);
			if (asiakkaat.getAsiakkaat().size() == 0) {
				id = 1;
			} else {
				id = tarkistaId();
			}

			Asiakas asiakas = new Asiakas(id, vaiheenNimiTextField.getText(),
					vaiheenAlkupvmTextField.getText(),
					vaiheenLoppupvmTextField.getText());

			vaiheenNimiTextField.setText("");
			vaiheenAlkupvmTextField.setText("");
			vaiheenLoppupvmTextField.setText("");

			rekisteri.lisaaAsiakas(asiakas);
		}
	}
	
	/**
	 * Enables the textfields.
	 */

	public void kentatPaalla() {

		vaiheenNimiTextField.setEditable(true);
		vaiheenAlkupvmTextField.setEditable(true);
		vaiheenLoppupvmTextField.setEditable(true);

	}
	
	/**
	 * Disables the textfields.
	 */

	public void kentatPois() {
		vaiheenNimiTextField.setEditable(false);
		vaiheenAlkupvmTextField.setEditable(false);
		vaiheenLoppupvmTextField.setEditable(false);

	}
	
	/**
	 * Clears the textfields.
	 */

	public void tyhjennaKentat() {
		vaiheenNimiTextField.setText("");
		vaiheenAlkupvmTextField.setText("");
		vaiheenLoppupvmTextField.setText("");
	}

}
