/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projekti.projhallinta;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

/**
 * 
 * @author Jay-Z u
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

		loppupvmTextField = new JTextField();
		loppupvmTextField.setToolTipText("DD:MM:YYYY");
		loppupvmTextField.setColumns(10);
		loppupvmTextField.setBounds(125, 94, 170, 20);
		add(loppupvmTextField);

		alkupvmTextField = new JTextField();
		alkupvmTextField.setToolTipText("DD:MM:YYYY");
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
		for(Asiakas asiakas: asiakkaat.getAsiakkaat()){
			projAsiakasComboBox.addItem(asiakas);
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
		


	}
	public void paivitaAsiakkaat(){
		for(Asiakas asiakas: asiakkaat.getAsiakkaat()){
			projAsiakasComboBox.addItem(asiakas);
		}
	}

	public int tarkistaId() {

		Projekti projekti = projektit.palautaLista().get(projektit.palautaLista().size()-1);
		int id = projekti.getID();
		id++;
		return id;

	}

	public void lisaaProjekti() {
		if (!this.nimeaProjektiTextField.equals("")
				&& !this.loppupvmTextField.equals("")
				&& !this.alkupvmTextField.equals("")) {
			int id = tarkistaId();
			
			String miro = seliteTextArea.getText();

			Projekti projekti = new Projekti(id,
					nimeaProjektiTextField.getText(),
					alkupvmTextField.getText(), loppupvmTextField.getText(),
					miro);
		    if(projStatusComboBox.getSelectedIndex() == 1){
		    	projekti.setStatus(ProjektinStatus.KAYNNISSA);
		    }else if(projStatusComboBox.getSelectedIndex() == 2){
		    	projekti.setStatus(ProjektinStatus.PAATTYNYT);
		    }else{
		    	projekti.setStatus(ProjektinStatus.TARJOTTU);
		    }

			projektit.lisaaProjekti(projekti);
			nimeaProjektiTextField.setText("");
			loppupvmTextField.setText("");
			alkupvmTextField.setText("");
			seliteTextArea.setText("");
			
			JOptionPane.showMessageDialog(this, "Projekti: "+projekti.getNimi()+" luotu IDllä: "+projekti.getID()+" onnistuneesti","Projekti luotu",JOptionPane.INFORMATION_MESSAGE);

		}
	}
}