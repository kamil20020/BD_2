package JDBC_test.com.JDBC_test;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

public class PlaceholderJTextField extends JTextField{

	public PlaceholderJTextField(final String placeholder) {
		super(placeholder);
		
		this.addFocusListener(new FocusListener() {
		    
			@Override
		    public void focusGained(FocusEvent e) {
		    	
		        if (getText().equals(placeholder)) {
		        	
		            setText("");
		        }
		    }
		    @Override
		    public void focusLost(FocusEvent e) {
		    	
		        if (getText().isEmpty()) {
		            
		            setText(placeholder);
		        }
		    }
		});
	}
}
