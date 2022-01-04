package JDBC_test.com.JDBC_test;

public class Product {

	public String name;
	public double price;
	public String description;
	
	public Product(String name, double price, String description) {
		
		this.name = name;
		this.price = price;
		this.description = description;
	}
	
	@Override
	public String toString() {
		
		return name + " " + price + " " + description;
	}
}
