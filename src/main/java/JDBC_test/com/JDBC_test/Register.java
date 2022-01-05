package JDBC_test.com.JDBC_test;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.text.NumberFormatter;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class Register extends JPanel implements ActionListener{
	
	private JTextField firstnameInput;
	private JTextField surnameInput;
	private JTextField emailInput;
	private JTextField loginInput;
	private JPasswordField passwordInput;
	private JPasswordField repeatPasswordInput;
	private JFormattedTextField employeeNumberInput;
	private JFormattedTextField phoneNumberInput;
	private JButton acceptButton = new JButton("Zarejestruj");
	private JButton closeButton = new JButton("Zamknij");
	
	private void events(final Shop shop) {
		
		acceptButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		
		closeButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				shop.setPanel(new Home(shop));
			}
		});
	}
	
	public Register(final Shop shop) {
		
		setSize(813, 520);
		
		NumberFormat numberFormat = NumberFormat.getIntegerInstance();
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{147, 111, 103, 31, 120, 148, 137, 0};
		gridBagLayout.rowHeights = new int[]{63, 30, 0, 30, 30, 30, 30, 30, 30, 0, 20, 20, 37, 33, 23, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel title = new JLabel("Rejestracja");
		title.setFont(new Font("Tahoma", Font.PLAIN, 22));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_title = new GridBagConstraints();
		gbc_title.fill = GridBagConstraints.VERTICAL;
		gbc_title.insets = new Insets(0, 0, 5, 0);
		gbc_title.gridwidth = 7;
		gbc_title.gridx = 0;
		gbc_title.gridy = 0;
		add(title, gbc_title);
		
		JLabel firstnameLabel = new JLabel("Imię:");
		GridBagConstraints gbc_firstnameLabel = new GridBagConstraints();
		gbc_firstnameLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_firstnameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_firstnameLabel.gridx = 2;
		gbc_firstnameLabel.gridy = 2;
		add(firstnameLabel, gbc_firstnameLabel);
		
		firstnameInput = new JPasswordField();
		GridBagConstraints gbc_firstnameInput = new GridBagConstraints();
		gbc_firstnameInput.fill = GridBagConstraints.HORIZONTAL;
		gbc_firstnameInput.insets = new Insets(0, 0, 5, 5);
		gbc_firstnameInput.gridx = 4;
		gbc_firstnameInput.gridy = 2;
		add(firstnameInput, gbc_firstnameInput);
		firstnameInput.setColumns(10);
		
		JLabel surnameLabel = new JLabel("Nazwisko:");
		GridBagConstraints gbc_surnameLabel = new GridBagConstraints();
		gbc_surnameLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_surnameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_surnameLabel.gridx = 2;
		gbc_surnameLabel.gridy = 3;
		add(surnameLabel, gbc_surnameLabel);
		
		surnameInput = new JPasswordField();
		GridBagConstraints gbc_surnameInput = new GridBagConstraints();
		gbc_surnameInput.fill = GridBagConstraints.HORIZONTAL;
		gbc_surnameInput.insets = new Insets(0, 0, 5, 5);
		gbc_surnameInput.gridx = 4;
		gbc_surnameInput.gridy = 3;
		add(surnameInput, gbc_surnameInput);
		surnameInput.setColumns(10);
		
		JLabel emailLabel = new JLabel("E-mail:");
		GridBagConstraints gbc_emailLabel = new GridBagConstraints();
		gbc_emailLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_emailLabel.insets = new Insets(0, 0, 5, 5);
		gbc_emailLabel.gridx = 2;
		gbc_emailLabel.gridy = 4;
		add(emailLabel, gbc_emailLabel);
		
		emailInput = new JPasswordField();
		GridBagConstraints gbc_emailInput = new GridBagConstraints();
		gbc_emailInput.fill = GridBagConstraints.HORIZONTAL;
		gbc_emailInput.insets = new Insets(0, 0, 5, 5);
		gbc_emailInput.gridx = 4;
		gbc_emailInput.gridy = 4;
		add(emailInput, gbc_emailInput);
		emailInput.setColumns(10);
		
		JLabel loginLabel = new JLabel("Login:");
		GridBagConstraints gbc_loginLabel = new GridBagConstraints();
		gbc_loginLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_loginLabel.insets = new Insets(0, 0, 5, 5);
		gbc_loginLabel.gridx = 2;
		gbc_loginLabel.gridy = 5;
		add(loginLabel, gbc_loginLabel);
		
		loginInput = new JTextField();
		GridBagConstraints gbc_loginInput = new GridBagConstraints();
		gbc_loginInput.fill = GridBagConstraints.HORIZONTAL;
		gbc_loginInput.insets = new Insets(0, 0, 5, 5);
		gbc_loginInput.gridx = 4;
		gbc_loginInput.gridy = 5;
		add(loginInput, gbc_loginInput);
		loginInput.setColumns(10);
		
		JLabel passwordLabel = new JLabel("Hasło:");
		GridBagConstraints gbc_passwordLabel = new GridBagConstraints();
		gbc_passwordLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordLabel.insets = new Insets(0, 0, 5, 5);
		gbc_passwordLabel.gridx = 2;
		gbc_passwordLabel.gridy = 6;
		add(passwordLabel, gbc_passwordLabel);
		
		passwordInput = new JPasswordField();
		GridBagConstraints gbc_passwordInput = new GridBagConstraints();
		gbc_passwordInput.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordInput.insets = new Insets(0, 0, 5, 5);
		gbc_passwordInput.gridx = 4;
		gbc_passwordInput.gridy = 6;
		add(passwordInput, gbc_passwordInput);
		passwordInput.setColumns(10);
		
		JLabel repeatPasswordLabel = new JLabel("Powtórz hasło:");
		GridBagConstraints gbc_repeatPasswordLabel = new GridBagConstraints();
		gbc_repeatPasswordLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_repeatPasswordLabel.insets = new Insets(0, 0, 5, 5);
		gbc_repeatPasswordLabel.gridx = 2;
		gbc_repeatPasswordLabel.gridy = 7;
		add(repeatPasswordLabel, gbc_repeatPasswordLabel);
		
		repeatPasswordInput = new JPasswordField();
		GridBagConstraints gbc_repeatPasswordInput = new GridBagConstraints();
		gbc_repeatPasswordInput.fill = GridBagConstraints.HORIZONTAL;
		gbc_repeatPasswordInput.insets = new Insets(0, 0, 5, 5);
		gbc_repeatPasswordInput.gridx = 4;
		gbc_repeatPasswordInput.gridy = 7;
		add(repeatPasswordInput, gbc_repeatPasswordInput);
		repeatPasswordInput.setColumns(10);
		
		JLabel employeeNumberLabel = new JLabel("Numer pracownika:");
		GridBagConstraints gbc_employeeNumberLabel = new GridBagConstraints();
		gbc_employeeNumberLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_employeeNumberLabel.insets = new Insets(0, 0, 5, 5);
		gbc_employeeNumberLabel.gridx = 2;
		gbc_employeeNumberLabel.gridy = 9;
		add(employeeNumberLabel, gbc_employeeNumberLabel);
		
		employeeNumberInput = new JFormattedTextField(numberFormat);
		GridBagConstraints gbc_employeeNumberInput = new GridBagConstraints();
		gbc_employeeNumberInput.fill = GridBagConstraints.HORIZONTAL;
		gbc_employeeNumberInput.insets = new Insets(0, 0, 5, 5);
		gbc_employeeNumberInput.gridx = 4;
		gbc_employeeNumberInput.gridy = 9;
		add(employeeNumberInput, gbc_employeeNumberInput);
		employeeNumberInput.setColumns(10);
		
		JLabel phoneNumberLabel = new JLabel("Numer telefonu służbowego:");
		GridBagConstraints gbc_phoneNumberLabel = new GridBagConstraints();
		gbc_phoneNumberLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_phoneNumberLabel.insets = new Insets(0, 0, 5, 5);
		gbc_phoneNumberLabel.gridx = 2;
		gbc_phoneNumberLabel.gridy = 10;
		add(phoneNumberLabel, gbc_phoneNumberLabel);
		
		phoneNumberInput = new JFormattedTextField(numberFormat);
		GridBagConstraints gbc_phoneNumberInput = new GridBagConstraints();
		gbc_phoneNumberInput.fill = GridBagConstraints.HORIZONTAL;
		gbc_phoneNumberInput.insets = new Insets(0, 0, 5, 5);
		gbc_phoneNumberInput.gridx = 4;
		gbc_phoneNumberInput.gridy = 10;
		add(phoneNumberInput, gbc_phoneNumberInput);
		phoneNumberInput.setColumns(10);
		
		GridBagConstraints gbc_acceptButton = new GridBagConstraints();
		gbc_acceptButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_acceptButton.insets = new Insets(0, 0, 5, 5);
		gbc_acceptButton.gridwidth = 3;
		gbc_acceptButton.gridx = 2;
		gbc_acceptButton.gridy = 12;
		add(acceptButton, gbc_acceptButton);
		GridBagConstraints gbc_closeButton = new GridBagConstraints();
		gbc_closeButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_closeButton.insets = new Insets(0, 0, 5, 5);
		gbc_closeButton.gridwidth = 3;
		gbc_closeButton.gridx = 2;
		gbc_closeButton.gridy = 13;
		add(closeButton, gbc_closeButton);
		
		events(shop);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
