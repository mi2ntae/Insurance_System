package customer;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class Customer {
	// Attributes
	private String name;
	private String address;
	private String phoneNumber;
	private String customerId;
	private String password;
	
	// Composition Class
	private ArrayList<Insurant> insurantList;
	
	private InsurantDAO insurantDAO;

	// Constructor
	public Customer(){
		this.insurantList = new ArrayList<Insurant>();
		this.insurantDAO = new InsurantDAOImpl();
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
	
	public ArrayList<Insurant> getInsurantList() {return insurantList;}
	public void setInsurantList(ArrayList<Insurant> insurantList) {this.insurantList = insurantList;}
	
	public String getPassword() {return password;}
	public void setPassword(String password) {this.password = password;}

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

	public void createInsurant(Insurant insurant) {
		this.insurantDAO.insert(insurant);
	}
}