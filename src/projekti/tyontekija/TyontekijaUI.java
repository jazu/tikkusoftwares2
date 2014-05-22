/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projekti.tyontekija;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import projekti.login.LoginUI;
import projekti.projhallinta.Projekti;
import projekti.projhallinta.ProjektiHallintaUI;
import projekti.projhallinta.Projektit;

/**
 *
 * @author s1200508
 */
public class TyontekijaUI extends JFrame{
	private static String kirjautujannimi;
	private Projektit projektit;
	private ProjektiHallintaUI phu;
	private JComboBox comboBox;
	private JComboBox comboBox_1;
	private List<Projekti> apulista = new ArrayList<Projekti>();
	private JFormattedTextField formattedTextField;
    private JFormattedTextField formattedTextField2;
    private JFormattedTextField formattedTextField3;
    private JTextArea textArea;
	
    public TyontekijaUI(String kirjautujannimi) {
    	phu = new ProjektiHallintaUI(kirjautujannimi);
    	phu.stealthmode();
        this.projektit = phu.getProjektit();
    	this.kirjautujannimi = kirjautujannimi;
    	setResizable(false);
    	setSize(630, 480);
    	this.setTitle("Kirjautunut sisaan kayttajana: "+kirjautujannimi);
    	
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
    	
    	comboBox = new JComboBox();
    	comboBox.setBounds(120, 8, 312, 20);
    	panel.add(comboBox);
    	
    	comboBox_1 = new JComboBox();
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
    	
    	textArea = new JTextArea();
    	textArea.setBounds(10, 211, 422, 124);
    	panel.add(textArea);
    	
    	formattedTextField = new JFormattedTextField();
    	formattedTextField.setToolTipText("Aika HH-MM muodossa");
    	formattedTextField.setBounds(120, 90, 312, 20);
    	panel.add(formattedTextField);
    	
    	formattedTextField2 = new JFormattedTextField();
    	formattedTextField2.setToolTipText("Aika HH-MM muodossa");
    	formattedTextField2.setBounds(120, 115, 312, 20);
    	panel.add(formattedTextField2);
    	
    	formattedTextField3 = new JFormattedTextField();
    	formattedTextField3.setToolTipText("Paivamaara DD-MM-YYYY muoodossa");
    	formattedTextField3.setBounds(120, 143, 312, 20);
    	panel.add(formattedTextField3);
    	
    	JButton btnNewButton = new JButton("Kirjaudu ulos");
    	btnNewButton.setBounds(10, 346, 134, 23);
    	panel.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
    	
    	JButton button = new JButton("Tallenna");
    	button.setBounds(154, 346, 134, 23);
    	panel.add(button);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				suoritteenTalletus();
			}
		});
    	
    	JButton button_1 = new JButton("Sulje");
    	button_1.setBounds(298, 346, 134, 23);
    	panel.add(button_1);
    	getContentPane().setLayout(groupLayout);
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (JOptionPane.showConfirmDialog(null, "Haluatko varmasti lopettaa", "Lopetus",
				        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					System.exit(0);
				} else {

				}
			}
		});
    	
    	
    	paivitaProjektit();

    }
    public void suoritteenTalletus(){
		JOptionPane.showMessageDialog(this, "Suorite: "+comboBox_1.getSelectedItem()+" talletettu onnistuneesti","Suorite tallennettu",JOptionPane.INFORMATION_MESSAGE);
		this.formattedTextField.setText("");
		this.formattedTextField2.setText("");
		this.formattedTextField3.setText("");
		this.textArea.setText("");
	

    	
    }
	public void paivitaProjektit() {

		for (Projekti projekti : projektit.palautaLista()) {
			if (projektit.palautaLista() == null) {
			} else {
				if (!apulista.contains(projekti)) {
					apulista.add(projekti);
					comboBox.addItem(projekti);
				}

			}
		}
		comboBox_1.addItem("Java-ohjelmointi");
		comboBox_1.addItem("Web-ohjelmointi");
		comboBox_1.addItem("Testaus");
		comboBox_1.addItem("Usercaset");
		

	}
    public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable(){

			@Override
			public void run() {
				new TyontekijaUI(kirjautujannimi).setVisible(true);
				
			}
			
		});
	}
}
