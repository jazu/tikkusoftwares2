

package projekti.projhallinta;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;

public class AsiakasUI extends JPanel {
	private JTextField asiakasidTextfield;
	private JTextField asiakkaannimiTextfield;
	private JTextField yhteyshenkilöTextfield;
	private JTextField yrityksennimiTextfield;
	private Projektit projektit;
	private Tyontekijat tyontekijat;
	
	public AsiakasUI(Projektit projektit, Tyontekijat tyontekijat) {
		this.projektit = projektit;
		this.tyontekijat = tyontekijat;
		
	this.setLayout(null);

		JLabel asiakkaatLabel = new JLabel("Asiakkaat");
		asiakkaatLabel.setBounds(10, 11, 150, 14);
		this.add(asiakkaatLabel);

		JList AsiakkaatList = new JList();
		AsiakkaatList.setBounds(10, 36, 193, 244);
		this.add(AsiakkaatList);

		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(225, 11, 14, 371);
		this.add(separator);

		JLabel asiakkaanimiLabel = new JLabel("Asiakkaan nimi :");
		asiakkaanimiLabel.setBounds(249, 96, 99, 26);
		this.add(asiakkaanimiLabel);

		JLabel yhteyshenkilöLabel = new JLabel("Yhteyshenkil\u00F6:");
		yhteyshenkilöLabel.setBounds(249, 155, 74, 26);
		this.add(yhteyshenkilöLabel);

		JLabel yrityksennimiLabel = new JLabel("Yrityksen nimi:");
		yrityksennimiLabel.setBounds(249, 217, 74, 26);
		this.add(yrityksennimiLabel);

		JLabel asiakasidLabel = new JLabel("AsiakasID:");
		asiakasidLabel.setBounds(249, 37, 74, 23);
		this.add(asiakasidLabel);

		JButton tallennaButton = new JButton("Tallenna");
		tallennaButton.setBounds(10, 306, 89, 23);
		this.add(tallennaButton);

		asiakasidTextfield = new JTextField();
		asiakasidTextfield.setBounds(424, 34, 193, 20);
		this.add(asiakasidTextfield);
		asiakasidTextfield.setColumns(10);

		asiakkaannimiTextfield = new JTextField();
		asiakkaannimiTextfield.setBounds(424, 99, 193, 20);
		this.add(asiakkaannimiTextfield);
		asiakkaannimiTextfield.setColumns(10);

		yhteyshenkilöTextfield = new JTextField();
		yhteyshenkilöTextfield.setBounds(424, 158, 193, 20);
		this.add(yhteyshenkilöTextfield);
		yhteyshenkilöTextfield.setColumns(10);

		yrityksennimiTextfield = new JTextField();
		yrityksennimiTextfield.setBounds(424, 220, 190, 20);
		this.add(yrityksennimiTextfield);
		yrityksennimiTextfield.setColumns(10);
	}
}