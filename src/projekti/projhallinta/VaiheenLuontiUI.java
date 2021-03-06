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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import projekti.sql.Tietovarasto;

/**
 * User interface used in managing the existing phases of a project and creating new ones for projects.
 * 
 */
public class VaiheenLuontiUI extends JPanel {
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
	private JButton tallennaButton;
	private Tietovarasto rekisteri = new Tietovarasto();
	
	/**
	 * 
	 * @param projektit
	 * @param tyontekijat
	 */

	public VaiheenLuontiUI(Projektit projektit, Tyontekijat tyontekijat) {
		setLayout(null);
		this.projektit = projektit;
		this.tyontekijat = tyontekijat;

		vaiheenNimiTextField = new JTextField();
		vaiheenNimiTextField.setColumns(10);
		vaiheenNimiTextField.setBounds(562, 11, 216, 20);
		add(vaiheenNimiTextField);

		JLabel vaiheenNimiLabel = new JLabel("Vaiheen nimi:");
		vaiheenNimiLabel.setBounds(389, 14, 114, 14);
		add(vaiheenNimiLabel);
		
		DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
		

		vaiheenAlkupvmTextField = new JFormattedTextField(format);
		vaiheenAlkupvmTextField.setToolTipText("DD.MM.YYYY");
		vaiheenAlkupvmTextField.setColumns(10);
		vaiheenAlkupvmTextField.setBounds(562, 42, 216, 20);
		add(vaiheenAlkupvmTextField);

		JLabel vaiheenAlkupvmLabel = new JLabel("Vaiheen alkupaivamaara:");
		vaiheenAlkupvmLabel.setBounds(389, 45, 163, 14);
		add(vaiheenAlkupvmLabel);

		vaiheenLoppupvmTextField = new JFormattedTextField(format);
		vaiheenLoppupvmTextField.setToolTipText("DD.MM.YYYY");
		vaiheenLoppupvmTextField.setColumns(10);
		vaiheenLoppupvmTextField.setBounds(562, 73, 216, 20);
		add(vaiheenLoppupvmTextField);

		JLabel vaiheenLoppupvmLabel = new JLabel("Vaiheen loppupaivamaara:");
		vaiheenLoppupvmLabel.setBounds(389, 76, 163, 14);
		add(vaiheenLoppupvmLabel);

		seliteLabel = new JLabel("Selite");
		seliteLabel.setBounds(389, 117, 340, 14);
		add(seliteLabel);

		seliteTextArea = new JTextArea();
		seliteTextArea.setBounds(389, 142, 389, 125);
		add(seliteTextArea);

		uusiButton = new JButton("Uusi");
		uusiButton.setBounds(389, 279, 114, 23);
		add(uusiButton);
		uusiButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				kentatPaalla();
				tyhjennaKentat();
				tallennaButton.setEnabled(true);

			}
		});

		projektiLabel = new JLabel("Projekti:");
		projektiLabel.setBounds(10, 14, 76, 14);
		add(projektiLabel);

		projektiComboBox = new JComboBox();
		projektiComboBox.setBounds(78, 11, 270, 20);
		add(projektiComboBox);

		vaiheLabel = new JLabel("Vaiheet");
		vaiheLabel.setBounds(10, 59, 46, 14);
		add(vaiheLabel);

		JButton poistaButton = new JButton("Poista");
		poistaButton.setBounds(234, 279, 114, 23);
		add(poistaButton);
		poistaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// tallennaVaihe();
				Projekti proj = (Projekti) projektiComboBox.getSelectedItem();

				Vaihe vaih = (Vaihe) vaiheList.getSelectedValue();
								
				rekisteri.poistaVaihe(vaih);

				kentatPaalla();
				tyhjennaKentat();
				paivitaTiedot();
			}
		});

		JButton muokkaaButton = new JButton("Muokkaa");
		muokkaaButton.setBounds(664, 279, 114, 23);
		add(muokkaaButton);
		muokkaaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// tallennaVaihe();
				kentatPaalla();
				tallennaButton.setEnabled(true);
			}
		});

		tallennaButton = new JButton("Tallenna");
		tallennaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// tallennaVaihe();
				lisaaVaihe();
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
		vaiheList.setBounds(10, 85, 340, 182);
		add(vaiheList);
		projektiComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				paivitaTiedot();

			}
		});
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
        seliteTextArea.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                seliteTextArea.setBackground(Color.WHITE);
            }
        });
		kentatPaalla();

	}
	
	/**
	 * Updates the textfields with the correct data from the currently selected phase.
	 */

	public void paivitaVaiheenTiedot() {
		int valittu = vaiheList.getSelectedIndex();
		if (valittu >= 0) {
			Vaihe vaihe = (Vaihe) vaiheList.getModel().getElementAt(valittu);
			vaiheenNimiTextField.setText(vaihe.getNimi());
			vaiheenAlkupvmTextField.setText(vaihe.getAlkupvm());
			vaiheenLoppupvmTextField.setText(vaihe.getLoppupvm());
			seliteTextArea.setText(vaihe.getSelite());

		}

	}
	
	/**
	 * Updates the JList with all the phases in the selected project.
	 */

	public void paivitaTiedot() {
		Projekti projekti = (Projekti) projektiComboBox.getSelectedItem();
		this.vaiheList.setListData(rekisteri.haeProjektinVaiheet(projekti).toArray());
		kentatPaalla();

	}
	
	/**
	 * Updates the project JComboBox with all the projects in the database.
	 */

	public void paivitaProjektit() {
		for (Projekti projekti : rekisteri.haeKaikkiProjektit()) {
			if (projektit.palautaLista() == null) {
			} else {
				if (!apulista.contains(projekti)) {
					apulista.add(projekti);
					projektiComboBox.addItem(projekti);
				}

			}
		}
	}

	/**
	 * Method used to automatically create the sequence numbers on the phases while adding a new phase on a project.
	 * @return sequence number of a phase on a project
	 */
	

	public int tarkistaVaiheNro() {
		int id = 0;

		for (Projekti projekti : rekisteri.haeKaikkiProjektit()) {
			if (projekti.equals(projektiComboBox.getSelectedItem())) {
				Vaihe vaihe = rekisteri.haeProjektinVaiheet(projekti).get(rekisteri.haeProjektinVaiheet(projekti).size() - 1);
				id = vaihe.getVaihenro();
				id++;

			}
		}

		return id;

	}
	
	

	public void tallennaVaihe() {
		int id = tarkistaVaiheNro();
		Vaihe vaihe = new Vaihe(id, vaiheenNimiTextField.getText(),
				vaiheenAlkupvmTextField.getText(),
				vaiheenLoppupvmTextField.getText(), "Selite");

	}
	
	/**
	 * Removes the selected phase from the selected project.
	 */

	public void poistaVaihe() {
		Projekti proj = (Projekti) projektiComboBox.getSelectedItem();
		Vaihe vaihe = (Vaihe) vaiheList.getSelectedValue();
		Vaihe poistettava = null;

		for (Vaihe vaih : proj.getVaiheet()) {
			if (vaih.equals(vaihe)) {
				poistettava = vaih;

			}
		}
		if (poistettava != null) {
			rekisteri.poistaVaihe(vaihe);
		}

	}
	
	/**
	 * Adds a new phase for the selected project, but only if data is entered in all the textfields.
	 */

	public void lisaaVaihe() {
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
		}
		if (seliteTextArea.getText() != null
				&& seliteTextArea.getText().equals("")) {
			seliteTextArea.setBackground(Color.PINK);
		} else {
			int vaiheNro = 1;
			Projekti projektiN = (Projekti) projektiComboBox.getSelectedItem();
			if (rekisteri.haeProjektinVaiheet(projektiN).size() == 0) {
				System.out.println("size == 0");
				vaiheNro = 1;
			} else {
				System.out.println("size != 0");
				vaiheNro = tarkistaVaiheNro();
			}

			Vaihe vaihe = new Vaihe(vaiheNro, vaiheenNimiTextField.getText(),
					vaiheenAlkupvmTextField.getText(),
					vaiheenLoppupvmTextField.getText(),
					seliteTextArea.getText());

			for (Projekti projekti : rekisteri.haeKaikkiProjektit()) {
				if (projekti.equals(projektiComboBox.getSelectedItem())) {
					rekisteri.lisaaVaihe(vaihe, projekti);
				}
				vaiheenNimiTextField.setText("");
				vaiheenAlkupvmTextField.setText("");
				vaiheenLoppupvmTextField.setText("");
				seliteTextArea.setText("");

			}
		}
	}
	/**
	 * Enables all the textfields
	 */

	public void kentatPaalla() {

		vaiheenNimiTextField.setEditable(true);
		vaiheenAlkupvmTextField.setEditable(true);
		vaiheenLoppupvmTextField.setEditable(true);
		seliteTextArea.setEditable(true);

	}
	
	
	/**
	 * Disables all the textfields.
	 */

	public void kentatPois() {
		vaiheenNimiTextField.setEditable(false);
		vaiheenAlkupvmTextField.setEditable(false);
		vaiheenLoppupvmTextField.setEditable(false);
		seliteTextArea.setEditable(false);

	}
	
	/**
	 * Clears all the textfields.
	 */

	public void tyhjennaKentat() {
		vaiheenNimiTextField.setText("");
		vaiheenAlkupvmTextField.setText("");
		vaiheenLoppupvmTextField.setText("");
		seliteTextArea.setText("");
	}
}