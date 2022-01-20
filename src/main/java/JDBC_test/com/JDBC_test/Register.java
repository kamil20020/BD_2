package JDBC_test.com.JDBC_test;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;

import org.apache.commons.validator.EmailValidator;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import JDBC_test.com.JDBC_test.DAOS.EmployeeDAO;
import JDBC_test.com.JDBC_test.models.Employee;
import JDBC_test.com.JDBC_test.models.Store;
import JDBC_test.com.JDBC_test.models.ValidatorType;
import JDBC_test.com.JDBC_test.services.DatabaseConnection;
import JDBC_test.com.JDBC_test.services.EmployeeService;
import oracle.security.crypto.core.MaskException;
import oracle.ucp.AbandonedConnectionTimeoutCallback;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class Register extends JPanel implements ActionListener{
	
	private ValidationTextField firstnameInput;
	private ValidationTextField surnameInput;
	private ValidationTextField emailInput;
	private ValidationTextField loginInput;
	private ValidationTextField passwordInput;
	private ValidationTextField repeatPasswordInput;
	private ValidationTextField employeeNumberInput;
	private ValidationTextField phoneNumberInput;
	private JDateChooser hiringDate; 
	private JButton acceptButton = new JButton("Zarejestruj");
	private JButton closeButton = new JButton("Zamknij");
	
	private void events(final Shop shop) {
		
		acceptButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				boolean requiredFields = (firstnameInput.validateInput() & surnameInput.validateInput() & emailInput.validateInput()
						& loginInput.validateInput() & passwordInput.validateInput() & repeatPasswordInput.validateInput()
						& employeeNumberInput.validateInput() & phoneNumberInput.validateInput());
				
				boolean repeatedPasswordIsProper = (new String(passwordInput.getText()).equals(new String(repeatPasswordInput.getText())));
				
				if(!repeatedPasswordIsProper) {
					
					repeatPasswordInput.setErrorMessage("Hasła nie są identyczne");
				}
				
				if(requiredFields && repeatedPasswordIsProper) {
					
					try {
						Optional<Employee> employee = Optional.ofNullable(EmployeeService.register(new Employee(
								firstnameInput.getText(), surnameInput.getText(), emailInput.getText(),
								loginInput.getText(), passwordInput.getText().toCharArray(), 
								new Timestamp(Calendar.getInstance().getTimeInMillis()), 
								Long.valueOf(employeeNumberInput.getText()), phoneNumberInput.getText())));
						
						if(employee.isPresent()) {
							
							Store.setEmployee(employee.get());
						}
						
					} 
					catch (NumberFormatException e1) {
						e1.printStackTrace();
					} 
					catch (IllegalStateException e1) {
						e1.printStackTrace();
					} 
					catch (SQLException e1) {
						e1.printStackTrace();
						JOptionPane.showMessageDialog(shop, "Wystąpił błąd podczas tworzenia konta", "Błąd", JOptionPane.ERROR_MESSAGE);
						return;
					} 
					
					JOptionPane.showMessageDialog(shop, "Rejestracja przebiegła pomyślnie");
					
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
	
	public Register(final Shop shop){
		
		setSize(622, 770);
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{147, 103, 148, 0};
		gridBagLayout.rowHeights = new int[]{63, 52, 40, 40, 0, 19, -12, -6, 8, 30, -7, 0, 20, 37, 33, 44, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel title = new JLabel("Rejestracja");
		title.setFont(new Font("Tahoma", Font.PLAIN, 22));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_title = new GridBagConstraints();
		gbc_title.gridheight = 2;
		gbc_title.fill = GridBagConstraints.VERTICAL;
		gbc_title.insets = new Insets(0, 0, 5, 5);
		gbc_title.gridx = 1;
		gbc_title.gridy = 0;
		add(title, gbc_title);
		
		firstnameInput = new ValidationTextField(new JTextField(), "Imię*");
		GridBagConstraints gbc_firstnameInput = new GridBagConstraints();
		gbc_firstnameInput.insets = new Insets(0, 0, 5, 5);
		gbc_firstnameInput.gridx = 1;
		gbc_firstnameInput.gridy = 2;
		add(firstnameInput, gbc_firstnameInput);
		
		surnameInput = new ValidationTextField(new JTextField(), "Nazwisko*");
		GridBagConstraints gbc_surnameInput = new GridBagConstraints();
		gbc_surnameInput.insets = new Insets(0, 0, 5, 5);
		gbc_surnameInput.gridx = 1;
		gbc_surnameInput.gridy = 3;
		add(surnameInput, gbc_surnameInput);
		
		emailInput = new ValidationTextField(new JTextField(), "E-mail*");
		emailInput.setValidator(ValidatorType.E_MAIL, "Niewłaściwa wartość");
		GridBagConstraints gbc_emailInput = new GridBagConstraints();
		gbc_emailInput.insets = new Insets(0, 0, 5, 5);
		gbc_emailInput.gridx = 1;
		gbc_emailInput.gridy = 5;
		add(emailInput, gbc_emailInput);
		
		loginInput = new ValidationTextField(new JTextField(), "Login*");
		GridBagConstraints gbc_loginInput = new GridBagConstraints();
		gbc_loginInput.anchor = GridBagConstraints.NORTH;
		gbc_loginInput.insets = new Insets(0, 0, 5, 5);
		gbc_loginInput.gridx = 1;
		gbc_loginInput.gridy = 6;
		add(loginInput, gbc_loginInput);
		
		passwordInput = new ValidationTextField(new JPasswordField(), "Hasło*");
		GridBagConstraints gbc_passwordInput = new GridBagConstraints();
		gbc_passwordInput.insets = new Insets(0, 0, 5, 5);
		gbc_passwordInput.gridx = 1;
		gbc_passwordInput.gridy = 7;
		add(passwordInput, gbc_passwordInput);
		
		repeatPasswordInput = new ValidationTextField(new JPasswordField(), "Powtórz hasło*");
		GridBagConstraints gbc_repeatPasswordInput = new GridBagConstraints();
		gbc_repeatPasswordInput.insets = new Insets(0, 0, 5, 5);
		gbc_repeatPasswordInput.gridx = 1;
		gbc_repeatPasswordInput.gridy = 8;
		add(repeatPasswordInput, gbc_repeatPasswordInput);
		
		phoneNumberInput = new ValidationTextField(new JTextField(), "Numer telefonu służbowego*");
		phoneNumberInput.setValidator(ValidatorType.NUMBER, "Należy wprowadzić cyfry");
		
		employeeNumberInput = new ValidationTextField(new JTextField(), "Numer pracownika*");
		employeeNumberInput.setValidator(ValidatorType.NUMBER, "Należy wprowadzić cyfry");
		
		hiringDate = new JDateChooser();
		hiringDate.setPreferredSize(new Dimension(150, 20));
		GridBagConstraints gbc_hiringDate = new GridBagConstraints();
		gbc_hiringDate.insets = new Insets(0, 0, 5, 5);
		gbc_hiringDate.gridx = 1;
		gbc_hiringDate.gridy = 10;
		//add(hiringDate, gbc_hiringDate);
		
		GridBagConstraints gbc_employeeNumberInput = new GridBagConstraints();
		gbc_employeeNumberInput.insets = new Insets(0, 0, 5, 5);
		gbc_employeeNumberInput.gridx = 1;
		gbc_employeeNumberInput.gridy = 10;
		add(employeeNumberInput, gbc_employeeNumberInput);
		GridBagConstraints gbc_phoneNumberInput = new GridBagConstraints();
		gbc_phoneNumberInput.insets = new Insets(0, 0, 5, 5);
		gbc_phoneNumberInput.gridx = 1;
		gbc_phoneNumberInput.gridy = 11;
		add(phoneNumberInput, gbc_phoneNumberInput);
		
		GridBagConstraints gbc_acceptButton = new GridBagConstraints();
		gbc_acceptButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_acceptButton.insets = new Insets(0, 0, 5, 5);
		gbc_acceptButton.gridx = 1;
		gbc_acceptButton.gridy = 13;
		add(acceptButton, gbc_acceptButton);
		
		GridBagConstraints gbc_closeButton = new GridBagConstraints();
		gbc_closeButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_closeButton.insets = new Insets(0, 0, 5, 5);
		gbc_closeButton.gridx = 1;
		gbc_closeButton.gridy = 14;
		add(closeButton, gbc_closeButton);
		
		events(shop);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
