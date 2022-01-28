package JDBC_test.com.JDBC_test;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import JDBC_test.com.JDBC_test.DAOS.AbstractProductDAO;
import JDBC_test.com.JDBC_test.models.AbstractProduct;
import JDBC_test.com.JDBC_test.models.Product;
import JDBC_test.com.JDBC_test.services.AbstractProductService;
import JDBC_test.com.JDBC_test.services.ProductCategoryService;

public class ShopResources extends JPanel{
	
	private JTextField searchInput;
	private JButton searchButton = new JButton("Wyszukaj");
	private JButton resetCriteriaButton = new JButton("Resetuj filtr");
	private JButton addButton = new JButton("Dodaj");
	private JButton closeButton = new JButton("Zamknij");
	private JScrollPane products;
	private JComboBox<String> productCategories;
	
	private Product[] productsData;
	private String[] productCategoriesData;
	private ArrayList<AbstractProduct> abstractProducts = new ArrayList<>();
	
	private ProductsPanel productsPanel;
	
	private String searchPlaceholder = "Wyszukiwarka produktów";
	
	private void events(final Shop shop) {
		
		final ShopResources shopResources = this;
		
		addButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				shop.setPanel(new AddAbstractProduct(shop, shopResources, productCategoriesData));
			}
		});
		
		searchButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				String productName = searchInput.getText();
				
				if(productName.equals(searchPlaceholder)) {
					
					productName = "";
				}
				
				try {
					Long productCategoryId = Long.valueOf(productCategories.getSelectedIndex()+1);
					
					abstractProducts = AbstractProductService.searchByCriteria(productCategoryId, productName);
					
					productsPanel = new ProductsPanel(shop, abstractProducts);
					products.setViewportView(productsPanel);
				} 
				catch (SQLException | IOException e1) {

					e1.printStackTrace();
					return;
				}
			}
		});
		
		resetCriteriaButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				initProducts();
				
				productsPanel = new ProductsPanel(shop, abstractProducts);
				products.setViewportView(productsPanel);
			}
		});
		
		closeButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				shop.setPanel(new HomeAfterLogin(shop));
			}
		});
	}
	
	private void initProducts() {
		
		try {
			abstractProducts = AbstractProductService.maybyeNewlyCreatedDatabaseGetAll();
			
		} 
		catch (SQLException e) {
			
			e.printStackTrace();
		} 
		catch (IOException e) {

			e.printStackTrace();
		}
	}
	
	public void addAbstractProduct(Shop shop, AbstractProduct product) {
		
		productsPanel.addProduct(shop, product);
	}
	
	private void initProductCategories() {
		
		ArrayList<String> returnedProductCategories;
		
		try {
			returnedProductCategories = ProductCategoryService.getAll();
		} 
		catch (SQLException e2) {

			e2.printStackTrace();
			JOptionPane.showMessageDialog(this, "Nie udało się wczytać kategorii produktów", "Błąd", JOptionPane.ERROR_MESSAGE);
			
			return;
		}
		
		productCategoriesData = new String[returnedProductCategories.size()];
		
		productCategoriesData = returnedProductCategories.toArray(productCategoriesData);
	}

	public ShopResources(final Shop shop) {
		
		initProducts();
		initProductCategories();
		productsPanel = new ProductsPanel(shop, abstractProducts);
		
		setSize(1101, 473);
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{102, 117, 173, 110, 110, 234, 103, 96, 153, 0};
		gridBagLayout.rowHeights = new int[]{63, 27, 35, 273, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		events(shop);
		
		JLabel title = new JLabel("Zasoby sklepu");
		
		title.setBackground(Color.WHITE);
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Tahoma", Font.PLAIN, 22));
		GridBagConstraints gbc_title = new GridBagConstraints();
		gbc_title.gridwidth = 9;
		gbc_title.fill = GridBagConstraints.HORIZONTAL;
		gbc_title.insets = new Insets(0, 0, 5, 0);
		gbc_title.gridx = 0;
		gbc_title.gridy = 0;
		add(title, gbc_title);
		
		productCategories = new JComboBox(productCategoriesData);
		GridBagConstraints gbc_productCategories = new GridBagConstraints();
		gbc_productCategories.insets = new Insets(0, 0, 5, 5);
		gbc_productCategories.fill = GridBagConstraints.HORIZONTAL;
		gbc_productCategories.gridx = 1;
		gbc_productCategories.gridy = 1;
		add(productCategories, gbc_productCategories);
		
		searchInput = new PlaceholderJTextField(searchPlaceholder);
		GridBagConstraints gbc_searchInput = new GridBagConstraints();
		gbc_searchInput.gridx = 2;
		gbc_searchInput.gridy = 1;
		gbc_searchInput.fill = GridBagConstraints.BOTH;
		gbc_searchInput.insets = new Insets(0, 0, 5, 5);
		add(searchInput, gbc_searchInput);
		searchInput.setColumns(10);
		
		GridBagConstraints gbc_searchButton = new GridBagConstraints();
		gbc_searchButton.anchor = GridBagConstraints.NORTH;
		gbc_searchButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_searchButton.insets = new Insets(0, 0, 5, 5);
		gbc_searchButton.gridx = 3;
		gbc_searchButton.gridy = 1;
		add(searchButton, gbc_searchButton);
		
		GridBagConstraints gbc_resetCriteriaButton = new GridBagConstraints();
		gbc_resetCriteriaButton.insets = new Insets(0, 0, 5, 5);
		gbc_resetCriteriaButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_resetCriteriaButton.gridx = 4;
		gbc_resetCriteriaButton.gridy = 1;
		add(resetCriteriaButton, gbc_resetCriteriaButton);
		
		GridBagConstraints gbc_addButton = new GridBagConstraints();
		gbc_addButton.insets = new Insets(0, 0, 6, 5);
		gbc_addButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_addButton.gridx = 6;
		gbc_addButton.gridy = 1;
		add(addButton, gbc_addButton);
		
		GridBagConstraints gbc_closeButton = new GridBagConstraints();
		gbc_closeButton.insets = new Insets(0, 0, 5, 5);
		gbc_closeButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_closeButton.gridx = 7;
		gbc_closeButton.gridy = 1;
		add(closeButton, gbc_closeButton);
		
		products = new JScrollPane(productsPanel);
		GridBagConstraints gbc_products = new GridBagConstraints();
		gbc_products.fill = GridBagConstraints.BOTH;
		gbc_products.gridwidth = 4;
		gbc_products.insets = new Insets(0, 0, 5, 5);
		gbc_products.gridx = 2;
		gbc_products.gridy = 3;
		products.setSize(products.getWidth(), this.getHeight());
		add(products, gbc_products);
	}

}
