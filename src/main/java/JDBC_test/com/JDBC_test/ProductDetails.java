package JDBC_test.com.JDBC_test;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import JDBC_test.com.JDBC_test.models.AbstractProduct;
import JDBC_test.com.JDBC_test.models.Product;

public class ProductDetails extends JPanel{

	private JButton closeButton = new JButton("Zamknij");
	private JLabel imageLabel;
	private JLabel nameLabel;
	private JLabel priceLabel;
	
	public Product product;
	
	private void events(final Shop shop) {
		
		closeButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				shop.setPanel(new ShopResources(shop));
			}
		});
	}
	
	private BufferedImage initImage() {
		
		BufferedImage myPicture;
		
		try {
			
			myPicture = ImageIO.read(new File("D:\\Program Files\\eclipse1\\eclipse\\workspace\\BD_2_app_try\\src\\main\\resources\\images\\mouse.jpg"));
			
			return myPicture;
		}
		catch(IOException e) {
			
			System.out.println("Nie udalo sie wczytac obrazka produktu");
		}
		
		return null;
	}

	public ProductDetails(final Shop shop, AbstractProduct product) {
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{70, 81, 89, 78, 70, 0};
		gridBagLayout.rowHeights = new int[]{63, 27, 33, 40, 40, 23, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		events(shop);
		
		JLabel title = new JLabel("Detale produktu");
		title.setFont(new Font("Tahoma", Font.PLAIN, 22));
		
		initImage();
		
		GridBagConstraints gbc_title = new GridBagConstraints();
		gbc_title.gridwidth = 5;
		gbc_title.insets = new Insets(0, 0, 5, 0);
		gbc_title.gridx = 0;
		gbc_title.gridy = 0;
		add(title, gbc_title);
		
		imageLabel = new JLabel(new ImageIcon(initImage()));
		GridBagConstraints gbc_imageLabel = new GridBagConstraints();
		gbc_imageLabel.insets = new Insets(0, 0, 5, 5);
		gbc_imageLabel.gridx = 1;
		gbc_imageLabel.gridy = 1;
		add(imageLabel, gbc_imageLabel);
		
		nameLabel = new JLabel(product.getName());
		nameLabel.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_nameLabel = new GridBagConstraints();
		gbc_nameLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_nameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_nameLabel.gridx = 2;
		gbc_nameLabel.gridy = 1;
		add(nameLabel, gbc_nameLabel);
		
		priceLabel = new JLabel(Double.toString(product.getPrice()) + " zł");
		priceLabel.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_priceLabel = new GridBagConstraints();
		gbc_priceLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_priceLabel.insets = new Insets(0, 0, 5, 5);
		gbc_priceLabel.gridx = 2;
		gbc_priceLabel.gridy = 2;
		add(priceLabel, gbc_priceLabel);
		GridBagConstraints gbc_closeButton = new GridBagConstraints();
		gbc_closeButton.insets = new Insets(0, 0, 5, 5);
		gbc_closeButton.gridx = 2;
		gbc_closeButton.gridy = 4;
		add(closeButton, gbc_closeButton);
	}

}
