package projekti.projhallinta;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;

public class AsiakasUI extends JFrame {
	private JTextField asiakasidTextfield;
	private JTextField asiakkaannimiTextfield;
	private JTextField yhteyshenkilöTextfield;
	private JTextField yrityksennimiTextfield;
	public AsiakasUI() {
		getContentPane().setLayout(null);
		
		JLabel asiakkaatLabel = new JLabel("Asiakkaat");
		asiakkaatLabel.setBounds(10, 11, 150, 14);
		getContentPane().add(asiakkaatLabel);
		
		JList AsiakkaatList = new JList();
		AsiakkaatList.setBounds(10, 36, 193, 244);
		getContentPane().add(AsiakkaatList);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(225, 11, 14, 371);
		getContentPane().add(separator);
		
		JLabel asiakkaanimiLabel = new JLabel("Asiakkaan nimi ");
		asiakkaanimiLabel.setBounds(249, 96, 74, 26);
		getContentPane().add(asiakkaanimiLabel);
		
		JLabel yhteyshenkilöLabel = new JLabel("Yhteyshenkil\u00F6");
		yhteyshenkilöLabel.setBounds(249, 155, 74, 26);
		getContentPane().add(yhteyshenkilöLabel);
		
		JLabel yrityksennimiLabel = new JLabel("Yrityksen nimi");
		yrityksennimiLabel.setBounds(249, 217, 74, 26);
		getContentPane().add(yrityksennimiLabel);
		
		JLabel asiakasidLabel = new JLabel("AsiakasID");
		asiakasidLabel.setBounds(249, 37, 74, 23);
		getContentPane().add(asiakasidLabel);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.BLACK);
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(570, 11, 8, 371);
		getContentPane().add(separator_1);
		
		JButton tallennaButton = new JButton("Tallenna");
		tallennaButton.setBounds(10, 306, 89, 23);
		getContentPane().add(tallennaButton);
		
		asiakasidTextfield = new JTextField();
		asiakasidTextfield.setBounds(356, 38, 193, 20);
		getContentPane().add(asiakasidTextfield);
		asiakasidTextfield.setColumns(10);
		
		asiakkaannimiTextfield = new JTextField();
		asiakkaannimiTextfield.setBounds(356, 99, 193, 20);
		getContentPane().add(asiakkaannimiTextfield);
		asiakkaannimiTextfield.setColumns(10);
		
		yhteyshenkilöTextfield = new JTextField();
		yhteyshenkilöTextfield.setBounds(356, 158, 193, 20);
		getContentPane().add(yhteyshenkilöTextfield);
		yhteyshenkilöTextfield.setColumns(10);
		
		yrityksennimiTextfield = new JTextField();
		yrityksennimiTextfield.setBounds(359, 220, 190, 20);
		getContentPane().add(yrityksennimiTextfield);
		yrityksennimiTextfield.setColumns(10);
	}
}
