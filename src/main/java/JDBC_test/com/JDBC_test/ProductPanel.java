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
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
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

	public ProductPanel(final Shop shop, final ProductsPanel productsPanel, AbstractProduct product, Long index) {
		
		this.index = index;
		
		GridLayout gridLayout = new GridLayout(1, 4);
		
		setLayout(gridLayout);
		
		this.product = product;
		
		imageLabel = new JLabel(new ImageIcon(product.getImage()));
		
		events(shop, productsPanel);
		
		nameLabel = new JLabel(product.getName());
		nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		priceLabel = new JLabel(Double.toString(product.getPrice()));
		priceLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		editButton.setSize(50, 40);
		
		add(imageLabel);
		add(nameLabel);
		add(priceLabel);
		add(editButton);
		add(removeButton);
	}
}
