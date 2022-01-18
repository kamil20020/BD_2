package JDBC_test.com.JDBC_test;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import JDBC_test.com.JDBC_test.models.Product;

public class ProductPanel extends JPanel{

	private JButton editButton = new JButton("Edytuj");
	private JButton removeButton = new JButton("Usuń");
	private JLabel imageLabel;
	private JLabel nameLabel;
	private JLabel priceLabel;
	
	public Product product;
	
	private void events(final Shop shop) {
		
		imageLabel.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				shop.setPanel(new ProductDetails(shop, product));
			}
		});
		
		editButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				shop.setPanel(new EditProduct(shop, product));
			}
		});
	}

	public ProductPanel(final Shop shop, Product product) {
		
		GridLayout gridLayout = new GridLayout(1, 4);
		
		setLayout(gridLayout);
		
		this.product = product;
		
		BufferedImage myPicture = null;
		
		try {
			
			myPicture = ImageIO.read(new File("D:\\Program Files\\eclipse1\\eclipse\\workspace\\BD_2_app_try\\src\\main\\resources\\images\\mouse.jpg"));
		}
		catch(IOException e) {
			
			System.out.println("Nie udalo sie wczytac obrazka produktu");
		}
		
		imageLabel = new JLabel(new ImageIcon(myPicture));
		
		events(shop);
		
		nameLabel = new JLabel(product.getName());
		nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		priceLabel = new JLabel(Double.toString(product.getValue()));
		priceLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		editButton.setSize(50, 40);
		
		add(imageLabel);
		add(nameLabel);
		add(priceLabel);
		add(editButton);
		add(removeButton);
	}
}
