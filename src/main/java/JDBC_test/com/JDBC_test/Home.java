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

public class Home extends JPanel {
	
	private JButton registerButton = new JButton("Rejestracja");
	private JButton loginButton = new JButton("Logowanie");
	private JButton closeButton = new JButton("Zamknij");
	
	private void events(final Shop shop) {
		
		loginButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				shop.setLoginPanel();
			}
		});
		
		registerButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				shop.setRegisterPanel();
			}
		});
		
		closeButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				shop.close();
			}
		});
	}

	public Home(final Shop shop) {
		
		setSize(400, 350);

		SpringLayout sl_mainPanel = new SpringLayout();
		sl_mainPanel.putConstraint(SpringLayout.NORTH, loginButton, 125, SpringLayout.NORTH, this);
		sl_mainPanel.putConstraint(SpringLayout.WEST, loginButton, 0, SpringLayout.WEST, registerButton);
		sl_mainPanel.putConstraint(SpringLayout.SOUTH, loginButton, -20, SpringLayout.NORTH, registerButton);
		sl_mainPanel.putConstraint(SpringLayout.EAST, loginButton, -73, SpringLayout.EAST, this);
		sl_mainPanel.putConstraint(SpringLayout.WEST, closeButton, 75, SpringLayout.WEST, this);
		sl_mainPanel.putConstraint(SpringLayout.EAST, closeButton, -73, SpringLayout.EAST, this);
		sl_mainPanel.putConstraint(SpringLayout.WEST, registerButton, 75, SpringLayout.WEST, this);
		sl_mainPanel.putConstraint(SpringLayout.EAST, registerButton, -73, SpringLayout.EAST, this);
		sl_mainPanel.putConstraint(SpringLayout.NORTH, closeButton, 214, SpringLayout.NORTH, this);
		sl_mainPanel.putConstraint(SpringLayout.SOUTH, registerButton, -23, SpringLayout.NORTH, closeButton);
		setLayout(sl_mainPanel);
		
		JLabel title = new JLabel("Sklep z elektroniką");
		sl_mainPanel.putConstraint(SpringLayout.SOUTH, title, -35, SpringLayout.NORTH, loginButton);
		sl_mainPanel.putConstraint(SpringLayout.WEST, title, 0, SpringLayout.WEST, this);
		sl_mainPanel.putConstraint(SpringLayout.EAST, title, 0, SpringLayout.EAST, this);
		
		title.setBackground(Color.WHITE);
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Tahoma", Font.PLAIN, 22));
		add(title);
		
		events(shop);
		
		add(loginButton);
		add(registerButton);
		add(closeButton);
	}
}
