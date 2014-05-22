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

/**
 * 
 * @author s1200508
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

		vaiheenAlkupvmTextField = new JTextField();
		vaiheenAlkupvmTextField.setColumns(10);
		vaiheenAlkupvmTextField.setBounds(562, 42, 216, 20);
		add(vaiheenAlkupvmTextField);

		JLabel vaiheenAlkupvmLabel = new JLabel("Vaiheen alkupaivamaara:");
		vaiheenAlkupvmLabel.setBounds(389, 45, 163, 14);
		add(vaiheenAlkupvmLabel);

		vaiheenLoppupvmTextField = new JTextField();
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

				proj.poistaVaihe((Vaihe) vaiheList.getSelectedValue());

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

	public void paivitaTiedot() {
		Projekti projekti = (Projekti) projektiComboBox.getSelectedItem();
		this.vaiheList.setListData(projekti.getVaiheet().toArray());
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

		for (Projekti projekti : projektit.palautaLista()) {
			if (projekti.equals(projektiComboBox.getSelectedItem())) {
				Vaihe vaihe = projekti.getVaiheet().get(
						projekti.getVaiheet().size() - 1);
				id = vaihe.getId();
				id++;

			}
		}

		return id;

	}

	public void tallennaVaihe() {
		int id = tarkistaId();
		Vaihe vaihe = new Vaihe(id, vaiheenNimiTextField.getText(),
				vaiheenAlkupvmTextField.getText(),
				vaiheenLoppupvmTextField.getText(), "Selite");

	}

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
			proj.poistaVaihe(vaihe);
		}

	}

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
			int id = 1;
			Projekti zz = (Projekti) projektiComboBox.getSelectedItem();
			if (zz.getVaiheet().size() == 0) {
				id = 1;
			} else {
				id = tarkistaId();
			}

			Vaihe vaihe = new Vaihe(id, vaiheenNimiTextField.getText(),
					vaiheenAlkupvmTextField.getText(),
					vaiheenLoppupvmTextField.getText(),
					seliteTextArea.getText());

			for (Projekti projekti : projektit.palautaLista()) {
				if (projekti.equals(projektiComboBox.getSelectedItem())) {
					projekti.lisaaVaihe(vaihe);
				}
				vaiheenNimiTextField.setText("");
				vaiheenAlkupvmTextField.setText("");
				vaiheenLoppupvmTextField.setText("");
				seliteTextArea.setText("");

			}
		}
	}

	public void kentatPaalla() {

		vaiheenNimiTextField.setEditable(true);
		vaiheenAlkupvmTextField.setEditable(true);
		vaiheenLoppupvmTextField.setEditable(true);
		seliteTextArea.setEditable(true);

	}

	public void kentatPois() {
		vaiheenNimiTextField.setEditable(false);
		vaiheenAlkupvmTextField.setEditable(false);
		vaiheenLoppupvmTextField.setEditable(false);
		seliteTextArea.setEditable(false);

	}

	public void tyhjennaKentat() {
		vaiheenNimiTextField.setText("");
		vaiheenAlkupvmTextField.setText("");
		vaiheenLoppupvmTextField.setText("");
		seliteTextArea.setText("");
	}
}