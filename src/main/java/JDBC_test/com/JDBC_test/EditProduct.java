package JDBC_test.com.JDBC_test;

import java.awt.Font;
import java.awt.Graphics;
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
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import JDBC_test.com.JDBC_test.models.Product;

public class EditProduct extends JPanel{

	private JButton saveButton = new JButton("Zapisz");
	private JButton closeButton = new JButton("Zamknij");
	private JButton imageInput = new JButton("Wybierz obraz");
	private JLabel imageLabel;
	private JTextField nameInput;
	private JTextField priceInput;
	
	public Product product;
	
	private void events(final Shop shop) {
		
		imageInput.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				choseImage();
			}
		});
		
		closeButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				shop.setPanel(new ShopResources(shop));
			}
		});
	}
	
	private BufferedImage initImage() {
		
		BufferedImage myPicture;
		
		try {
			
			myPicture = ImageIO.read(new File("D:\\Program Files\\eclipse1\\eclipse\\workspace\\BD_2_app_try\\src\\main\\resources\\mouse.jpg"));
			
			return myPicture;
		}
		catch(IOException e) {
			
			System.out.println("Nie udalo sie wczytac obrazka produktu");
		}
		
		return null;
	}
	
	private void choseImage() {
		
		JFileChooser imageChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		imageChooser.setDialogTitle("Wybierz obraz");
		imageChooser.setAcceptAllFileFilterUsed(false);
		
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Obrazy png lub jpg", "png", "jpg");
		imageChooser.addChoosableFileFilter(filter);
		
		int returnValue = imageChooser.showOpenDialog(null);
		
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			
			try {
				
				BufferedImage selectedFile = ImageIO.read(imageChooser.getSelectedFile());
				
				BufferedImage resizedImage = new BufferedImage(60, 60, BufferedImage.TYPE_INT_RGB);

				Graphics g = resizedImage.createGraphics();
				g.drawImage(selectedFile, 0, 0, 60, 60, null);
				g.dispose();
				
				imageLabel.setIcon(new ImageIcon(resizedImage));
			}
			catch(IOException e) {
				
				System.out.println("Nie udało sie wczytac obrazka");
			}
		}
	}

	public EditProduct(final Shop shop, Product product) {
		
		setSize(389, 420);
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{70, 81, 89, 78, 70, 0};
		gridBagLayout.rowHeights = new int[]{63, 27, 32, 54, 33, 41, 15, 41, 48, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		events(shop);
		
		JLabel title = new JLabel("Zmiana danych produktu");
		title.setFont(new Font("Tahoma", Font.PLAIN, 22));
		
		initImage();
		
		GridBagConstraints gbc_title = new GridBagConstraints();
		gbc_title.fill = GridBagConstraints.VERTICAL;
		gbc_title.gridwidth = 5;
		gbc_title.insets = new Insets(0, 0, 5, 0);
		gbc_title.gridx = 0;
		gbc_title.gridy = 0;
		add(title, gbc_title);
		
		JLabel nameLabel = new JLabel("Nazwa:");
		nameLabel.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_nameLabel = new GridBagConstraints();
		gbc_nameLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_nameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_nameLabel.gridx = 1;
		gbc_nameLabel.gridy = 2;
		add(nameLabel, gbc_nameLabel);
		
		nameInput = new JTextField();
		GridBagConstraints gbc_nameInput = new GridBagConstraints();
		gbc_nameInput.fill = GridBagConstraints.HORIZONTAL;
		gbc_nameInput.insets = new Insets(0, 0, 5, 5);
		gbc_nameInput.gridx = 2;
		gbc_nameInput.gridy = 2;
		add(nameInput, gbc_nameInput);
		
		JLabel priceLabel = new JLabel("Cena:");
		priceLabel.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_priceLabel = new GridBagConstraints();
		gbc_priceLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_priceLabel.insets = new Insets(0, 0, 5, 5);
		gbc_priceLabel.gridx = 1;
		gbc_priceLabel.gridy = 3;
		add(priceLabel, gbc_priceLabel);
		
		priceInput = new JTextField();
		GridBagConstraints gbc_priceInput = new GridBagConstraints();
		gbc_priceInput.fill = GridBagConstraints.HORIZONTAL;
		gbc_priceInput.insets = new Insets(0, 0, 5, 5);
		gbc_priceInput.gridx = 2;
		gbc_priceInput.gridy = 3;
		add(priceInput, gbc_priceInput);
		
		imageLabel = new JLabel(new ImageIcon(initImage()));
		GridBagConstraints gbc_imageLabel = new GridBagConstraints();
		gbc_imageLabel.insets = new Insets(0, 0, 5, 5);
		gbc_imageLabel.gridx = 2;
		gbc_imageLabel.gridy = 4;
		add(imageLabel, gbc_imageLabel);
		
		GridBagConstraints gbc_imageInput = new GridBagConstraints();
		gbc_imageInput.fill = GridBagConstraints.HORIZONTAL;
		gbc_imageInput.insets = new Insets(0, 0, 5, 5);
		gbc_imageInput.gridx = 2;
		gbc_imageInput.gridy = 5;
		add(imageInput, gbc_imageInput);
		
		GridBagConstraints gbc_saveButton = new GridBagConstraints();
		gbc_saveButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_saveButton.insets = new Insets(0, 0, 5, 5);
		gbc_saveButton.gridx = 2;
		gbc_saveButton.gridy = 7;
		add(saveButton, gbc_saveButton);
		
		GridBagConstraints gbc_closeButton = new GridBagConstraints();
		gbc_closeButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_closeButton.insets = new Insets(0, 0, 5, 5);
		gbc_closeButton.gridx = 2;
		gbc_closeButton.gridy = 8;
		add(closeButton, gbc_closeButton);
		
	}

}
