package JDBC_test.com.JDBC_test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.EventSetDescriptor;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class Home extends JPanel {
	
	private JButton registerButton = new JButton("Rejestracja");
	private JButton loginButton = new JButton("Logowanie");
	private JButton closeButton = new JButton("Zamknij");
	
	private void events(final Shop shop) {
		
		loginButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				shop.setPanel(new Login(shop));
			}
		});
		
		registerButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				shop.setPanel(new Register(shop));
			}
		});
		
		closeButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				shop.close();
			}
		});
	}

	public Home(final Shop shop) {
		
		setSize(326, 274);
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{70, 0, 70, 0};
		gridBagLayout.rowHeights = new int[]{63, 27, 35, 40, 40, 23, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		events(shop);
		
		JLabel title = new JLabel("Sklep z elektroniką");
		
		title.setBackground(Color.WHITE);
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Tahoma", Font.PLAIN, 22));
		GridBagConstraints gbc_title = new GridBagConstraints();
		gbc_title.anchor = GridBagConstraints.NORTH;
		gbc_title.fill = GridBagConstraints.HORIZONTAL;
		gbc_title.insets = new Insets(0, 0, 5, 5);
		gbc_title.gridx = 1;
		gbc_title.gridy = 1;
		add(title, gbc_title);
		
		GridBagConstraints gbc_loginButton = new GridBagConstraints();
		gbc_loginButton.anchor = GridBagConstraints.NORTH;
		gbc_loginButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_loginButton.insets = new Insets(0, 0, 5, 5);
		gbc_loginButton.gridx = 1;
		gbc_loginButton.gridy = 3;
		add(loginButton, gbc_loginButton);
		GridBagConstraints gbc_registerButton = new GridBagConstraints();
		gbc_registerButton.anchor = GridBagConstraints.NORTH;
		gbc_registerButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_registerButton.insets = new Insets(0, 0, 5, 5);
		gbc_registerButton.gridx = 1;
		gbc_registerButton.gridy = 4;
		add(registerButton, gbc_registerButton);
		GridBagConstraints gbc_closeButton = new GridBagConstraints();
		gbc_closeButton.insets = new Insets(0, 0, 0, 5);
		gbc_closeButton.anchor = GridBagConstraints.NORTH;
		gbc_closeButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_closeButton.gridx = 1;
		gbc_closeButton.gridy = 5;
		add(closeButton, gbc_closeButton);
	}
}
