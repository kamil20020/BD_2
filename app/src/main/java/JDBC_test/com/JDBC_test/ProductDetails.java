package JDBC_test.com.JDBC_test;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import JDBC_test.com.JDBC_test.DAOS.ProducerDAO;
import JDBC_test.com.JDBC_test.DAOS.ProductCategoryDAO;
import JDBC_test.com.JDBC_test.models.AbstractProduct;
import JDBC_test.com.JDBC_test.models.Product;
import JDBC_test.com.JDBC_test.services.ImageService;

public class ProductDetails extends JPanel{

	private JButton closeButton = new JButton("Zamknij");
	private JLabel imageLabel;
	private JLabel nameLabel;
	private JLabel priceLabel;
	
	private JLabel nameHeader = new JLabel("Nazwa: ");
	private JLabel priceHeader = new JLabel("Cena: ");
	
	private JLabel descriptionHeader;
	private JLabel descriptionLabel;
	private JLabel weightHeader;
	private JLabel heightHeader;
	private JLabel widthHeader;
	private JLabel productCategoryHeader;
	private JLabel producerHeader;
	
	public AbstractProduct product;
	private JLabel weightLabel;
	private JLabel heightLabel;
	private JLabel widthLabel;
	private JLabel productCategoryLabel;
	private JLabel producentLabel;
	
	private void events(final Shop shop) {
		
		closeButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				shop.setPanel(new ShopResources(shop));
			}
		});
	}
	
	private void loadImage() {
		
		if(product.getImage() == null) {
			
			imageLabel = new JLabel("Brak obrazka"); 
			return;
		}
		
		ImageIcon icon = new ImageIcon(product.getImage());
		
		imageLabel = new JLabel(icon);
	}
	
	private void loadCategory() {
		
		String productCategory;
		
		try {
			productCategory = ProductCategoryDAO.getById(product.getProduct_category_id());
			
		}
		catch (SQLException e) {
			
			e.printStackTrace();
			return;
		}
		
		productCategoryLabel = new JLabel(productCategory);
	}
	
	private void loadProducer() {
		
		String productProducer;
		
		try {
			productProducer = ProducerDAO.getById(product.getProducer_id());
			
		}
		catch (SQLException e) {
			
			e.printStackTrace();
			return;
		}
		
		producentLabel = new JLabel(productProducer);
	}

	public ProductDetails(final Shop shop, AbstractProduct product) {
		
		setSize(272, 565);
		
		this.product = product;
		
		events(shop);
		loadImage();
		loadCategory();
		loadProducer();
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{112, 78, 93, 93, 0};
		gridBagLayout.rowHeights = new int[]{107, 28, 34, 27, 33, 40, 37, 35, 38, 38, 37, 41, 54, 59, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel title = new JLabel("Detale produktu");
		title.setFont(new Font("Tahoma", Font.PLAIN, 22));
		
		GridBagConstraints gbc_title = new GridBagConstraints();
		gbc_title.fill = GridBagConstraints.VERTICAL;
		gbc_title.gridwidth = 4;
		gbc_title.insets = new Insets(0, 0, 5, 0);
		gbc_title.gridx = 0;
		gbc_title.gridy = 0;
		add(title, gbc_title);
		
		GridBagConstraints gbc_imageLabel = new GridBagConstraints();
		gbc_imageLabel.fill = GridBagConstraints.BOTH;
		gbc_imageLabel.gridwidth = 2;
		gbc_imageLabel.gridheight = 2;
		gbc_imageLabel.insets = new Insets(0, 0, 5, 5);
		gbc_imageLabel.gridx = 1;
		gbc_imageLabel.gridy = 1;
		add(imageLabel, gbc_imageLabel);
		
		nameHeader.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_nameHeder = new GridBagConstraints();
		gbc_nameHeder.fill = GridBagConstraints.VERTICAL;
		gbc_nameHeder.anchor = GridBagConstraints.WEST;
		gbc_nameHeder.insets = new Insets(0, 0, 5, 5);
		gbc_nameHeder.gridx = 1;
		gbc_nameHeder.gridy = 4;
		add(nameHeader, gbc_nameHeder);
		
		nameLabel = new JLabel(product.getName());
		nameLabel.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_nameLabel = new GridBagConstraints();
		gbc_nameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_nameLabel.gridx = 2;
		gbc_nameLabel.gridy = 4;
		add(nameLabel, gbc_nameLabel);
		
		descriptionHeader = new JLabel("Opis:");
		descriptionHeader.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_descriptionHeader = new GridBagConstraints();
		gbc_descriptionHeader.fill = GridBagConstraints.VERTICAL;
		gbc_descriptionHeader.anchor = GridBagConstraints.WEST;
		gbc_descriptionHeader.insets = new Insets(0, 0, 5, 5);
		gbc_descriptionHeader.gridx = 1;
		gbc_descriptionHeader.gridy = 5;
		add(descriptionHeader, gbc_descriptionHeader);
		
		descriptionLabel = new JLabel(product.getDescription());
		descriptionLabel.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_descriptionLabel = new GridBagConstraints();
		gbc_descriptionLabel.insets = new Insets(0, 0, 5, 5);
		gbc_descriptionLabel.gridx = 2;
		gbc_descriptionLabel.gridy = 5;
		add(descriptionLabel, gbc_descriptionLabel);
		
		priceHeader.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_priceHeader = new GridBagConstraints();
		gbc_priceHeader.fill = GridBagConstraints.VERTICAL;
		gbc_priceHeader.anchor = GridBagConstraints.WEST;
		gbc_priceHeader.insets = new Insets(0, 0, 5, 5);
		gbc_priceHeader.gridx = 1;
		gbc_priceHeader.gridy = 6;
		add(priceHeader, gbc_priceHeader);
		
		priceLabel = new JLabel(Double.toString(product.getPrice()) + " zł");
		priceLabel.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_priceLabel = new GridBagConstraints();
		gbc_priceLabel.insets = new Insets(0, 0, 5, 5);
		gbc_priceLabel.gridx = 2;
		gbc_priceLabel.gridy = 6;
		add(priceLabel, gbc_priceLabel);
		
		weightHeader = new JLabel("Waga:");
		weightHeader.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_weightHeader = new GridBagConstraints();
		gbc_weightHeader.fill = GridBagConstraints.VERTICAL;
		gbc_weightHeader.anchor = GridBagConstraints.WEST;
		gbc_weightHeader.insets = new Insets(0, 0, 5, 5);
		gbc_weightHeader.gridx = 1;
		gbc_weightHeader.gridy = 7;
		add(weightHeader, gbc_weightHeader);
		
		weightLabel = new JLabel(String.valueOf(product.getWeight()));
		weightLabel.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_weightLabel = new GridBagConstraints();
		gbc_weightLabel.insets = new Insets(0, 0, 5, 5);
		gbc_weightLabel.gridx = 2;
		gbc_weightLabel.gridy = 7;
		add(weightLabel, gbc_weightLabel);
		
		heightHeader = new JLabel("Wysokość:");
		heightHeader.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_heightHeader = new GridBagConstraints();
		gbc_heightHeader.fill = GridBagConstraints.VERTICAL;
		gbc_heightHeader.anchor = GridBagConstraints.WEST;
		gbc_heightHeader.insets = new Insets(0, 0, 5, 5);
		gbc_heightHeader.gridx = 1;
		gbc_heightHeader.gridy = 8;
		add(heightHeader, gbc_heightHeader);
		
		heightLabel = new JLabel(String.valueOf(product.getHeight()));
		heightLabel.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_heightLabel = new GridBagConstraints();
		gbc_heightLabel.insets = new Insets(0, 0, 5, 5);
		gbc_heightLabel.gridx = 2;
		gbc_heightLabel.gridy = 8;
		add(heightLabel, gbc_heightLabel);
		
		widthHeader = new JLabel("Szerokość:");
		widthHeader.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_widthHeader = new GridBagConstraints();
		gbc_widthHeader.fill = GridBagConstraints.VERTICAL;
		gbc_widthHeader.anchor = GridBagConstraints.WEST;
		gbc_widthHeader.insets = new Insets(0, 0, 5, 5);
		gbc_widthHeader.gridx = 1;
		gbc_widthHeader.gridy = 9;
		add(widthHeader, gbc_widthHeader);
		
		widthLabel = new JLabel(String.valueOf(product.getWidth()));
		widthLabel.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_widthLabel = new GridBagConstraints();
		gbc_widthLabel.insets = new Insets(0, 0, 5, 5);
		gbc_widthLabel.gridx = 2;
		gbc_widthLabel.gridy = 9;
		add(widthLabel, gbc_widthLabel);
		
		productCategoryHeader = new JLabel("Kategoria:");
		productCategoryHeader.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_productCategoryHeader = new GridBagConstraints();
		gbc_productCategoryHeader.fill = GridBagConstraints.VERTICAL;
		gbc_productCategoryHeader.anchor = GridBagConstraints.WEST;
		gbc_productCategoryHeader.insets = new Insets(0, 0, 5, 5);
		gbc_productCategoryHeader.gridx = 1;
		gbc_productCategoryHeader.gridy = 10;
		add(productCategoryHeader, gbc_productCategoryHeader);
		
		productCategoryLabel.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_productCategoryLabel = new GridBagConstraints();
		gbc_productCategoryLabel.insets = new Insets(0, 0, 5, 5);
		gbc_productCategoryLabel.gridx = 2;
		gbc_productCategoryLabel.gridy = 10;
		add(productCategoryLabel, gbc_productCategoryLabel);
		
		producerHeader = new JLabel("Producent:");
		producerHeader.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_producerHeader = new GridBagConstraints();
		gbc_producerHeader.fill = GridBagConstraints.VERTICAL;
		gbc_producerHeader.anchor = GridBagConstraints.WEST;
		gbc_producerHeader.insets = new Insets(0, 0, 5, 5);
		gbc_producerHeader.gridx = 1;
		gbc_producerHeader.gridy = 11;
		add(producerHeader, gbc_producerHeader);
		
		producentLabel.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_producentLabel = new GridBagConstraints();
		gbc_producentLabel.insets = new Insets(0, 0, 5, 5);
		gbc_producentLabel.gridx = 2;
		gbc_producentLabel.gridy = 11;
		add(producentLabel, gbc_producentLabel);
		GridBagConstraints gbc_closeButton = new GridBagConstraints();
		gbc_closeButton.gridwidth = 4;
		gbc_closeButton.insets = new Insets(0, 0, 5, 5);
		gbc_closeButton.gridx = 0;
		gbc_closeButton.gridy = 12;
		add(closeButton, gbc_closeButton);
	}

}
