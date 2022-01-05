package JDBC_test.com.JDBC_test;

import java.awt.GridLayout;
import java.awt.List;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

import JDBC_test.com.JDBC_test.models.Product;

public class ProductsPanel extends JPanel{
	
	private ArrayList<JPanel> products = new ArrayList<JPanel>();

	public ProductsPanel(final Shop shop, Product[] productsData) {
		
		GridLayout gridLayout = new GridLayout(productsData.length, 1);
		gridLayout.setHgap(10);
		
		setLayout(gridLayout);
		
		for(Product product : productsData) {
			
			JPanel productPanel = new ProductPanel(shop, product);
			
			this.products.add(productPanel);
			add(productPanel);
		}
	}
}
