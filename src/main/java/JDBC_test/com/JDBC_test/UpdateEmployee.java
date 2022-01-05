package JDBC_test.com.JDBC_test;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
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
import javax.swing.SwingConstants;

public class UpdateEmployee extends JPanel implements ActionListener{
	
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
	private JButton acceptButton = new JButton("Zapisz");
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
				
				shop.setPanel(new HomeAfterLogin(shop));
			}
		});
	}
	
	private void initPlaces() {
		
		countries = new String[]{"Polska"};
		states = new String[]{"Dolnośląskie", "Mazowieckie"};
		cities = new String[]{"Wrocław", "Warszawa"};
		streets = new String[]{"Bzowa", "Boczna", "Bradzka",  "Czarnoleska", "Huculska", "Krucza"};
	}

	public UpdateEmployee(final Shop shop) {
		
		setSize(813, 504);
		
		NumberFormat numberFormat = NumberFormat.getIntegerInstance();
		
		initPlaces();
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{147, 111, 103, 31, 120, 148, 137, 0};
		gridBagLayout.rowHeights = new int[]{63, 30, 30, 30, 30, 30, 30, 17, 35, 30, 20, 20, 37, 33, 23, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel title = new JLabel("Aktualizacja danych");
		title.setFont(new Font("Tahoma", Font.PLAIN, 22));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_title = new GridBagConstraints();
		gbc_title.fill = GridBagConstraints.VERTICAL;
		gbc_title.insets = new Insets(0, 0, 5, 5);
		gbc_title.gridwidth = 7;
		gbc_title.gridx = 0;
		gbc_title.gridy = 0;
		add(title, gbc_title);
		
		JLabel firstnameLabel = new JLabel("Imię:");
		GridBagConstraints gbc_firstnameLabel = new GridBagConstraints();
		gbc_firstnameLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_firstnameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_firstnameLabel.gridx = 1;
		gbc_firstnameLabel.gridy = 1;
		add(firstnameLabel, gbc_firstnameLabel);
		
		statesList = new JComboBox(states);
		statesList.addActionListener(this);
		
		firstnameInput = new JPasswordField();
		GridBagConstraints gbc_firstnameInput = new GridBagConstraints();
		gbc_firstnameInput.fill = GridBagConstraints.HORIZONTAL;
		gbc_firstnameInput.insets = new Insets(0, 0, 5, 5);
		gbc_firstnameInput.gridx = 2;
		gbc_firstnameInput.gridy = 1;
		add(firstnameInput, gbc_firstnameInput);
		firstnameInput.setColumns(10);
		
		JLabel countryLabel = new JLabel("Państwo:");
		GridBagConstraints gbc_countryLabel = new GridBagConstraints();
		gbc_countryLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_countryLabel.insets = new Insets(0, 0, 5, 5);
		gbc_countryLabel.gridx = 4;
		gbc_countryLabel.gridy = 1;
		add(countryLabel, gbc_countryLabel);
		
		countriesList = new JComboBox(countries);
		countriesList.addActionListener(this);
		GridBagConstraints gbc_countriesList = new GridBagConstraints();
		gbc_countriesList.fill = GridBagConstraints.HORIZONTAL;
		gbc_countriesList.insets = new Insets(0, 0, 5, 5);
		gbc_countriesList.gridx = 5;
		gbc_countriesList.gridy = 1;
		add(countriesList, gbc_countriesList);
		
		JLabel surnameLabel = new JLabel("Nazwisko:");
		GridBagConstraints gbc_surnameLabel = new GridBagConstraints();
		gbc_surnameLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_surnameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_surnameLabel.gridx = 1;
		gbc_surnameLabel.gridy = 2;
		add(surnameLabel, gbc_surnameLabel);
		
		surnameInput = new JPasswordField();
		GridBagConstraints gbc_surnameInput = new GridBagConstraints();
		gbc_surnameInput.fill = GridBagConstraints.HORIZONTAL;
		gbc_surnameInput.insets = new Insets(0, 0, 5, 5);
		gbc_surnameInput.gridx = 2;
		gbc_surnameInput.gridy = 2;
		add(surnameInput, gbc_surnameInput);
		surnameInput.setColumns(10);
		
		JLabel stateLabel = new JLabel("Województwo/Stan:");
		GridBagConstraints gbc_stateLabel = new GridBagConstraints();
		gbc_stateLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_stateLabel.insets = new Insets(0, 0, 5, 5);
		gbc_stateLabel.gridx = 4;
		gbc_stateLabel.gridy = 2;
		add(stateLabel, gbc_stateLabel);
		GridBagConstraints gbc_statesList = new GridBagConstraints();
		gbc_statesList.fill = GridBagConstraints.HORIZONTAL;
		gbc_statesList.insets = new Insets(0, 0, 5, 5);
		gbc_statesList.gridx = 5;
		gbc_statesList.gridy = 2;
		add(statesList, gbc_statesList);
		
		JLabel emailLabel = new JLabel("E-mail:");
		GridBagConstraints gbc_emailLabel = new GridBagConstraints();
		gbc_emailLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_emailLabel.insets = new Insets(0, 0, 5, 5);
		gbc_emailLabel.gridx = 1;
		gbc_emailLabel.gridy = 3;
		add(emailLabel, gbc_emailLabel);
		
		emailInput = new JPasswordField();
		GridBagConstraints gbc_emailInput = new GridBagConstraints();
		gbc_emailInput.fill = GridBagConstraints.HORIZONTAL;
		gbc_emailInput.insets = new Insets(0, 0, 5, 5);
		gbc_emailInput.gridx = 2;
		gbc_emailInput.gridy = 3;
		add(emailInput, gbc_emailInput);
		emailInput.setColumns(10);
		
		JLabel cityLabel = new JLabel("Miasto:");
		GridBagConstraints gbc_cityLabel = new GridBagConstraints();
		gbc_cityLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_cityLabel.insets = new Insets(0, 0, 5, 5);
		gbc_cityLabel.gridx = 4;
		gbc_cityLabel.gridy = 3;
		add(cityLabel, gbc_cityLabel);
		
		citiesList = new JComboBox(cities);
		citiesList.addActionListener(this);
		GridBagConstraints gbc_citiesList = new GridBagConstraints();
		gbc_citiesList.fill = GridBagConstraints.HORIZONTAL;
		gbc_citiesList.insets = new Insets(0, 0, 5, 5);
		gbc_citiesList.gridx = 5;
		gbc_citiesList.gridy = 3;
		add(citiesList, gbc_citiesList);
		
		JLabel loginLabel = new JLabel("Login:");
		GridBagConstraints gbc_loginLabel = new GridBagConstraints();
		gbc_loginLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_loginLabel.insets = new Insets(0, 0, 5, 5);
		gbc_loginLabel.gridx = 1;
		gbc_loginLabel.gridy = 4;
		add(loginLabel, gbc_loginLabel);
		
		loginInput = new JTextField();
		GridBagConstraints gbc_loginInput = new GridBagConstraints();
		gbc_loginInput.fill = GridBagConstraints.HORIZONTAL;
		gbc_loginInput.insets = new Insets(0, 0, 5, 5);
		gbc_loginInput.gridx = 2;
		gbc_loginInput.gridy = 4;
		add(loginInput, gbc_loginInput);
		loginInput.setColumns(10);
		
		streetsList = new JComboBox(streets);
		streetsList.addActionListener(this);
		
		JLabel streetLabel = new JLabel("Ulica:");
		GridBagConstraints gbc_streetLabel = new GridBagConstraints();
		gbc_streetLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_streetLabel.insets = new Insets(0, 0, 5, 5);
		gbc_streetLabel.gridx = 4;
		gbc_streetLabel.gridy = 4;
		add(streetLabel, gbc_streetLabel);
		GridBagConstraints gbc_streetsList = new GridBagConstraints();
		gbc_streetsList.fill = GridBagConstraints.HORIZONTAL;
		gbc_streetsList.insets = new Insets(0, 0, 5, 5);
		gbc_streetsList.gridx = 5;
		gbc_streetsList.gridy = 4;
		add(streetsList, gbc_streetsList);
		
		JLabel passwordLabel = new JLabel("Hasło:");
		GridBagConstraints gbc_passwordLabel = new GridBagConstraints();
		gbc_passwordLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordLabel.insets = new Insets(0, 0, 5, 5);
		gbc_passwordLabel.gridx = 1;
		gbc_passwordLabel.gridy = 5;
		add(passwordLabel, gbc_passwordLabel);
		
		passwordInput = new JPasswordField();
		GridBagConstraints gbc_passwordInput = new GridBagConstraints();
		gbc_passwordInput.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordInput.insets = new Insets(0, 0, 5, 5);
		gbc_passwordInput.gridx = 2;
		gbc_passwordInput.gridy = 5;
		add(passwordInput, gbc_passwordInput);
		passwordInput.setColumns(10);
		
		JLabel streetNumberLabel = new JLabel("Numer ulicy:");
		GridBagConstraints gbc_streetNumberLabel = new GridBagConstraints();
		gbc_streetNumberLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_streetNumberLabel.insets = new Insets(0, 0, 5, 5);
		gbc_streetNumberLabel.gridx = 4;
		gbc_streetNumberLabel.gridy = 5;
		add(streetNumberLabel, gbc_streetNumberLabel);
		
		streetNumberInput = new JTextField();
		GridBagConstraints gbc_streetNumberInput = new GridBagConstraints();
		gbc_streetNumberInput.fill = GridBagConstraints.HORIZONTAL;
		gbc_streetNumberInput.insets = new Insets(0, 0, 5, 5);
		gbc_streetNumberInput.gridx = 5;
		gbc_streetNumberInput.gridy = 5;
		add(streetNumberInput, gbc_streetNumberInput);
		streetNumberInput.setColumns(10);
		
		JLabel repeatPasswordLabel = new JLabel("Powtórz hasło:");
		GridBagConstraints gbc_repeatPasswordLabel = new GridBagConstraints();
		gbc_repeatPasswordLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_repeatPasswordLabel.insets = new Insets(0, 0, 5, 5);
		gbc_repeatPasswordLabel.gridx = 1;
		gbc_repeatPasswordLabel.gridy = 6;
		add(repeatPasswordLabel, gbc_repeatPasswordLabel);
		
		repeatPasswordInput = new JPasswordField();
		GridBagConstraints gbc_repeatPasswordInput = new GridBagConstraints();
		gbc_repeatPasswordInput.fill = GridBagConstraints.HORIZONTAL;
		gbc_repeatPasswordInput.insets = new Insets(0, 0, 5, 5);
		gbc_repeatPasswordInput.gridx = 2;
		gbc_repeatPasswordInput.gridy = 6;
		add(repeatPasswordInput, gbc_repeatPasswordInput);
		repeatPasswordInput.setColumns(10);
		
		JLabel numberOfFlatLabel = new JLabel("Numer mieszkania/lokalu:");
		GridBagConstraints gbc_numberOfFlatLabel = new GridBagConstraints();
		gbc_numberOfFlatLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_numberOfFlatLabel.insets = new Insets(0, 0, 5, 5);
		gbc_numberOfFlatLabel.gridx = 4;
		gbc_numberOfFlatLabel.gridy = 6;
		add(numberOfFlatLabel, gbc_numberOfFlatLabel);
		
		numberOfFlatInput = new JTextField();
		GridBagConstraints gbc_numberOfFlatInput = new GridBagConstraints();
		gbc_numberOfFlatInput.fill = GridBagConstraints.HORIZONTAL;
		gbc_numberOfFlatInput.insets = new Insets(0, 0, 5, 5);
		gbc_numberOfFlatInput.gridx = 5;
		gbc_numberOfFlatInput.gridy = 6;
		add(numberOfFlatInput, gbc_numberOfFlatInput);
		numberOfFlatInput.setColumns(10);
		
		JLabel postalCodeLabel = new JLabel("Kod pocztowy:");
		GridBagConstraints gbc_postalCodeLabel = new GridBagConstraints();
		gbc_postalCodeLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_postalCodeLabel.insets = new Insets(0, 0, 5, 5);
		gbc_postalCodeLabel.gridx = 4;
		gbc_postalCodeLabel.gridy = 7;
		add(postalCodeLabel, gbc_postalCodeLabel);
		
		postalCode = new JFormattedTextField(numberFormat);
		GridBagConstraints gbc_postalCode = new GridBagConstraints();
		gbc_postalCode.fill = GridBagConstraints.HORIZONTAL;
		gbc_postalCode.insets = new Insets(0, 0, 5, 5);
		gbc_postalCode.gridx = 5;
		gbc_postalCode.gridy = 7;
		add(postalCode, gbc_postalCode);
		postalCode.setColumns(10);
		
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
