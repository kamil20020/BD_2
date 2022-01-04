package JDBC_test.com.JDBC_test;

import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.SpringLayout;
import javax.swing.JList;

import java.awt.event.MouseAdapter; 
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;

public class Catalog extends JPanel {
	
	JList<Product> productsList = new JList<>();
	
	private void fillProductsList() {
		
		Product[] products = new Product[10];
		
		for(int i=0; i < 10; i++) {
			
			products[i] = new Product("product" + i, i+1, "description" + i);
		}
		
		productsList.setListData(products);	
	}
	
	private void events(final Shop shop) {
		
		productsList.addMouseListener(new MouseAdapter() {
			
		    public void mouseClicked(MouseEvent evt) {

	        	System.out.print(1);
		    }
		});
	}


	public Catalog(final Shop shop) {
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		
		springLayout.putConstraint(SpringLayout.NORTH, productsList, 83, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, productsList, 104, SpringLayout.WEST, this);
		
		events(shop);
		
		productsList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		fillProductsList();
		add(productsList);
		
		
		JButton cartButton = new JButton("Koszyk");
		springLayout.putConstraint(SpringLayout.NORTH, cartButton, 10, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, cartButton, -85, SpringLayout.EAST, this);
		springLayout.putConstraint(SpringLayout.EAST, cartButton, -10, SpringLayout.EAST, this);
		add(cartButton);

	}
}
