package projekti.login;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import projekti.projhallinta.ProjektiHallintaUI;
import projekti.sql.Tietovarasto;
import projekti.tyontekija.TyontekijaUI;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * The user interface used while logging in the application.
 * 
 */
public class LoginUI extends javax.swing.JFrame {
	

	private Tietovarasto rekisteri = new Tietovarasto();
	private String kirjautujannimi = "";

	/**
	 * Creates new form Login
	 * @throws IOException 
	 */
	public LoginUI() throws IOException {
		this.setTitle("Tikku Project Management");
		initComponents();
		this.setLocation(500, 200);
		URL url = ClassLoader.getSystemResource("data/32x32kuvake.png");
		Toolkit kit = Toolkit.getDefaultToolkit();
		Image img = kit.createImage(url);
		this.setIconImage(img);

	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 * @throws IOException 
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	
/**
 * Method used to check if the user has entered his details correctly and check if the program should
 * open the project management view or the worker view.
 */
	private void kirjaudu() {
		
		System.out.println(rekisteri.haeKaikkiProjektit());
		


		try {

			User haettava = rekisteri.haeKayttaja(kayttajanimiTextField
					.getText());
			if (haettava == null) {
				JOptionPane.showMessageDialog(null,
						"Virheellinen Käyttäjänimi tai Salasana.", "Error",
						JOptionPane.ERROR_MESSAGE);

			}

			else if (haettava.getPassword().equals(salasanaTextField.getText())
					&& haettava.getIsAdmin() == 1) {
				this.kirjautujannimi = kayttajanimiTextField.getText();
				this.dispose();
				ProjektiHallintaUI admin = new ProjektiHallintaUI(
						kirjautujannimi);
			} else if (haettava.getPassword().equals(
					salasanaTextField.getText())
					&& haettava.getIsAdmin() == 0) {
				this.kirjautujannimi = kayttajanimiTextField.getText();

				this.dispose();
				TyontekijaUI tyontekija = new TyontekijaUI(kirjautujannimi);
				tyontekija.setVisible(true);

			} else {
				JOptionPane.showMessageDialog(null,
						"Virheellinen Käyttäjänimi tai Salasana.", "Error",
						JOptionPane.ERROR_MESSAGE);
			}

			/*
			 * if (jTextField1.getText().equals(users.get(i)) &&
			 * jPasswordField1.getText().equals(passwords.get(i)) &&
			 * admincheck.get(i) == 0 ) {
			 * 
			 * 
			 * }
			 * 
			 * else if (jTextField1.getText().equals(users.get(i)) &&
			 * jPasswordField1.getText().equals(passwords.get(i)) &&
			 * admincheck.get(i) == 1) {
			 * 
			 * this.dispose(); ProjektiHallinta admin = new ProjektiHallinta();
			 * admin.setVisible(true); break;
			 * 
			 * }/* else if (!jTextField1.getText().equals(users.get(i)) &&
			 * !jPasswordField1.getText().equals(passwords.get(i))) { }
			 * JOptionPane.showMessageDialog(null,
			 * "Virheellinen Käyttäjänimi tai Salasana.", "Error",
			 * JOptionPane.ERROR_MESSAGE); break;
			 */

		} catch (IndexOutOfBoundsException e) {
			JOptionPane.showMessageDialog(null,
					"Tietokantaan ei saatu yhteyttä.", "Error",
					JOptionPane.ERROR_MESSAGE);
		}

	}
	
	/**
	 * Quits the program
	 */

	private void lopetus() {
		System.exit(0);

	}

	private void suoritaHaku() {
		JTextArea tulos = new JTextArea(20, 40);
		for (User user : rekisteri.haeKaikkiKayttajat()) {
			tulos.append(user + "\n");
		}
		JOptionPane.showMessageDialog(this, new JScrollPane(tulos), "tulos",
				JOptionPane.PLAIN_MESSAGE);
	}

	/*
	 * private void connectSql() { Connection conn = null; try { conn =
	 * DriverManager.getConnection( "jdbc:mysql://localhost/tikkusoftwares",
	 * "admin", "shibainu");
	 * 
	 * String henkilonHakuSql = "select * from kayttajat"; PreparedStatement
	 * henkilonHaku = conn .prepareStatement(henkilonHakuSql);
	 * 
	 * ResultSet output = henkilonHaku.executeQuery();
	 * 
	 * while (output.next()) { admincheck.add(output.getInt(1));
	 * users.add(output.getString(2)); passwords.add(output.getString(3)); }
	 * output.close();
	 * 
	 * } catch (SQLException ex) { System.out.println("SQLException: " +
	 * ex.getMessage()); System.out.println("SQLState: " + ex.getSQLState());
	 * System.out.println("VendorError: " + ex.getErrorCode()); }
	 * 
	 * }
	 */
	private void initComponents() {

		loginLabel = new javax.swing.JLabel();
		kirjauduButton = new javax.swing.JButton();
		lopetaButton = new javax.swing.JButton();
		kayttajanimiTextField = new javax.swing.JTextField();
		kayttajanimiLabel = new javax.swing.JLabel();
		salasanaLabel = new javax.swing.JLabel();
		salasanaTextField = new javax.swing.JPasswordField();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		loginLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
		loginLabel.setText("Login");

		kirjauduButton.setText("Kirjaudu");
		kirjauduButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				kirjauduButtonActionPerformed(evt);
				kirjaudu();
			}
		});

		salasanaTextField.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				kirjaudu();

			}
		});
		lopetaButton.setText("Lopeta");
		lopetaButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				lopetaButtonActionPerformed(evt);
				lopetus();
			}
		});

		kayttajanimiTextField
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						kayttajanimiTextFieldActionPerformed(evt);
					}
				});

		kayttajanimiLabel.setText("Käyttäjänimi:");

		salasanaLabel.setText("Salasana:");

		salasanaTextField
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						salasanaTextFieldActionPerformed(evt);
					}
				});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						javax.swing.GroupLayout.Alignment.TRAILING,
						layout.createSequentialGroup()
								.addContainerGap(
										javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addComponent(kirjauduButton)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(lopetaButton).addGap(32, 32, 32))
				.addGroup(
						layout.createSequentialGroup()
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(
														layout.createSequentialGroup()
																.addGap(19, 19,
																		19)
																.addComponent(
																		loginLabel,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		290,
																		javax.swing.GroupLayout.PREFERRED_SIZE))
												.addGroup(
														layout.createSequentialGroup()
																.addGap(29, 29,
																		29)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING,
																				false)
																				.addComponent(
																						kayttajanimiLabel)
																				.addComponent(
																						kayttajanimiTextField)
																				.addComponent(
																						salasanaLabel)
																				.addComponent(
																						salasanaTextField,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						331,
																						Short.MAX_VALUE))))
								.addContainerGap(
										javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addComponent(loginLabel,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										55,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(18, 18, 18)
								.addComponent(kayttajanimiLabel)
								.addGap(2, 2, 2)
								.addComponent(kayttajanimiTextField,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addComponent(salasanaLabel)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(salasanaTextField,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(kirjauduButton)
												.addComponent(lopetaButton))
								.addContainerGap()));

		pack();
	}// </editor-fold>

	private void kayttajanimiTextFieldActionPerformed(
			java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void kirjauduButtonActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void salasanaTextFieldActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void lopetaButtonActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {
		/* Set the Nimbus look and feel */
		// <editor-fold defaultstate="collapsed"
		// desc=" Look and feel setting code (optional) ">
		/*
		 * If Nimbus (introduced in Java SE 6) is not available, stay with the
		 * default look and feel. For details see
		 * http://download.oracle.com/javase
		 * /tutorial/uiswing/lookandfeel/plaf.html
		 */

		// </editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {

					try {
						new LoginUI().setVisible(true);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

			}
		});
	}

	// Variables declaration - do not modify
	private javax.swing.JButton kirjauduButton;
	private javax.swing.JButton lopetaButton;
	private javax.swing.JLabel loginLabel;
	private javax.swing.JLabel kayttajanimiLabel;
	private javax.swing.JLabel salasanaLabel;
	private javax.swing.JPasswordField salasanaTextField;
	private javax.swing.JTextField kayttajanimiTextField;
	// End of variables declaration
}
