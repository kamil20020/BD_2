package JDBC_test.com.JDBC_test;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import JDBC_test.com.JDBC_test.models.Store;

public class HomeAfterLogin extends JPanel {
	
	private JButton shopResourcesButton = new JButton("Zasoby sklepu");
	private JButton updateEmployeeButton = new JButton("Aktualizacja danych pracownika");
	private JButton logoutButton = new JButton("Wyloguj");
	private JButton closeButton = new JButton("Zamknij");
	
	private void events(final Shop shop) {
		
		updateEmployeeButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				shop.setPanel(new UpdateEmployee(shop));
			}
		});
		
		shopResourcesButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				shop.setPanel(new ShopResources(shop));
			}
		});
		
		logoutButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				Store.clearEmployee();
				shop.setPanel(new Home(shop));
			}
		});
		
		closeButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				shop.close();
			}
		});
	}

	public HomeAfterLogin(final Shop shop) {
		
		setSize(326, 383);
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{70, 0, 70, 0};
		gridBagLayout.rowHeights = new int[]{63, 27, 35, 40, 40, 40, 40, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		events(shop);
		
		JLabel title = new JLabel("Menu");
		
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
		
		GridBagConstraints gbc_registerButton = new GridBagConstraints();
		gbc_registerButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_registerButton.insets = new Insets(0, 0, 5, 5);
		gbc_registerButton.gridx = 1;
		gbc_registerButton.gridy = 3;
		add(shopResourcesButton, gbc_registerButton);
		
		GridBagConstraints gbc_loginButton = new GridBagConstraints();
		gbc_loginButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_loginButton.insets = new Insets(0, 0, 5, 5);
		gbc_loginButton.gridx = 1;
		gbc_loginButton.gridy = 4;
		add(updateEmployeeButton, gbc_loginButton);
		
		GridBagConstraints gbc_logoutButton = new GridBagConstraints();
		gbc_logoutButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_logoutButton.insets = new Insets(0, 0, 5, 5);
		gbc_logoutButton.gridx = 1;
		gbc_logoutButton.gridy = 5;
		add(logoutButton, gbc_logoutButton);
		
		GridBagConstraints gbc_closeButton = new GridBagConstraints();
		gbc_closeButton.insets = new Insets(0, 0, 0, 5);
		gbc_closeButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_closeButton.gridx = 1;
		gbc_closeButton.gridy = 6;
		add(closeButton, gbc_closeButton);
	}

}
