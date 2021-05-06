package customer;


public class Customer {
	// Attributes
	private String address;
	private String customerId;
	private String name;
	private String phoneNumber;
	
	// Composition Class
	private Insurant insurant;
//	private Contract contract;
	//private Salesperson m_Salesperson;

	// Constructor
	public Customer(String address, String customerId, String name, String phoneNumber){
		this.address = address;
		this.customerId = customerId;
		this.name = name;
		this.phoneNumber = phoneNumber;
	}
	
	// getters & setters
	public String getAddress() {return address;}
	public void setAddress(String address) {this.address = address;}

	public String getCustomerId() {return customerId;}
	public void setCustomerId(String customerId) {this.customerId = customerId;}

	public String getName() {return name;}
	public void setName(String name) {this.name = name;}

	public String getPhoneNumber() {return phoneNumber;}
	public void setPhoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber;}

	// Methods
	public void checkJoinedInsuranceList(){

	}

	public void checkPaymentHistory(){

	}

	public void checkWholeInsuranceList(){

	}

	public void requestInterview(){

	}

	public void writeSurvey(){

	}
}