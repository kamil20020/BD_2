package JDBC_test.com.JDBC_test;

import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class Login extends JPanel {
	private JTextField loginInput;
	private JTextField passwordInput;
	private JButton acceptLoginButton = new JButton("Zaloguj");
	private JButton closeButton = new JButton("Zamknij");

	private void events(final Shop shop) {
		
		acceptLoginButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				shop.setPanel(new HomeAfterLogin(shop));
			}
		});
		
		closeButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				shop.setPanel(new Home(shop));
			}
		});
	}
	
	public Login(final Shop shop) {
		
		setSize(452, 324);
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{107, 95, 147, 107, 0};
		gridBagLayout.rowHeights = new int[]{125, 20, 50, 45, 36, 61, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel loginTitle = new JLabel("Logowanie");
		loginTitle.setFont(new Font("Tahoma", Font.PLAIN, 22));
		loginTitle.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_loginTitle = new GridBagConstraints();
		gbc_loginTitle.fill = GridBagConstraints.VERTICAL;
		gbc_loginTitle.insets = new Insets(0, 0, 5, 5);
		gbc_loginTitle.gridwidth = 2;
		gbc_loginTitle.gridx = 1;
		gbc_loginTitle.gridy = 0;
		add(loginTitle, gbc_loginTitle);
		
		JLabel loginLabel = new JLabel("Login:");
		loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_loginLabel = new GridBagConstraints();
		gbc_loginLabel.anchor = GridBagConstraints.WEST;
		gbc_loginLabel.insets = new Insets(0, 0, 5, 5);
		gbc_loginLabel.gridx = 1;
		gbc_loginLabel.gridy = 1;
		add(loginLabel, gbc_loginLabel);
		
		loginInput = new JTextField();
		GridBagConstraints gbc_loginInput = new GridBagConstraints();
		gbc_loginInput.fill = GridBagConstraints.HORIZONTAL;
		gbc_loginInput.insets = new Insets(0, 0, 5, 5);
		gbc_loginInput.gridx = 2;
		gbc_loginInput.gridy = 1;
		add(loginInput, gbc_loginInput);
		loginInput.setColumns(10);
		
		JLabel passwordLabel = new JLabel("Hasło:");
		passwordLabel.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_passwordLabel = new GridBagConstraints();
		gbc_passwordLabel.anchor = GridBagConstraints.WEST;
		gbc_passwordLabel.insets = new Insets(0, 0, 5, 5);
		gbc_passwordLabel.gridx = 1;
		gbc_passwordLabel.gridy = 2;
		add(passwordLabel, gbc_passwordLabel);
		
		passwordInput = new JTextField();
		GridBagConstraints gbc_passwordInput = new GridBagConstraints();
		gbc_passwordInput.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordInput.insets = new Insets(0, 0, 5, 5);
		gbc_passwordInput.gridx = 2;
		gbc_passwordInput.gridy = 2;
		add(passwordInput, gbc_passwordInput);
		passwordInput.setColumns(10);
		
		GridBagConstraints gbc_acceptLoginButton = new GridBagConstraints();
		gbc_acceptLoginButton.ipadx = 60;
		gbc_acceptLoginButton.gridwidth = 2;
		gbc_acceptLoginButton.insets = new Insets(0, 0, 5, 5);
		gbc_acceptLoginButton.gridx = 1;
		gbc_acceptLoginButton.gridy = 3;
		add(acceptLoginButton, gbc_acceptLoginButton);
		GridBagConstraints gbc_closeButton = new GridBagConstraints();
		gbc_closeButton.insets = new Insets(0, 0, 5, 5);
		gbc_closeButton.ipadx = 60;
		gbc_closeButton.gridwidth = 2;
		gbc_closeButton.gridx = 1;
		gbc_closeButton.gridy = 4;
		add(closeButton, gbc_closeButton);
		
		events(shop);

	}
}
