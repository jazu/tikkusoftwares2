package projekti.sql;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.JLabel;

import projekti.projhallinta.Projektit;
import projekti.projhallinta.Tyontekijat;

public class AsiakasUI extends JFrame {
	private Projektit projektit;
	private Tyontekijat tyontekijat;
	
	public AsiakasUI(Projektit projektit, Tyontekijat tyontekijat) {
		this.projektit = projektit;
		this.tyontekijat = tyontekijat;
		
		JLabel lblNewLabel = new JLabel("New label");
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addContainerGap(378, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addContainerGap(237, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);
	}
}
