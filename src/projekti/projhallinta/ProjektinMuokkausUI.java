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
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import projekti.sql.Tietovarasto;

/**
 *  User interface used to edit the projects.
 * 
 */
public class ProjektinMuokkausUI extends JPanel {
	private JFormattedTextField loppupvmTextField;
	private JFormattedTextField alkupvmTextField;
	private Projektit projektit;
	private JComboBox projektiComboBox;
	private List<Projekti> apulista = new ArrayList<Projekti>();
	private JTextArea seliteTextArea;
	private JList projVaiheetTextArea;
	private Tyontekijat tyontekijat;
	private JList projTyontekijatTextArea;
	private JComboBox projStatusComboBox;
	private Asiakkaat asiakkaat;
	private JComboBox projAsiakasTextField;
	private JTabbedPane tb;
	private Tietovarasto rekisteri = new Tietovarasto();
	/**
	 * 
	 * @param projektit
	 * @param tyontekijat
	 * @param asiakkaat
	 * @param tb
	 */

	public ProjektinMuokkausUI(Projektit projektit, Tyontekijat tyontekijat,
			Asiakkaat asiakkaat, final JTabbedPane tb) {
		this.tb = tb;
		this.asiakkaat = asiakkaat;
		setLayout(null);
		this.projektit = projektit;
		this.tyontekijat = tyontekijat;
		projektiComboBox = new JComboBox();
		projektiComboBox.setBounds(125, 36, 170, 20);
		add(projektiComboBox);

		JLabel projektiLabel = new JLabel("Projekti: ");
		projektiLabel.setBounds(10, 39, 105, 14);
		add(projektiLabel);

		DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
		
		loppupvmTextField = new JFormattedTextField(format);
		loppupvmTextField.setToolTipText("DD.MM.YYYY");
		loppupvmTextField.setColumns(10);
		loppupvmTextField.setBounds(125, 95, 170, 20);
		add(loppupvmTextField);

		alkupvmTextField = new JFormattedTextField(format);
		alkupvmTextField.setToolTipText("DD.MM.YYYY");
		alkupvmTextField.setColumns(10);
		alkupvmTextField.setBounds(125, 64, 170, 20);
		add(alkupvmTextField);

		JLabel alkupvmLabel = new JLabel("Alkupaivamaara:");
		alkupvmLabel.setBounds(10, 67, 105, 14);
		add(alkupvmLabel);

		JLabel loppupvmLabel = new JLabel("Loppupaivamaara:");
		loppupvmLabel.setBounds(10, 98, 106, 14);
		add(loppupvmLabel);

		projStatusComboBox = new JComboBox();
		projStatusComboBox.setBounds(125, 126, 170, 20);
		add(projStatusComboBox);
		projStatusComboBox.addItem(ProjektinStatus.TARJOTTU);
		projStatusComboBox.addItem(ProjektinStatus.KAYNNISSA);
		projStatusComboBox.addItem(ProjektinStatus.PAATTYNYT);

		projAsiakasTextField = new JComboBox();
		projAsiakasTextField.setBounds(125, 157, 170, 20);
		add(projAsiakasTextField);
		for (Asiakas asiakas : rekisteri.haeKaikkiAsiakkaat()) {
			projAsiakasTextField.addItem(asiakas);
		}

		projTyontekijatTextArea = new JList();
		projTyontekijatTextArea.setBounds(305, 36, 200, 327);
		add(projTyontekijatTextArea);

		projVaiheetTextArea = new JList();
		projVaiheetTextArea.setBounds(555, 36, 200, 327);
		add(projVaiheetTextArea);

		JButton muokkaaTyontekijoitaButton = new JButton(
				"Muokkaa tyontekijoita");
		muokkaaTyontekijoitaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tb.setSelectedIndex(4);

			}
		});
		muokkaaTyontekijoitaButton.setBounds(305, 374, 200, 23);
		add(muokkaaTyontekijoitaButton);

		JButton muokkaaVaiheitaButton = new JButton("Muokkaa vaiheita");
		muokkaaVaiheitaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tb.setSelectedIndex(3);
			}
		});
		muokkaaVaiheitaButton.setBounds(555, 374, 200, 23);
		add(muokkaaVaiheitaButton);

		JButton tallennaButton = new JButton("Tallenna");
		tallennaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				muokkaaProjektia();
			}
		});
		tallennaButton.setBounds(471, 408, 127, 23);
		add(tallennaButton);

		seliteTextArea = new JTextArea();
		seliteTextArea.setBounds(10, 221, 285, 142);
		add(seliteTextArea);

		JLabel seliteLabel = new JLabel("Selite:");
		seliteLabel.setBounds(10, 198, 91, 14);
		add(seliteLabel);

		JLabel projStatusLabel = new JLabel("Projektin status:");
		projStatusLabel.setBounds(10, 129, 106, 14);
		add(projStatusLabel);

		JLabel projAsiakasLabel = new JLabel("Projektin asiakas:");
		projAsiakasLabel.setBounds(10, 160, 105, 14);
		add(projAsiakasLabel);

		JLabel projTyontekijatLabel = new JLabel("Projektin tyontekijat:");
		projTyontekijatLabel.setBounds(305, 12, 200, 14);
		add(projTyontekijatLabel);

		JLabel projVaiheetLabel = new JLabel("Projektin vaiheet");
		projVaiheetLabel.setBounds(555, 11, 200, 14);
		add(projVaiheetLabel);

		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(530, 37, 2, 326);
		add(separator);
		projektiComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				paivitaTiedot();
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
	 * Adds all the customers from the database to a JComboBox so they can be used while editing the project.
	 */

	public void paivitaAsiakkaat() {
		for (Asiakas asiakas : asiakkaat.getAsiakkaat()) {
			if (projAsiakasTextField.getItemCount() < asiakkaat.getAsiakkaat()
					.size()) {
				projAsiakasTextField.addItem(asiakas);
			}

		}
	}
	
	/**
	 * Updates the textfields with correct data while selecting a project from the project combobox.
	 */

	public void paivitaTiedot() {
		Projekti projekti = (Projekti) projektiComboBox.getSelectedItem();
		this.loppupvmTextField.setText(projekti.getLoppupvm());
		this.alkupvmTextField.setText(projekti.getAlkupvm());
		this.seliteTextArea.setText(projekti.getSelite());
		this.projVaiheetTextArea.setListData(rekisteri.haeProjektinVaiheet(projekti).toArray());
		this.projTyontekijatTextArea.setListData(rekisteri.haeProjektinTyontekijat(projekti)
				.toArray());
		this.projStatusComboBox.setSelectedItem(projekti.getStatus());
		this.projAsiakasTextField.setSelectedItem(projekti.getAsiakas());

	}
	
	/**
	 * Checks if textfields contain data and edits the selected project in database with
	 * the data given the the textfields.
	 */

	public void muokkaaProjektia() {
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
			for (Projekti projekti : rekisteri.haeKaikkiProjektit()) {
				if (projekti.equals(projektiComboBox.getSelectedItem())) {
					projekti.setAlkupvm(alkupvmTextField.getText());
					projekti.setLoppupvm(loppupvmTextField.getText());
					projekti.setSelite(seliteTextArea.getText());
					rekisteri.muokkaaProjektia(projekti);
					Asiakas asiakas = (Asiakas) projAsiakasTextField
							.getSelectedItem();
					rekisteri.muokkaaProjektinAsiakasta(asiakas, projekti);

					if (projStatusComboBox.getSelectedIndex() == 1) {
						projekti.setStatus(ProjektinStatus.KAYNNISSA);
						PStatus statusz = new PStatus(projekti.getID(),projekti.getID(),"KAYNNISSA");
						rekisteri.muokkaaProjektinStatusta(statusz, projekti);
					} else if (projStatusComboBox.getSelectedIndex() == 2) {
						projekti.setStatus(ProjektinStatus.PAATTYNYT);
						PStatus statusz = new PStatus(projekti.getID(),projekti.getID(),"PAATTYNYT");
						rekisteri.muokkaaProjektinStatusta(statusz, projekti);
					} else {
						projekti.setStatus(ProjektinStatus.TARJOTTU);
						PStatus statusz = new PStatus(projekti.getID(),projekti.getID(),"TARJOTTU");
						rekisteri.muokkaaProjektinStatusta(statusz, projekti);
					}

					projekti.setAsiakas(asiakas);

				}
			}
		}
	}
	
	/**
	 * Updates the projects to project combobox from the database.
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
}
