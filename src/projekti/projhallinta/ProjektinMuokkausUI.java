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
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * 
 * @author s1200508
 */
public class ProjektinMuokkausUI extends JPanel {
	private JTextField loppupvmTextField;
	private JTextField alkupvmTextField;
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

		JComboBox projTyontekijatComboBox = new JComboBox();
		projTyontekijatComboBox.setBounds(305, 36, 200, 20);
		add(projTyontekijatComboBox);
		projTyontekijatComboBox.addItem("Filter");

		JComboBox projVaiheetComboBox = new JComboBox();
		projVaiheetComboBox.setBounds(555, 36, 200, 20);
		add(projVaiheetComboBox);
		projVaiheetComboBox.addItem("Filter");

		loppupvmTextField = new JTextField();
		loppupvmTextField.setColumns(10);
		loppupvmTextField.setBounds(125, 95, 170, 20);
		add(loppupvmTextField);

		alkupvmTextField = new JTextField();
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
		for (Asiakas asiakas : asiakkaat.getAsiakkaat()) {
			projAsiakasTextField.addItem(asiakas);
		}

		projTyontekijatTextArea = new JList();
		projTyontekijatTextArea.setBounds(305, 67, 200, 296);
		add(projTyontekijatTextArea);

		projVaiheetTextArea = new JList();
		projVaiheetTextArea.setBounds(555, 67, 200, 296);
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
		tallennaButton.setBounds(555, 425, 127, 23);
		add(tallennaButton);

		JButton peruutaButton = new JButton("Peruuta");
		peruutaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		peruutaButton.setBounds(378, 425, 127, 23);
		add(peruutaButton);

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
		separator.setBounds(530, 68, 2, 295);
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

	public void paivitaAsiakkaat() {
		for (Asiakas asiakas : asiakkaat.getAsiakkaat()) {
			if (projAsiakasTextField.getItemCount() < asiakkaat.getAsiakkaat()
					.size()) {
				projAsiakasTextField.addItem(asiakas);
			}

		}
	}

	public void paivitaTiedot() {
		Projekti projekti = (Projekti) projektiComboBox.getSelectedItem();
		this.loppupvmTextField.setText(projekti.getLoppupvm());
		this.alkupvmTextField.setText(projekti.getAlkupvm());
		this.seliteTextArea.setText(projekti.getSelite());
		this.projVaiheetTextArea.setListData(projekti.getVaiheet().toArray());
		this.projTyontekijatTextArea.setListData(projekti.getTyontekijat()
				.toArray());
		this.projStatusComboBox.setSelectedItem(projekti.getStatus());
		this.projAsiakasTextField.setSelectedItem(projekti.getAsiakas());

	}

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
			for (Projekti projekti : projektit.palautaLista()) {
				if (projekti.equals(projektiComboBox.getSelectedItem())) {
					projekti.setAlkupvm(alkupvmTextField.getText());
					projekti.setLoppupvm(loppupvmTextField.getText());
					projekti.setSelite(seliteTextArea.getText());
					if (projStatusComboBox.getSelectedIndex() == 1) {
						projekti.setStatus(ProjektinStatus.KAYNNISSA);
					} else if (projStatusComboBox.getSelectedIndex() == 2) {
						projekti.setStatus(ProjektinStatus.PAATTYNYT);
					} else {
						projekti.setStatus(ProjektinStatus.TARJOTTU);
					}
					Asiakas asiakas = (Asiakas) projAsiakasTextField
							.getSelectedItem();
					projekti.setAsiakas(asiakas);

				}
			}
		}
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
}
