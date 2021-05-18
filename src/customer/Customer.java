package customer;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import global.Constants.eGender;
import global.Constants.eJob;
import global.Constants.eTypeOfCar;
import global.Constants.eRiskOfTripCountry;
import global.Constants.eUsageOfStructure;
import insurance.InsuranceListImpl;

public class Customer {
	// Attributes
	private String name;
	private String address;
	private String phoneNumber;
	private String customerId;
	private String password;
	
	// Composition Class
	private InsurantList insurantList;
	// private Contract contract;
	// private Salesperson m_Salesperson;

	// Constructor
	public Customer(){
		try {
			this.insurantList = new InsurantListImpl();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	
	public String writeToFile() {
		String output = null;
		output = this.name + ' ' + this.address + ' ' + this.phoneNumber + ' ' + this.customerId + ' ' + this.password + '\n';
		return output;
		
	}
	
	public void readFromFile(Scanner sc) {
		this.name = sc.next();
		this.address = sc.next();
		this.phoneNumber = sc.next();
		this.customerId = sc.next();
		this.password = sc.next();
	}

	public void createInsurant(Insurant insurant) {
		this.insurantList.insert(insurant);
	}
}