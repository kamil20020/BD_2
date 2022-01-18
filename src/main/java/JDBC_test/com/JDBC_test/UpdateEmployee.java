package JDBC_test.com.JDBC_test;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Optional;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import JDBC_test.com.JDBC_test.models.Employee;
import JDBC_test.com.JDBC_test.models.Store;
import JDBC_test.com.JDBC_test.models.ValidatorType;
import JDBC_test.com.JDBC_test.services.EmployeeService;

public class UpdateEmployee extends JPanel implements ActionListener{
	
	private ValidationTextField firstnameInput;
	private ValidationTextField surnameInput;
	private ValidationTextField emailInput;
	private ValidationTextField loginInput;
	private ValidationTextField passwordInput;
	private ValidationTextField repeatPasswordInput;
	private ValidationTextField employeeNumberInput;
	private ValidationTextField phoneNumberInput;
	private JButton acceptButton = new JButton("Zapisz");
	private JButton closeButton = new JButton("Zamknij");
	
	private void events(final Shop shop) {
		
		acceptButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				Employee storeEmployee = Store.getEmployee();
				Employee employee = new Employee(
										storeEmployee.getId(), storeEmployee.getFirstname(), storeEmployee.getSurname(), 
										storeEmployee.getE_mail(), storeEmployee.getUsername(), storeEmployee.getPassword(), 
										storeEmployee.getHiring_date(), storeEmployee.getEmployee_number(), storeEmployee.getPhoneNumber());
				
				boolean changed = false;
				
				if(shouldChangeValue(firstnameInput)) {
					
					employee.setFirstname(firstnameInput.getText());
					changed = true;
				}
				
				if(shouldChangeValue(surnameInput)) {
					
					employee.setSurname(surnameInput.getText());
					changed = true;
				}
				
				if(shouldChangeValue(emailInput)) {
					
					employee.setE_mail(emailInput.getText());
					changed = true;
				}
				
				if(shouldChangeValue(loginInput)) {
					
					employee.setUsername(loginInput.getText());
					changed = true;
				}
				
				if(shouldChangeValue(passwordInput) & shouldChangeValue(repeatPasswordInput)) {
					
					if(new String(passwordInput.getText()).equals(new String(repeatPasswordInput.getText()))) {
						
						employee.setPassword(passwordInput.getText().toCharArray());
						changed = true;
					}
					else {
						
						repeatPasswordInput.setErrorMessage("Hasła nie są identyczne");
					}
				}
				
				if(shouldChangeValue(employeeNumberInput)) {
					
					employee.setEmployee_number(Long.valueOf(employeeNumberInput.getText()));
					changed = true;
				}
				
				if(shouldChangeValue(phoneNumberInput)) {
					
					employee.setPhoneNumber(phoneNumberInput.getText());
					changed = true;
				}
				
				if(changed && !employee.equals(Store.getEmployee())) {
					
					try {
						
						Optional<Employee> employee1 = Optional.ofNullable(EmployeeService.update(employee.getId(), employee));
						
						if(employee1.isPresent()) {
							
							Store.setEmployee(EmployeeService.update(employee.getId(), employee));
							
							JOptionPane.showMessageDialog(shop, "Udało się zmienić dane");
						}
						else {
							
							JOptionPane.showMessageDialog(shop, "Nie udało się zmienić danych", "Błąd", JOptionPane.ERROR_MESSAGE);
						}
					} 
					catch (NumberFormatException e1) {
						e1.printStackTrace();
						return;
					} 
					catch (IllegalStateException e1) {
						e1.printStackTrace();
						JOptionPane.showMessageDialog(shop, "Istnieje juz użytkownik o takim loginie", "Błąd", JOptionPane.ERROR_MESSAGE);
						return;
					} 
					catch (SQLException e1) {
						e1.printStackTrace();
						JOptionPane.showMessageDialog(shop, "Wystąpił błąd podczas zmieniania danych", "Błąd", JOptionPane.ERROR_MESSAGE);
						return;
					} 
				}
				
			}
		});
		
		closeButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				shop.setPanel(new HomeAfterLogin(shop));
			}
		});
	}
	
	private void putEmployee() {
		
		if(Store.employee == null) {
			
			return;
		}
		
		Employee employee = Store.employee;
		
		this.firstnameInput.setText(employee.getFirstname());
		this.surnameInput.setText(employee.getSurname());
		this.emailInput.setText(employee.getE_mail());
		this.loginInput.setText(employee.getUsername());
		this.employeeNumberInput.setText(String.valueOf(employee.getEmployee_number()));
		this.phoneNumberInput.setText(String.valueOf(employee.getPhoneNumber()));
	}
	
	public UpdateEmployee(final Shop shop){
		
		setSize(622, 770);
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{147, 103, 148, 0};
		gridBagLayout.rowHeights = new int[]{63, 52, 40, 40, 0, 19, -12, -6, 8, 30, -1, -7, 20, 37, 33, 44, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel title = new JLabel("Aktualizacja danych pracownika");
		title.setFont(new Font("Tahoma", Font.PLAIN, 22));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_title = new GridBagConstraints();
		gbc_title.gridheight = 2;
		gbc_title.fill = GridBagConstraints.VERTICAL;
		gbc_title.insets = new Insets(0, 0, 5, 5);
		gbc_title.gridx = 1;
		gbc_title.gridy = 0;
		add(title, gbc_title);
		
		firstnameInput = new ValidationTextField(new JTextField(), "Imię");
		GridBagConstraints gbc_firstnameInput = new GridBagConstraints();
		gbc_firstnameInput.insets = new Insets(0, 0, 5, 5);
		gbc_firstnameInput.gridx = 1;
		gbc_firstnameInput.gridy = 2;
		add(firstnameInput, gbc_firstnameInput);
		
		surnameInput = new ValidationTextField(new JTextField(), "Nazwisko");
		GridBagConstraints gbc_surnameInput = new GridBagConstraints();
		gbc_surnameInput.insets = new Insets(0, 0, 5, 5);
		gbc_surnameInput.gridx = 1;
		gbc_surnameInput.gridy = 3;
		add(surnameInput, gbc_surnameInput);
		
		emailInput = new ValidationTextField(new JTextField(), "E-mail");
		emailInput.setValidator(ValidatorType.E_MAIL, "Niewłaściwa wartość");
		GridBagConstraints gbc_emailInput = new GridBagConstraints();
		gbc_emailInput.insets = new Insets(0, 0, 5, 5);
		gbc_emailInput.gridx = 1;
		gbc_emailInput.gridy = 5;
		add(emailInput, gbc_emailInput);
		
		loginInput = new ValidationTextField(new JTextField(), "Login");
		GridBagConstraints gbc_loginInput = new GridBagConstraints();
		gbc_loginInput.anchor = GridBagConstraints.NORTH;
		gbc_loginInput.insets = new Insets(0, 0, 5, 5);
		gbc_loginInput.gridx = 1;
		gbc_loginInput.gridy = 6;
		add(loginInput, gbc_loginInput);
		
		passwordInput = new ValidationTextField(new JPasswordField(), "Hasło");
		//passwordInput.setMinLength(8);
		GridBagConstraints gbc_passwordInput = new GridBagConstraints();
		gbc_passwordInput.insets = new Insets(0, 0, 5, 5);
		gbc_passwordInput.gridx = 1;
		gbc_passwordInput.gridy = 7;
		add(passwordInput, gbc_passwordInput);
		
		repeatPasswordInput = new ValidationTextField(new JPasswordField(), "Powtórz hasło");
		GridBagConstraints gbc_repeatPasswordInput = new GridBagConstraints();
		gbc_repeatPasswordInput.insets = new Insets(0, 0, 5, 5);
		gbc_repeatPasswordInput.gridx = 1;
		gbc_repeatPasswordInput.gridy = 8;
		add(repeatPasswordInput, gbc_repeatPasswordInput);
		   
		employeeNumberInput = new ValidationTextField(new JTextField(), "Numer pracownika");
		employeeNumberInput.setValidator(ValidatorType.NUMBER, "Należy wprowadzić cyfry");
		GridBagConstraints gbc_employeeNumberInput = new GridBagConstraints();
		gbc_employeeNumberInput.insets = new Insets(0, 0, 5, 5);
		gbc_employeeNumberInput.gridx = 1;
		gbc_employeeNumberInput.gridy = 10;
		add(employeeNumberInput, gbc_employeeNumberInput);
		 
		phoneNumberInput = new ValidationTextField(new JTextField(), "Numer telefonu służbowego");
		phoneNumberInput.setValidator(ValidatorType.NUMBER, "Należy wprowadzić cyfry");
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
		
		putEmployee();
		
		events(shop);
	}
	
	private boolean shouldChangeValue(ValidationTextField textField) {
		
		return !textField.getText().isEmpty() && textField.validateInput();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
