package customer;

import java.util.ArrayList;

import global.Constants.eGender;
import global.Constants.eJob;
import global.Constants.eRankOfCar;
import global.Constants.eRiskOfTripCountry;
import global.Constants.eUsageOfStructure;
import insurance.InsuranceListImpl;

public class Customer {
	// Attributes
	private String address;
	private String customerId;
	private String name;
	private String phoneNumber;
	private String password;
	
	// Composition Class
	private InsurantList insurantList;
	// private Contract contract;
	// private Salesperson m_Salesperson;

	// Constructor
	public Customer(){
		this.insurantList = new InsurantListImpl();
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
	
	public InsurantList getInsurantList() {return insurantList;}
	public void setInsurantList(InsurantList insurantList) {this.insurantList = insurantList;}
	
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
		this.insurantList.insert(insurant);
	}
}