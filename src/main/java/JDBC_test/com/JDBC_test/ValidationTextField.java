package JDBC_test.com.JDBC_test;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.Format;
import java.text.NumberFormat;

import javax.sound.midi.VoiceStatus;
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.apache.commons.validator.EmailValidator;
import org.apache.commons.validator.Validator;

import JDBC_test.com.JDBC_test.models.ValidatorType;

public class ValidationTextField extends JPanel{

	private JTextField textField = new JTextField();

	private JLabel headerLabel = new JLabel();
	private JLabel errorLabel = new JLabel();
	
	private String requiredMessage = "Pole nie może być puste";
	private String minLengthMessage = "Wartość jest za krótka";
	private String maxLengthMessage = "Wartość jest za długa";
	private String formatMessage;
	
	private boolean required = true;
	private boolean formatError = false;
	
	int minLength = -1, maxLength = -1;
	
	private ValidatorType validatorType;
	
	public ValidationTextField(final JTextField textField, String header) {
		
		headerLabel.setText(header + ":");
		errorLabel.setForeground(Color.RED);
		
		textField.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				
				validateInput();
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				
				
			}
		});
		
		textField.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
			
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				
				if(validatorType == ValidatorType.NUMBER && !validateInputWithValidator()) {
					
					errorLabel.setText(formatMessage);
					formatError = true;
				}
				else {
					
					errorLabel.setText("");
					formatError = false;
				}
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {

				
			}
		});
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{171, 150, 0};
		gridBagLayout.rowHeights = new int[]{22, 23, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		GridBagConstraints gbc_headerLabel = new GridBagConstraints();
		gbc_headerLabel.anchor = GridBagConstraints.WEST;
		gbc_headerLabel.insets = new Insets(0, 0, 5, 5);
		gbc_headerLabel.gridx = 0;
		gbc_headerLabel.gridy = 0;
		add(headerLabel, gbc_headerLabel);
		

		this.textField = textField;
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 0;
		add(this.textField, gbc_textField);
		
		GridBagConstraints gbc_errorLabel = new GridBagConstraints();
		gbc_errorLabel.gridx = 1;
		gbc_errorLabel.gridy = 1;
		add(errorLabel, gbc_errorLabel);
	}
	
	public void setRequired(boolean required) {
		
		this.required = true;
	}
	
	public void setMinLength(int length) {
		
		this.minLength = length;
	}
	
	public void setMaxLength(int length) {
		
		this.maxLength = length;
	}
	
	public boolean validateInput() {
		
		boolean succeed = true;
		
		if(required && textField.getText().isEmpty()) {
			
			errorLabel.setText(requiredMessage);
			succeed = false;
		}
		else if(minLength > -1 && textField.getText().length() < minLength) {
				
			errorLabel.setText(minLengthMessage);
			succeed = false;
		}
		else if(maxLength > -1 && textField.getText().length() > maxLength) {
				
			errorLabel.setText(maxLengthMessage);
			succeed = false;
		}
		else if(validatorType != null && !validateInputWithValidator()) {
			
			errorLabel.setText(formatMessage);
			succeed = false;
		}
		
		return succeed;
	}
	
	public boolean validateInputWithValidator() {
		
		if(validatorType == null) {
			
			return false;
		}
		
		switch(validatorType) {
		
			case E_MAIL:
				return EmailValidator.getInstance().isValid(textField.getText());
				
			case NUMBER:
				
				try {
					Long.valueOf(textField.getText());
					return true;
				}
				catch(NumberFormatException e) {
					
					return false;
				}
		}
		
		return true;
	}
	
	public void setErrorMessage(String message) {
		
		this.errorLabel.setText(message); 
	}
	
	public void setText(String text) {
		
		this.textField.setText(text);
	}
	
	public String getText() {
		
		return textField.getText();
	}
	
	public void setValidator(ValidatorType validatorType, String errorMessage) {
		
		this.formatMessage = errorMessage;
		this.validatorType = validatorType;
	}
	
	public void setValidator(ValidatorType validatorType) {
		
		this.formatMessage = "Wprowadzono niepoprawną wartość";
		this.validatorType = validatorType;
	}
}
