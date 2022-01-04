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

public class Register extends JPanel implements ActionListener{
	
	private JTextField firstnameInput;
	private JTextField surnameInput;
	private JTextField emailInput;
	private JTextField loginInput;
	private JPasswordField passwordInput;
	private JPasswordField repeatPasswordInput;
	private JFormattedTextField employeeNumberInput;
	private JFormattedTextField phoneNumberInput;
	private JComboBox countriesList;
	private JComboBox statesList;
	private JComboBox citiesList;
	private JComboBox streetsList;
	private JFormattedTextField postalCode;
	private JTextField streetNumberInput;
	private JTextField numberOfFlatInput;
	private JButton acceptButton = new JButton("Zarejestruj");
	private JButton closeButton = new JButton("Zamknij");
	
	private String[] countries;
	private String[] states;
	private String[] cities;
	private String[] streets;
	
	
	private void events(final Shop shop) {
		
		acceptButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		
		closeButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				shop.setMainPanel();
			}
		});
	}
	
	private void initPlaces() {
		
		countries = new String[]{"Poland"};
		states = new String[]{"Dolnośląskie", "Mazowieckie"};
		cities = new String[]{"Wrocław", "Warszawa"};
		streets = new String[]{"Bzowa", "Boczna", "Bradzka",  "Czarnoleska", "Huculska", "Krucza"};
	}

	public Register(final Shop shop) {
		
		initPlaces();
		
		//setSize(1000, 650);

		SpringLayout springLayout = new SpringLayout();
		springLayout.putConstraint(SpringLayout.WEST, acceptButton, 303, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, acceptButton, -18, SpringLayout.NORTH, closeButton);
		springLayout.putConstraint(SpringLayout.EAST, acceptButton, -317, SpringLayout.EAST, this);
		springLayout.putConstraint(SpringLayout.WEST, closeButton, 303, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, closeButton, -317, SpringLayout.EAST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, closeButton, -10, SpringLayout.SOUTH, this);
		setLayout(springLayout);
		
		JLabel title = new JLabel("Rejestracja");
		springLayout.putConstraint(SpringLayout.NORTH, title, 27, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, title, 147, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, title, -483, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.EAST, title, -193, SpringLayout.EAST, this);
		title.setFont(new Font("Tahoma", Font.PLAIN, 22));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		add(title);
		
		NumberFormat numberFormat = NumberFormat.getIntegerInstance();
		
		employeeNumberInput = new JFormattedTextField(numberFormat);
		add(employeeNumberInput);
		employeeNumberInput.setColumns(10);
		
		phoneNumberInput = new JFormattedTextField(numberFormat);
		springLayout.putConstraint(SpringLayout.NORTH, phoneNumberInput, 401, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.SOUTH, employeeNumberInput, -19, SpringLayout.NORTH, phoneNumberInput);
		springLayout.putConstraint(SpringLayout.WEST, phoneNumberInput, 0, SpringLayout.WEST, employeeNumberInput);
		add(phoneNumberInput);
		phoneNumberInput.setColumns(10);
		
		postalCode = new JFormattedTextField(numberFormat);
		add(postalCode);
		postalCode.setColumns(10);
		
		streetNumberInput = new JTextField();
		springLayout.putConstraint(SpringLayout.EAST, postalCode, 0, SpringLayout.EAST, streetNumberInput);
		add(streetNumberInput);
		streetNumberInput.setColumns(10);
		
		numberOfFlatInput = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, postalCode, 14, SpringLayout.SOUTH, numberOfFlatInput);
		add(numberOfFlatInput);
		numberOfFlatInput.setColumns(10);
		
		countriesList = new JComboBox(countries);
		springLayout.putConstraint(SpringLayout.EAST, countriesList, 0, SpringLayout.EAST, postalCode);
		countriesList.addActionListener(this);
		add(countriesList);
		
		statesList = new JComboBox(states);
		springLayout.putConstraint(SpringLayout.NORTH, statesList, 14, SpringLayout.SOUTH, countriesList);
		springLayout.putConstraint(SpringLayout.EAST, statesList, 0, SpringLayout.EAST, postalCode);
		statesList.addActionListener(this);
		add(statesList);
		
		citiesList = new JComboBox(cities);
		springLayout.putConstraint(SpringLayout.NORTH, citiesList, 17, SpringLayout.SOUTH, statesList);
		springLayout.putConstraint(SpringLayout.WEST, citiesList, 0, SpringLayout.WEST, postalCode);
		springLayout.putConstraint(SpringLayout.EAST, citiesList, -185, SpringLayout.EAST, this);
		citiesList.addActionListener(this);
		add(citiesList);
		
		streetsList = new JComboBox(streets);
		streetsList.addActionListener(this);
		add(streetsList);
		
		firstnameInput = new JPasswordField();
		springLayout.putConstraint(SpringLayout.NORTH, countriesList, 0, SpringLayout.NORTH, firstnameInput);
		springLayout.putConstraint(SpringLayout.SOUTH, countriesList, 23, SpringLayout.NORTH, firstnameInput);
		springLayout.putConstraint(SpringLayout.NORTH, firstnameInput, 93, SpringLayout.NORTH, this);
		add(firstnameInput);
		firstnameInput.setColumns(10);
		
		surnameInput = new JPasswordField();
		springLayout.putConstraint(SpringLayout.EAST, surnameInput, 0, SpringLayout.EAST, firstnameInput);
		springLayout.putConstraint(SpringLayout.NORTH, surnameInput, 17, SpringLayout.SOUTH, firstnameInput);
		add(surnameInput);
		surnameInput.setColumns(10);
		
		emailInput = new JPasswordField();
		springLayout.putConstraint(SpringLayout.NORTH, emailInput, 17, SpringLayout.SOUTH, surnameInput);
		springLayout.putConstraint(SpringLayout.EAST, emailInput, 0, SpringLayout.EAST, firstnameInput);
		springLayout.putConstraint(SpringLayout.WEST, emailInput, 0, SpringLayout.WEST, firstnameInput);
		add(emailInput);
		emailInput.setColumns(10);
		
		loginInput = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, loginInput, 18, SpringLayout.SOUTH, emailInput);
		springLayout.putConstraint(SpringLayout.NORTH, streetsList, 0, SpringLayout.NORTH, loginInput);
		add(loginInput);
		loginInput.setColumns(10);
		
		passwordInput = new JPasswordField();
		springLayout.putConstraint(SpringLayout.NORTH, streetNumberInput, 0, SpringLayout.NORTH, passwordInput);
		springLayout.putConstraint(SpringLayout.NORTH, passwordInput, 19, SpringLayout.SOUTH, loginInput);
		springLayout.putConstraint(SpringLayout.WEST, passwordInput, 0, SpringLayout.WEST, firstnameInput);
		add(passwordInput);
		passwordInput.setColumns(10);
		
		repeatPasswordInput = new JPasswordField();
		springLayout.putConstraint(SpringLayout.NORTH, numberOfFlatInput, 0, SpringLayout.NORTH, repeatPasswordInput);
		springLayout.putConstraint(SpringLayout.NORTH, repeatPasswordInput, 21, SpringLayout.SOUTH, passwordInput);
		springLayout.putConstraint(SpringLayout.WEST, repeatPasswordInput, 0, SpringLayout.WEST, firstnameInput);
		add(repeatPasswordInput);
		repeatPasswordInput.setColumns(10);
		
		JLabel firstnameLabel = new JLabel("Imię:");
		springLayout.putConstraint(SpringLayout.NORTH, firstnameLabel, 30, SpringLayout.SOUTH, title);
		springLayout.putConstraint(SpringLayout.WEST, firstnameLabel, 186, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.WEST, firstnameInput, 65, SpringLayout.EAST, firstnameLabel);
		add(firstnameLabel);
		
		JLabel surnameLabel = new JLabel("Nazwisko:");
		springLayout.putConstraint(SpringLayout.NORTH, surnameLabel, 22, SpringLayout.SOUTH, firstnameLabel);
		springLayout.putConstraint(SpringLayout.WEST, surnameInput, 41, SpringLayout.EAST, surnameLabel);
		springLayout.putConstraint(SpringLayout.WEST, surnameLabel, 0, SpringLayout.WEST, firstnameLabel);
		add(surnameLabel);
		
		JLabel emailLabel = new JLabel("E-mail:");
		springLayout.putConstraint(SpringLayout.NORTH, emailLabel, 23, SpringLayout.SOUTH, surnameLabel);
		springLayout.putConstraint(SpringLayout.WEST, emailLabel, 0, SpringLayout.WEST, firstnameLabel);
		add(emailLabel);
		
		JLabel employeeNumberLabel = new JLabel("Numer pracownika:");
		springLayout.putConstraint(SpringLayout.WEST, employeeNumberInput, 77, SpringLayout.EAST, employeeNumberLabel);
		springLayout.putConstraint(SpringLayout.NORTH, employeeNumberLabel, 3, SpringLayout.NORTH, employeeNumberInput);
		springLayout.putConstraint(SpringLayout.WEST, employeeNumberLabel, 0, SpringLayout.WEST, firstnameInput);
		add(employeeNumberLabel);
		
		JLabel phoneNumberLabel = new JLabel("Numer telefonu służbowego:");
		springLayout.putConstraint(SpringLayout.NORTH, phoneNumberLabel, 3, SpringLayout.NORTH, phoneNumberInput);
		springLayout.putConstraint(SpringLayout.WEST, phoneNumberLabel, 0, SpringLayout.WEST, firstnameInput);
		add(phoneNumberLabel);
		
		JLabel countryLabel = new JLabel("Państwo:");
		springLayout.putConstraint(SpringLayout.NORTH, countryLabel, 30, SpringLayout.SOUTH, title);
		springLayout.putConstraint(SpringLayout.WEST, countryLabel, 83, SpringLayout.EAST, firstnameInput);
		springLayout.putConstraint(SpringLayout.WEST, countriesList, 80, SpringLayout.EAST, countryLabel);
		add(countryLabel);
		
		JLabel stateLabel = new JLabel("Województwo/Stan:");
		springLayout.putConstraint(SpringLayout.NORTH, stateLabel, 22, SpringLayout.SOUTH, countryLabel);
		springLayout.putConstraint(SpringLayout.WEST, stateLabel, 0, SpringLayout.WEST, employeeNumberInput);
		add(stateLabel);
		
		JLabel cityLabel = new JLabel("Miasto:");
		springLayout.putConstraint(SpringLayout.NORTH, cityLabel, 26, SpringLayout.SOUTH, stateLabel);
		springLayout.putConstraint(SpringLayout.WEST, cityLabel, 0, SpringLayout.WEST, employeeNumberInput);
		add(cityLabel);
		
		JLabel streetLabel = new JLabel("Ulica:");
		springLayout.putConstraint(SpringLayout.WEST, streetLabel, 444, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.WEST, streetsList, 101, SpringLayout.EAST, streetLabel);
		springLayout.putConstraint(SpringLayout.EAST, loginInput, -83, SpringLayout.WEST, streetLabel);
		springLayout.putConstraint(SpringLayout.NORTH, streetLabel, 3, SpringLayout.NORTH, streetsList);
		add(streetLabel);
		
		JLabel postalCodeLabel = new JLabel("Kod pocztowy:");
		springLayout.putConstraint(SpringLayout.NORTH, postalCodeLabel, 3, SpringLayout.NORTH, postalCode);
		springLayout.putConstraint(SpringLayout.WEST, postalCodeLabel, 444, SpringLayout.WEST, this);
		add(postalCodeLabel);
		
		JLabel streetNumberLabel = new JLabel("Numer ulicy:");
		springLayout.putConstraint(SpringLayout.WEST, streetNumberLabel, 83, SpringLayout.EAST, passwordInput);
		springLayout.putConstraint(SpringLayout.WEST, streetNumberInput, 66, SpringLayout.EAST, streetNumberLabel);
		springLayout.putConstraint(SpringLayout.NORTH, streetNumberLabel, 3, SpringLayout.NORTH, streetNumberInput);
		add(streetNumberLabel);
		
		JLabel numberOfFlatLabel = new JLabel("Numer mieszkania/lokalu:");
		springLayout.putConstraint(SpringLayout.WEST, numberOfFlatLabel, 83, SpringLayout.EAST, repeatPasswordInput);
		springLayout.putConstraint(SpringLayout.WEST, numberOfFlatInput, 5, SpringLayout.EAST, numberOfFlatLabel);
		springLayout.putConstraint(SpringLayout.NORTH, numberOfFlatLabel, 3, SpringLayout.NORTH, numberOfFlatInput);
		add(numberOfFlatLabel);
		
		JLabel loginLabel = new JLabel("Login:");
		springLayout.putConstraint(SpringLayout.WEST, loginInput, 60, SpringLayout.EAST, loginLabel);
		springLayout.putConstraint(SpringLayout.NORTH, loginLabel, 3, SpringLayout.NORTH, loginInput);
		springLayout.putConstraint(SpringLayout.WEST, loginLabel, 0, SpringLayout.WEST, firstnameLabel);
		add(loginLabel);
		
		JLabel passwordLabel = new JLabel("Hasło:");
		springLayout.putConstraint(SpringLayout.NORTH, passwordLabel, 3, SpringLayout.NORTH, passwordInput);
		springLayout.putConstraint(SpringLayout.WEST, passwordLabel, 0, SpringLayout.WEST, firstnameLabel);
		add(passwordLabel);
		
		JLabel repeatPasswordLabel = new JLabel("Powtórz hasło:");
		springLayout.putConstraint(SpringLayout.NORTH, repeatPasswordLabel, 3, SpringLayout.NORTH, repeatPasswordInput);
		springLayout.putConstraint(SpringLayout.WEST, repeatPasswordLabel, 0, SpringLayout.WEST, firstnameLabel);
		add(repeatPasswordLabel);
		
		events(shop);
		
		add(acceptButton);
		add(closeButton);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
