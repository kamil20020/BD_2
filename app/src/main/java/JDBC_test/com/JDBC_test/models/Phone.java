package JDBC_test.com.JDBC_test.models;

public class Phone {

	private Long id;
	private Long person_id;
	private String phone_number;
	
	public Phone(Long id, Long person_id, String phone_number) {
		
		this.id = id;
		this.person_id = person_id;
		this.phone_number = phone_number;
	}
	
	public Phone(Long person_id, String phone_number) {
		
		this.person_id = person_id;
		this.phone_number = phone_number;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getPerson_id() {
		return person_id;
	}
	
	public void setPerson_id(Long person_id) {
		this.person_id = person_id;
	}
	
	public String getPhone_number() {
		return phone_number;
	}
	
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	
}
