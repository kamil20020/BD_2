package JDBC_test.com.JDBC_test.services;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

import JDBC_test.com.JDBC_test.DAOS.AbstractProductDAO;
import JDBC_test.com.JDBC_test.DAOS.EmployeeDAO;
import JDBC_test.com.JDBC_test.DAOS.PhoneDAO;
import JDBC_test.com.JDBC_test.models.AbstractProduct;
import JDBC_test.com.JDBC_test.models.Employee;
import JDBC_test.com.JDBC_test.models.Phone;

public class AbstractProductService {

	public static AbstractProduct create(AbstractProduct product) throws IllegalStateException, SQLException {
		
		AbstractProductDAO.create(product);
		
		AbstractProduct product1 = AbstractProductDAO.getLast();
		
		return product1;
	}
	
	public static ArrayList<AbstractProduct> getAll() throws SQLException{
		
		return AbstractProductDAO.getAll();
	}
	
	private static void loadDefaultImages(ArrayList<AbstractProduct> products) throws IOException {
		
		for(int i=0; i < products.size(); i++) {
			
			String filePath = null;
			
			String upperName = products.get(i).getName().toUpperCase();
			
			if(upperName.contains("MYSZKA")) {
				
				filePath = "images/mouse.jpg";
			}
			else if(upperName.contains("KLAWIATURA")) {
				
				filePath = "images/keyboard.jpg";
			}
			else if(upperName.contains("LAPTOP")) {
				
				filePath = "images/laptop.jpg";
			}
			else if(upperName.contains("SŁUCHAWKI")) {
				
				filePath = "images/headphones.jpg";
			}
			else if(upperName.contains("KARTA GRAFICZNA")) {
				
				filePath = "images/karta graficzna.jpg";
			}
			else if(upperName.contains("1")) {
				
				filePath = "images/tablet1.jpg";
			}
			else if(upperName.contains("KOMPUTER")) {
				
				filePath = "images/computer.jpg";
			}
			
			if(filePath != null) {
				
				products.get(i).setImage(ImageService.convert(filePath, 60, 60));
			}
		}
	}
	
	public static ArrayList<AbstractProduct> maybyeNewlyCreatedDatabaseGetAll() throws SQLException, IOException {
		
		ArrayList<AbstractProduct> products = getAll();
		
		boolean withImage = false;
		
		for(int i=0; i < products.size(); i++) {
			
			if(products.get(i).getImage() != null) {
				
				withImage = true;
			}
		}
		
		if(!withImage) {
			
			loadDefaultImages(products);
			
			for(AbstractProduct p : products) {
				
				AbstractProductDAO.updateImageById(p.getId(), p.getImage());
			}
		}
		
		return products;
	}
	
	public static AbstractProduct update(Long id, AbstractProduct product) throws SQLException {
		
		Optional<AbstractProduct> foundProduct =  Optional.ofNullable(AbstractProductDAO.getById(id));
		
		if(foundProduct.isPresent()) {
			
			AbstractProductDAO.updateById(id, product);
			
			return AbstractProductDAO.getById(id);
		}
		
		return null;
	}
}
