package JDBC_test.com.JDBC_test;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

import JDBC_test.com.JDBC_test.models.AbstractProduct;
import JDBC_test.com.JDBC_test.models.Product;

public class ProductsPanel extends JPanel{
	
	private ArrayList<JPanel> products = new ArrayList<JPanel>();

	public ProductsPanel(final Shop shop, ArrayList<AbstractProduct> productsData) {
		
		GridLayout gridLayout = new GridLayout(productsData.size(), 1);
		gridLayout.setHgap(10);
		
		setLayout(gridLayout);
		
		for(int i=0; i < productsData.size(); i++) {
			
			JPanel productPanel = new ProductPanel(shop, this, productsData.get(i), new Long(i));
			
			add(productPanel);
		}
	}
	
	public void deleteProduct(final Shop shop, JPanel product) {
		
		products.remove(product);

		removeAll();
		
		for(int i=0; i < products.size(); i++) {
			
			add(products.get(i));
		}

		revalidate();
		repaint();
		
		shop.update();
	}
	
	public void addProduct(final Shop shop, AbstractProduct product) {
		
		JPanel productPanel = new ProductPanel(shop, this, product, new Long(products.size()));
		
		products.add(productPanel);
		
		add(productPanel);
		
		revalidate();
		repaint();
	}
}
