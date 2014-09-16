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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import projekti.sql.Tietovarasto;

/**
 * 
 * @author s1200508
 */
public class ProjektiNakymaUI extends JPanel {
	private JTextField projektinNimiTextField;
	private JTextField projektinDeadlineTextField;
	private JTextField projektinStatusTextField;
	private JLabel seliteLabel;
	private JButton uusiButton;
	private JLabel vaiheLabel;
	private Projektit projektit;
	private List<Projekti> apulista = new ArrayList<Projekti>();
	private JList projektiList;
	private JTextArea seliteTextArea;
	private Tyontekijat tyontekijat;
	private JTextField projektinAsiakasTextField;
	private JTabbedPane tb;
	private JButton poistaButton;
	private JButton muokkaaButton;
	private Tietovarasto rekisteri = new Tietovarasto();

	public ProjektiNakymaUI(final Projektit projektit, Tyontekijat tyontekijat, final JTabbedPane tb) {
		this.tb = tb;
		setLayout(null);
		this.projektit = projektit;
		this.tyontekijat = tyontekijat;

		projektinNimiTextField = new JTextField();
		projektinNimiTextField.setColumns(10);
		projektinNimiTextField.setBounds(562, 11, 216, 20);
		add(projektinNimiTextField);

		JLabel vaiheenNimiLabel = new JLabel("Projektin nimi:");
		vaiheenNimiLabel.setBounds(389, 14, 114, 14);
		add(vaiheenNimiLabel);

		projektinDeadlineTextField = new JTextField();
		projektinDeadlineTextField.setColumns(10);
		projektinDeadlineTextField.setBounds(562, 42, 216, 20);
		add(projektinDeadlineTextField);

		JLabel vaiheenAlkupvmLabel = new JLabel("Projektin deadline:");
		vaiheenAlkupvmLabel.setBounds(389, 45, 163, 14);
		add(vaiheenAlkupvmLabel);

		projektinStatusTextField = new JTextField();
		projektinStatusTextField.setColumns(10);
		projektinStatusTextField.setBounds(562, 73, 216, 20);
		add(projektinStatusTextField);

		JLabel vaiheenLoppupvmLabel = new JLabel("Projektin status:");
		vaiheenLoppupvmLabel.setBounds(389, 76, 163, 14);
		add(vaiheenLoppupvmLabel);

		seliteLabel = new JLabel("Selite");
		seliteLabel.setBounds(389, 145, 340, 14);
		add(seliteLabel);

		seliteTextArea = new JTextArea();
		seliteTextArea.setBounds(389, 170, 389, 97);
		add(seliteTextArea);

		uusiButton = new JButton("Uusi");
		uusiButton.setBounds(389, 279, 114, 23);
		add(uusiButton);
		uusiButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tb.setSelectedIndex(1);

			}
		});

		vaiheLabel = new JLabel("Projekti:");
		vaiheLabel.setBounds(10, 14, 100, 14);
		add(vaiheLabel);

		poistaButton = new JButton("Poista");
		poistaButton.setBounds(664, 279, 114, 23);
		add(poistaButton);
		poistaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (JOptionPane.showConfirmDialog(null, "Haluatko varmasti poistaa projektin: "+projektinNimiTextField.getText(), "Projektin poistaminen",
				        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					projektit.poistaProjekti((Projekti) projektiList.getSelectedValue());
					Projekti poistettava = (Projekti) projektiList.getSelectedValue();
					rekisteri.poistaProjektinAsiakas(poistettava.getID());
					rekisteri.poistaProjektinStatus(poistettava.getID());
					rekisteri.poistaProjekti(poistettava.getID());
					paivitaTiedot();
					projektinNimiTextField.setText("");
					projektinStatusTextField.setText("");
					projektinDeadlineTextField.setText("");
					projektinAsiakasTextField.setText("");
					seliteTextArea.setText("");
				} else {

				}


			}
		});

		muokkaaButton = new JButton("Muokkaa");
		muokkaaButton.setBounds(522, 279, 114, 23);
		add(muokkaaButton);
		muokkaaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tb.setSelectedIndex(2);

			}
		});

		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(367, 13, 2, 289);
		add(separator);

		projektiList = new JList();
		projektiList.setBounds(10, 44, 340, 247);
		add(projektiList);
		
		JLabel lblProjektinAsiakas = new JLabel("Projektin asiakas:");
		lblProjektinAsiakas.setBounds(389, 107, 163, 14);
		add(lblProjektinAsiakas);
		
		projektinAsiakasTextField = new JTextField();
		projektinAsiakasTextField.setColumns(10);
		projektinAsiakasTextField.setBounds(562, 104, 216, 20);
		add(projektinAsiakasTextField);
		projektiList.addMouseListener(new MouseListener() {

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
				paivitaProjektinTiedot();

			}

			@Override
			public void mouseReleased(MouseEvent arg0) {

			}
		});
		projektinNimiTextField.setEditable(false);
		projektinDeadlineTextField.setEditable(false);
		projektinStatusTextField.setEditable(false);
		projektinAsiakasTextField.setEditable(false);
		seliteTextArea.setEditable(false);

	}
	public void paivitaProjektinTiedot(){
		int valittu = projektiList.getSelectedIndex();
		if(valittu >= 0){

			Projekti projekti = (Projekti) projektiList.getModel().getElementAt(valittu);
			projektinNimiTextField.setText(projekti.getNimi());
			projektinDeadlineTextField.setText(projekti.getLoppupvm());

			if(rekisteri.haeProjektinStatus(projekti.getID()) != null){
				PStatus pstatus = rekisteri.haeProjektinStatus(projekti.getID());
				projektinStatusTextField.setText(pstatus.getStatus());
			}else{
				projektinStatusTextField.setText("EI LÖYTYNYT");
			}

			if(rekisteri.haeProjektinAsiakas(projekti.getID()) != null){
				Asiakas pasiakas = rekisteri.haeProjektinAsiakas(projekti.getID());
				projektinAsiakasTextField.setText(pasiakas.getNimi());
			}else{
				projektinAsiakasTextField.setText("EI LÖYTYNYT");
			}

			

			seliteTextArea.setText(projekti.getSelite());
			System.out.println(rekisteri.haeKaikkiAsiakkaat());
			
			
		}

		
	}

	public void paivitaTiedot() {
		this.projektiList.setListData(rekisteri.haeKaikkiProjektit().toArray());


	

	}

}
