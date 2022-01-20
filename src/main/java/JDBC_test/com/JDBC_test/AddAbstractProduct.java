package JDBC_test.com.JDBC_test;

import java.awt.Color;
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
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Optional;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import JDBC_test.com.JDBC_test.models.AbstractProduct;
import JDBC_test.com.JDBC_test.models.Employee;
import JDBC_test.com.JDBC_test.models.Product;
import JDBC_test.com.JDBC_test.models.Store;
import JDBC_test.com.JDBC_test.models.ValidatorType;
import JDBC_test.com.JDBC_test.services.AbstractProductService;
import JDBC_test.com.JDBC_test.services.EmployeeService;
import JDBC_test.com.JDBC_test.services.ImageService;
import JDBC_test.com.JDBC_test.services.ProducerService;
import JDBC_test.com.JDBC_test.ShopResources;
import JDBC_test.com.JDBC_test.DAOS.AbstractProductDAO;

public class AddAbstractProduct extends JPanel{

	private JComboBox productCategories;
	private JComboBox producers;
	private JLabel imageLabel;
	private JButton imageInput = new JButton("Wybierz obraz");
	private ValidationTextField nameInput;
	private ValidationTextField priceInput;
	private ValidationTextField descriptionInput;
	private ValidationTextField weightInput;
	private ValidationTextField heightInput;
	private ValidationTextField widthInput;
	private ValidationTextField taxValueInput;
	
	private JButton saveButton = new JButton("Zapisz");
	private JButton closeButton = new JButton("Zamknij");
	
	private String[] productCategoriesData;
	private String[] producersData;
	
	private void events(final Shop shop, final ShopResources shopResources) {
		
		imageInput.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				choseImage();
			}
		});
		
		saveButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				boolean requiredFields = (nameInput.validateInput() & priceInput.validateInput() & descriptionInput.validateInput()
						& weightInput.validateInput() & heightInput.validateInput() & widthInput.validateInput()
						& taxValueInput.validateInput());
				
				if(requiredFields) {
					
					try {
						Optional<AbstractProduct> product = Optional.ofNullable(AbstractProductService.create(new AbstractProduct(
								new Long(productCategories.getSelectedIndex()+1), new Long(producers.getSelectedIndex()+1), ImageService.getBytes(imageLabel),
								Double.valueOf(priceInput.getText()), nameInput.getText(), descriptionInput.getText(), 
								Double.valueOf(weightInput.getText()), Double.valueOf(heightInput.getText()), Double.valueOf(widthInput.getText()),
								Float.valueOf(taxValueInput.getText()))));
						
						if(product.isPresent()) {
							
							shopResources.addAbstractProduct(shop, product.get());
						}
						
					} 
					catch (NumberFormatException e1) {
						e1.printStackTrace();
					} 
					catch (IllegalStateException e1) {
						e1.printStackTrace();
					} 
					catch (SQLException e1) {
						e1.printStackTrace();
						JOptionPane.showMessageDialog(shop, "Wystąpił błąd tworzenia produktu", "Błąd", JOptionPane.ERROR_MESSAGE);
						return;
					} 
					
					JOptionPane.showMessageDialog(shop, "Udało się utworzyć produkt");
					
					shop.setPanel(new ShopResources(shop));
				}
			}
		});
		
		closeButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				shop.setPanel(new ShopResources(shop));
			}
		});
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
	
	private void initProducers() {
		
		ArrayList<String> returnedProducers;
		
		try {
			returnedProducers = ProducerService.getAll();
		} 
		catch (SQLException e2) {

			e2.printStackTrace();
			JOptionPane.showMessageDialog(this, "Nie udało się wczytać producentów", "Błąd", JOptionPane.ERROR_MESSAGE);
			
			return;
		}
		
		producersData = new String[returnedProducers.size()];
		
		producersData = returnedProducers.toArray(producersData);
	}

	public AddAbstractProduct(final Shop shop, final ShopResources shopResources, String[] productCategoriesData) {
		
		this.productCategoriesData = productCategoriesData;
		initProducers();
		
		setSize(706, 863);
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{102, 81, 325, 85, 114, 0};
		gridBagLayout.rowHeights = new int[]{90, 44, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 83, 0, 37, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		events(shop, shopResources);
		
		JLabel title = new JLabel("Dodawanie produktu");
		
		title.setBackground(Color.WHITE);
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Tahoma", Font.PLAIN, 22));
		GridBagConstraints gbc_title = new GridBagConstraints();
		gbc_title.gridwidth = 5;
		gbc_title.fill = GridBagConstraints.HORIZONTAL;
		gbc_title.insets = new Insets(0, 0, 5, 0);
		gbc_title.gridx = 0;
		gbc_title.gridy = 0;
		add(title, gbc_title);
		
		nameInput = new ValidationTextField(new JTextField(), "Nazwa*");
		GridBagConstraints gbc_nameInput = new GridBagConstraints();
		gbc_nameInput.anchor = GridBagConstraints.NORTHWEST;
		gbc_nameInput.insets = new Insets(0, 0, 5, 5);
		gbc_nameInput.gridx = 2;
		gbc_nameInput.gridy = 2;
		add(nameInput, gbc_nameInput);
		
		descriptionInput = new ValidationTextField(new JTextField(), "Opis*");
		GridBagConstraints gbc_descriptionInput = new GridBagConstraints();
		gbc_descriptionInput.anchor = GridBagConstraints.NORTHWEST;
		gbc_descriptionInput.insets = new Insets(0, 0, 5, 5);
		gbc_descriptionInput.gridx = 2;
		gbc_descriptionInput.gridy = 3;
		add(descriptionInput, gbc_descriptionInput);
		
		priceInput = new ValidationTextField(new JTextField(), "Cena*");
		priceInput.setValidator(ValidatorType.NUMBER);
		GridBagConstraints gbc_priceInput = new GridBagConstraints();
		gbc_priceInput.anchor = GridBagConstraints.NORTHWEST;
		gbc_priceInput.insets = new Insets(0, 0, 5, 5);
		gbc_priceInput.gridx = 2;
		gbc_priceInput.gridy = 4;
		add(priceInput, gbc_priceInput);
		
		weightInput = new ValidationTextField(new JTextField(), "Waga*");
		GridBagConstraints gbc_weightInput = new GridBagConstraints();
		gbc_weightInput.anchor = GridBagConstraints.NORTHWEST;
		gbc_weightInput.insets = new Insets(0, 0, 5, 5);
		gbc_weightInput.gridx = 2;
		gbc_weightInput.gridy = 5;
		add(weightInput, gbc_weightInput);
		
		heightInput = new ValidationTextField(new JTextField(), "Wysokość*");
		GridBagConstraints gbc_heightInput = new GridBagConstraints();
		gbc_heightInput.anchor = GridBagConstraints.NORTHWEST;
		gbc_heightInput.insets = new Insets(0, 0, 5, 5);
		gbc_heightInput.gridx = 2;
		gbc_heightInput.gridy = 6;
		add(heightInput, gbc_heightInput);
		
		widthInput = new ValidationTextField(new JTextField(), "Szerokość*");
		GridBagConstraints gbc_widthInput = new GridBagConstraints();
		gbc_widthInput.anchor = GridBagConstraints.NORTHWEST;
		gbc_widthInput.insets = new Insets(0, 0, 5, 5);
		gbc_widthInput.gridx = 2;
		gbc_widthInput.gridy = 7;
		add(widthInput, gbc_widthInput);
		
		taxValueInput = new ValidationTextField(new JTextField(), "Wartość podatku*");
		GridBagConstraints gbc_taxValueInput = new GridBagConstraints();
		gbc_taxValueInput.anchor = GridBagConstraints.NORTHWEST;
		gbc_taxValueInput.insets = new Insets(0, 0, 5, 5);
		gbc_taxValueInput.gridx = 2;
		gbc_taxValueInput.gridy = 8;
		add(taxValueInput, gbc_taxValueInput);
		
		productCategories = new JComboBox(productCategoriesData);
		GridBagConstraints gbc_productCategories = new GridBagConstraints();
		gbc_productCategories.insets = new Insets(0, 0, 5, 5);
		gbc_productCategories.fill = GridBagConstraints.HORIZONTAL;
		gbc_productCategories.gridx = 2;
		gbc_productCategories.gridy = 10;
		add(productCategories, gbc_productCategories);
		
		producers = new JComboBox(producersData);
		GridBagConstraints gbc_producers= new GridBagConstraints();
		gbc_producers.insets = new Insets(0, 0, 5, 5);
		gbc_producers.fill = GridBagConstraints.HORIZONTAL;
		gbc_producers.gridx = 2;
		gbc_producers.gridy = 11;
		add(producers, gbc_producers);
		
		imageLabel = new JLabel(new ImageIcon());
		GridBagConstraints gbc_imageLabel = new GridBagConstraints();
		gbc_imageLabel.insets = new Insets(0, 0, 5, 5);
		gbc_imageLabel.gridx = 2;
		gbc_imageLabel.gridy = 12;
		add(imageLabel, gbc_imageLabel);
		
		GridBagConstraints gbc_imageInput = new GridBagConstraints();
		gbc_imageInput.fill = GridBagConstraints.HORIZONTAL;
		gbc_imageInput.insets = new Insets(0, 0, 5, 5);
		gbc_imageInput.gridx = 2;
		gbc_imageInput.gridy = 13;
		add(imageInput, gbc_imageInput);
		
		GridBagConstraints gbc_addButton = new GridBagConstraints();
		gbc_addButton.insets = new Insets(0, 0, 6, 5);
		gbc_addButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_addButton.gridx = 2;
		gbc_addButton.gridy = 15;
		add(saveButton, gbc_addButton);
		
		GridBagConstraints gbc_closeButton = new GridBagConstraints();
		gbc_closeButton.insets = new Insets(0, 0, 0, 5);
		gbc_closeButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_closeButton.gridx = 2;
		gbc_closeButton.gridy = 16;
		add(closeButton, gbc_closeButton);
	}
}
