/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projekti.tyontekija;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import javax.swing.SwingUtilities;

/**
 *
 * @author s1200508
 */
public class TyontekijaUI extends JFrame{
	
    public TyontekijaUI() {
    	setResizable(false);
    	setSize(630, 480);
    	
    	JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
    	GroupLayout groupLayout = new GroupLayout(getContentPane());
    	groupLayout.setHorizontalGroup(
    		groupLayout.createParallelGroup(Alignment.LEADING)
    			.addGroup(groupLayout.createSequentialGroup()
    				.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 644, GroupLayout.PREFERRED_SIZE)
    				.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    	);
    	groupLayout.setVerticalGroup(
    		groupLayout.createParallelGroup(Alignment.LEADING)
    			.addGroup(groupLayout.createSequentialGroup()
    				.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 483, GroupLayout.PREFERRED_SIZE)
    				.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    	);
    	
    	JPanel panel = new JPanel();
    	tabbedPane.addTab("Tyontekijan nakyma", null, panel, null);
    	panel.setLayout(null);
    	
    	JLabel lblValitseProjekti = new JLabel("Valitse projekti:");
    	lblValitseProjekti.setBounds(10, 11, 109, 14);
    	panel.add(lblValitseProjekti);
    	
    	JComboBox comboBox = new JComboBox();
    	comboBox.setBounds(120, 8, 312, 20);
    	panel.add(comboBox);
    	
    	JComboBox comboBox_1 = new JComboBox();
    	comboBox_1.setBounds(120, 36, 312, 20);
    	panel.add(comboBox_1);
    	
    	JLabel lblValitseTekeminen = new JLabel("Valitse tekeminen:");
    	lblValitseTekeminen.setBounds(10, 39, 109, 14);
    	panel.add(lblValitseTekeminen);
    	
    	JLabel lblNewLabel = new JLabel("Aloitusaika:");
    	lblNewLabel.setBounds(10, 93, 109, 14);
    	panel.add(lblNewLabel);
    	
    	JLabel lblNewLabel_1 = new JLabel("Lopetusaika:");
    	lblNewLabel_1.setBounds(10, 118, 109, 14);
    	panel.add(lblNewLabel_1);
    	
    	JLabel lblNewLabel_2 = new JLabel("Paivamaara:");
    	lblNewLabel_2.setBounds(10, 143, 109, 14);
    	panel.add(lblNewLabel_2);
    	
    	JLabel lblNewLabel_3 = new JLabel("Selite:");
    	lblNewLabel_3.setBounds(10, 186, 109, 14);
    	panel.add(lblNewLabel_3);
    	
    	JTextArea textArea = new JTextArea();
    	textArea.setBounds(10, 211, 422, 124);
    	panel.add(textArea);
    	
    	JFormattedTextField formattedTextField = new JFormattedTextField();
    	formattedTextField.setToolTipText("Aika HH-MM muodossa");
    	formattedTextField.setBounds(120, 90, 312, 20);
    	panel.add(formattedTextField);
    	
    	JFormattedTextField formattedTextField_1 = new JFormattedTextField();
    	formattedTextField_1.setToolTipText("Aika HH-MM muodossa");
    	formattedTextField_1.setBounds(120, 115, 312, 20);
    	panel.add(formattedTextField_1);
    	
    	JFormattedTextField formattedTextField_2 = new JFormattedTextField();
    	formattedTextField_2.setToolTipText("Paivamaara DD-MM-YYYY");
    	formattedTextField_2.setBounds(120, 143, 312, 20);
    	panel.add(formattedTextField_2);
    	
    	JButton btnNewButton = new JButton("Kirjaudu ulos");
    	btnNewButton.setBounds(10, 346, 134, 23);
    	panel.add(btnNewButton);
    	
    	JButton button = new JButton("Tallenna");
    	button.setBounds(154, 346, 134, 23);
    	panel.add(button);
    	
    	JButton button_1 = new JButton("Sulje");
    	button_1.setBounds(298, 346, 134, 23);
    	panel.add(button_1);
    	getContentPane().setLayout(groupLayout);

    }              
    public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable(){

			@Override
			public void run() {
				new TyontekijaUI().setVisible(true);
			}
			
		});
	}
}
