package JDBC_test.com.JDBC_test;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import JDBC_test.com.JDBC_test.DAOS.AbstractProductDAO;
import JDBC_test.com.JDBC_test.models.AbstractProduct;
import JDBC_test.com.JDBC_test.models.Product;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class ProductPanel extends JPanel{

	private JButton editButton = new JButton("Edytuj");
	private JButton removeButton = new JButton("Usuń");
	private JLabel imageLabel;
	private JLabel nameLabel;
	private JLabel priceLabel;
	
	public AbstractProduct product;
	
	private Long index;
	
	private void events(final Shop shop, final ProductsPanel productsPanel) {
		
		imageLabel.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				shop.setPanel(new ProductDetails(shop, product));
			}
		});
		
		final ProductPanel panel = this;
		
		removeButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				try {
					AbstractProductDAO.deleteById(product.getId());
				} 
				catch (SQLException e1) {

					e1.printStackTrace();
				}
				productsPanel.deleteProduct(shop, panel);
			}
		});
		
		editButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				shop.setPanel(new EditProduct(shop, product));
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

	public ProductPanel(final Shop shop, final ProductsPanel productsPanel, AbstractProduct product, Long index) {
		
		this.index = index;
		
		this.product = product;
		
		loadImage();
		
		events(shop, productsPanel);
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{56, 94, 94, 94, 94, 94, 60, 0};
		gridBagLayout.rowHeights = new int[]{33, 73, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		GridBagConstraints gbc_imageLabel = new GridBagConstraints();
		gbc_imageLabel.fill = GridBagConstraints.VERTICAL;
		gbc_imageLabel.insets = new Insets(0, 0, 5, 5);
		gbc_imageLabel.gridx = 1;
		gbc_imageLabel.gridy = 1;
		add(imageLabel, gbc_imageLabel);
		
		nameLabel = new JLabel(product.getName());
		nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_nameLabel = new GridBagConstraints();
		gbc_nameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_nameLabel.gridx = 2;
		gbc_nameLabel.gridy = 1;
		add(nameLabel, gbc_nameLabel);
		
		priceLabel = new JLabel(Double.toString(product.getPrice()));
		priceLabel.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_priceLabel = new GridBagConstraints();
		gbc_priceLabel.insets = new Insets(0, 0, 5, 5);
		gbc_priceLabel.gridx = 3;
		gbc_priceLabel.gridy = 1;
		gbc_priceLabel.fill = GridBagConstraints.HORIZONTAL;
		add(priceLabel, gbc_priceLabel);
		
		GridBagConstraints gbc_editButton = new GridBagConstraints();
		gbc_editButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_editButton.insets = new Insets(0, 0, 5, 5);
		gbc_editButton.gridx = 4;
		gbc_editButton.gridy = 1;
		add(editButton, gbc_editButton);
		
		GridBagConstraints gbc_removeButton = new GridBagConstraints();
		gbc_removeButton.insets = new Insets(0, 0, 5, 5);
		gbc_removeButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_removeButton.gridx = 5;
		gbc_removeButton.gridy = 1;
		add(removeButton, gbc_removeButton);
	}
}
