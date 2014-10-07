package projekti.projhallinta;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import projekti.sql.Tietovarasto;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Help extends JPanel {
	
	JTextArea helpTextarea = new JTextArea();
	Tietovarasto rekisteri = new Tietovarasto();
	public Help() {
		
		setLayout(null);
		

		helpTextarea.setBounds(10, 11, 509, 324);
		helpTextarea.setLineWrap(true);
		helpTextarea.setWrapStyleWord(true);
		add(helpTextarea);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(542, 11, 9, 324);
		add(separator);
		
		JButton projektinakymaButton = new JButton("Projektin n\u00E4kym\u00E4");
		projektinakymaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				helpTextarea.setText(rekisteri.haeHelpteksti(1));
			}
		});
		projektinakymaButton.setBounds(561, 12, 192, 23);
		add(projektinakymaButton);
		
		JButton lisaaprojektiButton = new JButton("Lisaa projekti");
		lisaaprojektiButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				helpTextarea.setText(rekisteri.haeHelpteksti(2));
			}
		});
		lisaaprojektiButton.setBounds(561, 72, 192, 23);
		add(lisaaprojektiButton);
		
		JButton muokaaprojektiButton = new JButton("Muokkaa projektia");
		muokaaprojektiButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				helpTextarea.setText(rekisteri.haeHelpteksti(3));
			}
		});
		muokaaprojektiButton.setBounds(561, 132, 192, 23);
		add(muokaaprojektiButton);
		
		JButton hallitsevaiheitaButton = new JButton("Hallitse vaiheita");
		hallitsevaiheitaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				helpTextarea.setText(rekisteri.haeHelpteksti(4));
			}
		});
		hallitsevaiheitaButton.setBounds(561, 192, 192, 23);
		add(hallitsevaiheitaButton);
		
		JButton hallitsetyontekijoitaButton = new JButton("Hallitse ty\u00F6ntekij\u00F6it\u00E4");
		hallitsetyontekijoitaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				helpTextarea.setText(rekisteri.haeHelpteksti(5));
				
			}
		});
		hallitsetyontekijoitaButton.setBounds(561, 252, 192, 23);
		add(hallitsetyontekijoitaButton);
		
		JButton asiakkaidenhallintaButton = new JButton("Asiakkaiden hallinta");
		asiakkaidenhallintaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				helpTextarea.setText(rekisteri.haeHelpteksti(6));
			}
		});
		asiakkaidenhallintaButton.setBounds(561, 312, 192, 23);
		add(asiakkaidenhallintaButton);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
