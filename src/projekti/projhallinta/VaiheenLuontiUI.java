/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projekti.projhallinta;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JList;

/**
 *
 * @author s1200508
 */
public class VaiheenLuontiUI extends JPanel {
	private JTextField vaiheenNimiTextField;
	private JTextField vaiheenAlkupvmTextField;
	private JTextField vaiheenLoppupvmTextField;
	private JLabel seliteLabel;
	private JButton uusiButton;
	private JLabel projektiLabel;
	private JComboBox projektiComboBox;
	private JLabel vaiheLabel;
	
    public VaiheenLuontiUI() {
    	setLayout(null);
    	
    	vaiheenNimiTextField = new JTextField();
    	vaiheenNimiTextField.setColumns(10);
    	vaiheenNimiTextField.setBounds(562, 11, 216, 20);
    	add(vaiheenNimiTextField);
    	
    	JLabel vaiheenNimiLabel = new JLabel("Vaiheen nimi:");
    	vaiheenNimiLabel.setBounds(389, 14, 114, 14);
    	add(vaiheenNimiLabel);
    	
    	vaiheenAlkupvmTextField = new JTextField();
    	vaiheenAlkupvmTextField.setColumns(10);
    	vaiheenAlkupvmTextField.setBounds(562, 42, 216, 20);
    	add(vaiheenAlkupvmTextField);
    	
    	JLabel vaiheenAlkupvmLabel = new JLabel("Vaiheen alkupaivamaara:");
    	vaiheenAlkupvmLabel.setBounds(389, 45, 163, 14);
    	add(vaiheenAlkupvmLabel);
    	
    	vaiheenLoppupvmTextField = new JTextField();
    	vaiheenLoppupvmTextField.setColumns(10);
    	vaiheenLoppupvmTextField.setBounds(562, 73, 216, 20);
    	add(vaiheenLoppupvmTextField);
    	
    	JLabel vaiheenLoppupvmLabel = new JLabel("Vaiheen loppupaivamaara:");
    	vaiheenLoppupvmLabel.setBounds(389, 76, 163, 14);
    	add(vaiheenLoppupvmLabel);
    	
    	seliteLabel = new JLabel("Selite");
    	seliteLabel.setBounds(389, 117, 340, 14);
    	add(seliteLabel);
    	
    	JTextArea seliteTextArea = new JTextArea();
    	seliteTextArea.setBounds(389, 142, 389, 125);
    	add(seliteTextArea);
    	
    	uusiButton = new JButton("Uusi");
    	uusiButton.setBounds(389, 279, 114, 23);
    	add(uusiButton);
    	
    	projektiLabel = new JLabel("Projekti:");
    	projektiLabel.setBounds(10, 14, 76, 14);
    	add(projektiLabel);
    	
    	projektiComboBox = new JComboBox();
    	projektiComboBox.setBounds(78, 11, 270, 20);
    	add(projektiComboBox);
    	
    	vaiheLabel = new JLabel("Vaiheet");
    	vaiheLabel.setBounds(10, 59, 46, 14);
    	add(vaiheLabel);
    	
    	JButton poistaButton = new JButton("Poista");
    	poistaButton.setBounds(234, 279, 114, 23);
    	add(poistaButton);
    	
    	JButton muokkaaButton = new JButton("Muokkaa");
    	muokkaaButton.setBounds(664, 279, 114, 23);
    	add(muokkaaButton);
    	
    	JButton tallennaButton = new JButton("Tallenna");
    	tallennaButton.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent arg0) {
    		}
    	});
    	tallennaButton.setBounds(528, 279, 114, 23);
    	add(tallennaButton);
    	
    	JSeparator separator = new JSeparator();
    	separator.setOrientation(SwingConstants.VERTICAL);
    	separator.setBounds(367, 13, 2, 289);
    	add(separator);
    	
    	JList vaiheList = new JList();
    	vaiheList.setBounds(10, 85, 340, 182);
    	add(vaiheList);
    }            
}
