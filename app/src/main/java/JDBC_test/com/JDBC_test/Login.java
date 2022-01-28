package JDBC_test.com.JDBC_test;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import JDBC_test.com.JDBC_test.models.Employee;
import JDBC_test.com.JDBC_test.models.Person;
import JDBC_test.com.JDBC_test.models.Store;
import JDBC_test.com.JDBC_test.services.EmployeeService;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Optional;

import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class Login extends JPanel {
	
	private ValidationTextField loginInput;
	private ValidationTextField passwordInput;
	private JButton acceptLoginButton = new JButton("Zaloguj");
	private JButton closeButton = new JButton("Zamknij");

	private void events(final Shop shop) {
		
		acceptLoginButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				if(loginInput.validateInput() & passwordInput.validateInput()) {
					
					try {
						Optional<Employee> employee = Optional.ofNullable(
								EmployeeService.login(loginInput.getText(), Person.convertPassword(passwordInput.getText())));
						
						if(employee.isPresent()) {
							
							Store.setEmployee(employee.get());
						}
						else {
							
							JOptionPane.showMessageDialog(shop, "Podany login i/lub hasło są nieprawidłowe", "Błędne dane", JOptionPane.INFORMATION_MESSAGE);
							return;
						}
						
					}  
					catch (SQLException e1) {
						e1.printStackTrace();
						JOptionPane.showMessageDialog(shop, "Wystąpił błąd podczas logowania", "Błąd", JOptionPane.ERROR_MESSAGE);
						return;
					} 
					
					JOptionPane.showMessageDialog(shop, "Udało się zalogować");
					
					shop.setPanel(new HomeAfterLogin(shop));
				}
			}
		});
		
		closeButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				shop.setPanel(new Home(shop));
			}
		});
	}
	
	public Login(final Shop shop) {
		
		setSize(524, 441);
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{118, 95, 107, 0};
		gridBagLayout.rowHeights = new int[]{175, 30, 79, 45, 36, 61, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel loginTitle = new JLabel("Logowanie");
		loginTitle.setFont(new Font("Tahoma", Font.PLAIN, 22));
		loginTitle.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_loginTitle = new GridBagConstraints();
		gbc_loginTitle.fill = GridBagConstraints.VERTICAL;
		gbc_loginTitle.insets = new Insets(0, 0, 5, 5);
		gbc_loginTitle.gridx = 1;
		gbc_loginTitle.gridy = 0;
		add(loginTitle, gbc_loginTitle);
		
		loginInput = new ValidationTextField(new JTextField(), "Login");
		GridBagConstraints gbc_loginInput = new GridBagConstraints();
		gbc_loginInput.fill = GridBagConstraints.HORIZONTAL;
		gbc_loginInput.insets = new Insets(0, 0, 5, 5);
		gbc_loginInput.gridx = 1;
		gbc_loginInput.gridy = 1;
		add(loginInput, gbc_loginInput);
		
		passwordInput = new ValidationTextField(new JPasswordField(), "Hasło");
		GridBagConstraints gbc_passwordInput = new GridBagConstraints();
		gbc_passwordInput.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordInput.insets = new Insets(0, 0, 5, 5);
		gbc_passwordInput.gridx = 1;
		gbc_passwordInput.gridy = 2;
		add(passwordInput, gbc_passwordInput);
		
		GridBagConstraints gbc_acceptLoginButton = new GridBagConstraints();
		gbc_acceptLoginButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_acceptLoginButton.ipadx = 60;
		gbc_acceptLoginButton.insets = new Insets(0, 0, 5, 5);
		gbc_acceptLoginButton.gridx = 1;
		gbc_acceptLoginButton.gridy = 3;
		add(acceptLoginButton, gbc_acceptLoginButton);
		GridBagConstraints gbc_closeButton = new GridBagConstraints();
		gbc_closeButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_closeButton.insets = new Insets(0, 0, 5, 5);
		gbc_closeButton.ipadx = 60;
		gbc_closeButton.gridx = 1;
		gbc_closeButton.gridy = 4;
		add(closeButton, gbc_closeButton);
		
		events(shop);

	}
}
