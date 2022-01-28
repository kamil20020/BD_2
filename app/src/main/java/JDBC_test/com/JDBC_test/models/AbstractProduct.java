package JDBC_test.com.JDBC_test.models;

import java.util.Arrays;

public class AbstractProduct {

	private Long id;
	private Long product_category_id;
	private Long producer_id;
	private byte[] image;
	private Double price;
	private String name;
	private String description;
	private Double weight;
	private Double height;
	private Double width;
	private Float tax_value;
	
	
	public AbstractProduct(Long id, Long product_category_id, Long producer_id, byte[] image, Double price, String name,
			String description, Double weight, Double height, Double width, Float tax_value) {

		this.id = id;
		this.product_category_id = product_category_id;
		this.producer_id = producer_id;
		this.image = image;
		this.price = price;
		this.name = name;
		this.description = description;
		this.weight = weight;
		this.height = height;
		this.width = width;
		this.tax_value = tax_value;
	}
	
	public AbstractProduct(Long product_category_id, Long producer_id, byte[] image, Double price, String name,
			String description, Double weight, Double height, Double width, Float tax_value) {

		this.id = id;
		this.product_category_id = product_category_id;
		this.producer_id = producer_id;
		this.image = image;
		this.price = price;
		this.name = name;
		this.description = description;
		this.weight = weight;
		this.height = height;
		this.width = width;
		this.tax_value = tax_value;
	}

	
	public AbstractProduct(Long id, Long product_category_id, Long producer_id, Double price, String name,
			String description, Double weight, Double height, Double width, Float tax_value) {
		
		this.id = id;
		this.product_category_id = product_category_id;
		this.producer_id = producer_id;
		this.price = price;
		this.name = name;
		this.description = description;
		this.weight = weight;
		this.height = height;
		this.width = width;
		this.tax_value = tax_value;
	}
	
	public AbstractProduct(Long product_category_id, Long producer_id, Double price, String name, String description,
			Float tax_value) {

		this.product_category_id = product_category_id;
		this.producer_id = producer_id;
		this.price = price;
		this.name = name;
		this.description = description;
		this.tax_value = tax_value;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getProduct_category_id() {
		return product_category_id;
	}
	public void setProduct_category_id(Long product_category_id) {
		this.product_category_id = product_category_id;
	}
	public Long getProducer_id() {
		return producer_id;
	}
	public void setProducer_id(Long producer_id) {
		this.producer_id = producer_id;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	public Double getHeight() {
		return height;
	}
	public void setHeight(Double height) {
		this.height = height;
	}
	public Double getWidth() {
		return width;
	}
	public void setWidth(Double width) {
		this.width = width;
	}
	public Float getTax_value() {
		return tax_value;
	}
	public void setTax_value(Float tax_value) {
		this.tax_value = tax_value;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "AbstractProduct [id=" + id + ", product_category_id=" + product_category_id + ", producer_id="
				+ producer_id + ", image=" + Arrays.toString(image) + ", price=" + price + ", name=" + name
				+ ", description=" + description + ", weight=" + weight + ", height=" + height + ", width=" + width
				+ ", tax_value=" + tax_value + "]";
	}
	
	
	
}
