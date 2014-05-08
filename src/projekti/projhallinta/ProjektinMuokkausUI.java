/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projekti.projhallinta;

import java.awt.HeadlessException;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 *
 * @author s1200508
 */
public class ProjektinMuokkausUI extends JPanel {
	private JTextField loppupvmTextField;
	private JTextField alkupvmTextField;

	public ProjektinMuokkausUI()  {
		setLayout(null);
		
		JLabel projektiLabel = new JLabel("Projekti: ");
		projektiLabel.setBounds(10, 39, 105, 14);
		add(projektiLabel);
		
		JComboBox projTyontekijatComboBox = new JComboBox();
		projTyontekijatComboBox.setBounds(305, 36, 200, 20);
		add(projTyontekijatComboBox);
		
		JComboBox projVaiheetComboBox = new JComboBox();
		projVaiheetComboBox.setBounds(555, 36, 200, 20);
		add(projVaiheetComboBox);
		
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
		
		JComboBox projStatusTextField = new JComboBox();
		projStatusTextField.setBounds(125, 126, 170, 20);
		add(projStatusTextField);
		
		JComboBox projAsiakasTextField = new JComboBox();
		projAsiakasTextField.setBounds(125, 157, 170, 20);
		add(projAsiakasTextField);
		
		JTextArea projTyontekijatTextArea = new JTextArea();
		projTyontekijatTextArea.setBounds(305, 67, 200, 296);
		add(projTyontekijatTextArea);
		
		JTextArea projVaiheetTextArea = new JTextArea();
		projVaiheetTextArea.setBounds(555, 67, 200, 296);
		add(projVaiheetTextArea);
		
		JButton muokkaaTyontekijoitaButton = new JButton("Muokkaa tyontekijoita");
		muokkaaTyontekijoitaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		muokkaaTyontekijoitaButton.setBounds(305, 374, 200, 23);
		add(muokkaaTyontekijoitaButton);
		
		JButton muokkaaVaiheitaButton = new JButton("Muokkaa vaiheita");
		muokkaaVaiheitaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		muokkaaVaiheitaButton.setBounds(555, 374, 200, 23);
		add(muokkaaVaiheitaButton);
		
		JButton tallennaButton = new JButton("Tallenna");
		tallennaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
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
		
		JTextArea seliteTextArea = new JTextArea();
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
		
		JComboBox projektiComboBox = new JComboBox();
		projektiComboBox.setBounds(125, 36, 170, 20);
		add(projektiComboBox);
		
	}
}
