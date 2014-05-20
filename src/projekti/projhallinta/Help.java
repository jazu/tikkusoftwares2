package projekti.projhallinta;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;

public class Help extends JPanel{
	public Help() {
		setLayout(null);
		
		JLabel lblNeedHelpCall = new JLabel("Need help? Call our helpdesk phone!");
		lblNeedHelpCall.setFont(new Font("Comic Sans MS", Font.ITALIC, 40));
		lblNeedHelpCall.setBounds(10, 11, 927, 333);
		add(lblNeedHelpCall);
		
		JLabel label = new JLabel("045 1367115");
		label.setFont(new Font("Comic Sans MS", Font.PLAIN, 80));
		label.setBounds(98, 241, 839, 212);
		add(label);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
