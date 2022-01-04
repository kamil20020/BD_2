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

public class Login extends JPanel {
	private JTextField loginInput;
	private JTextField passwordInput;
	private JButton acceptLoginButton = new JButton("Zaloguj");
	private JButton closeButton = new JButton("Zamknij");

	private void events(final Shop shop) {
		
		acceptLoginButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		
		closeButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				shop.setMainPanel();
			}
		});
	}
	
	public Login(final Shop shop) {
		
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		
		JLabel loginTitle = new JLabel("Logowanie");
		springLayout.putConstraint(SpringLayout.NORTH, loginTitle, 25, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, loginTitle, 116, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, loginTitle, -106, SpringLayout.EAST, this);
		loginTitle.setFont(new Font("Tahoma", Font.PLAIN, 22));
		loginTitle.setHorizontalAlignment(SwingConstants.CENTER);
		add(loginTitle);
		
		loginInput = new JTextField();
		springLayout.putConstraint(SpringLayout.SOUTH, loginTitle, -23, SpringLayout.NORTH, loginInput);
		springLayout.putConstraint(SpringLayout.NORTH, loginInput, 100, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.EAST, loginInput, -56, SpringLayout.EAST, this);
		add(loginInput);
		loginInput.setColumns(10);
		
		passwordInput = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, passwordInput, 18, SpringLayout.SOUTH, loginInput);
		springLayout.putConstraint(SpringLayout.EAST, passwordInput, 0, SpringLayout.EAST, loginInput);
		add(passwordInput);
		passwordInput.setColumns(10);
		
		JLabel loginLabel = new JLabel("Login:");
		springLayout.putConstraint(SpringLayout.NORTH, loginLabel, 23, SpringLayout.SOUTH, loginTitle);
		loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
		springLayout.putConstraint(SpringLayout.EAST, loginLabel, -209, SpringLayout.EAST, this);
		springLayout.putConstraint(SpringLayout.WEST, loginInput, 6, SpringLayout.EAST, loginLabel);
		springLayout.putConstraint(SpringLayout.WEST, loginLabel, 36, SpringLayout.WEST, this);
		add(loginLabel);
		
		JLabel passwordLabel = new JLabel("Hasło:");
		springLayout.putConstraint(SpringLayout.SOUTH, loginLabel, -18, SpringLayout.NORTH, passwordLabel);
		passwordLabel.setHorizontalAlignment(SwingConstants.CENTER);
		springLayout.putConstraint(SpringLayout.NORTH, passwordLabel, 138, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, passwordLabel, 36, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, passwordLabel, -142, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.EAST, passwordLabel, -209, SpringLayout.EAST, this);
		springLayout.putConstraint(SpringLayout.WEST, passwordInput, 6, SpringLayout.EAST, passwordLabel);
		add(passwordLabel);
		
		springLayout.putConstraint(SpringLayout.NORTH, acceptLoginButton, 25, SpringLayout.SOUTH, passwordInput);
		springLayout.putConstraint(SpringLayout.WEST, acceptLoginButton, 0, SpringLayout.WEST, loginTitle);
		springLayout.putConstraint(SpringLayout.EAST, acceptLoginButton, -106, SpringLayout.EAST, this);
		
		springLayout.putConstraint(SpringLayout.NORTH, closeButton, 22, SpringLayout.SOUTH, acceptLoginButton);
		springLayout.putConstraint(SpringLayout.WEST, closeButton, 116, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, closeButton, 0, SpringLayout.EAST, loginTitle);
		
		events(shop);
		
		add(acceptLoginButton);
		add(closeButton);

	}
}
