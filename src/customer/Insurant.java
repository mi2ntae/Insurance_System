package customer;

import global.Constants;

public class Insurant {

	// Attributes
	private int accidentHistory;
	private String address;
	private int age;
	private String insurantId;
	private String name;
	private String phoneNumber;
	private int postedPriceOfStructure;
	private int usageOfStructure;
	
	// Composition Class
	public FamilyMedicalHistory m_FamilyMedicalHistory;

	// Constructor
	public Insurant(){
		
	}

	// getters & setters	
	public int getAccidentHistory() {return accidentHistory;}
	public void setAccidentHistory(int accidentHistory) {this.accidentHistory = accidentHistory;}

	public String getAddress() {return address;}
	public void setAddress(String address) {this.address = address;}

	public int getAge() {return age;}
	public void setAge(int age) {this.age = age;}

	public String getInsurantId() {return insurantId;}
	public void setInsurantId(String insurantId) {this.insurantId = insurantId;}

	public String getName() {return name;}
	public void setName(String name) {this.name = name;}

	public String getPhoneNumber() {return phoneNumber;}
	public void setPhoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber;}

	public int getPostedPriceOfStructure() {return postedPriceOfStructure;}
	public void setPostedPriceOfStructure(int postedPriceOfStructure) {	this.postedPriceOfStructure = postedPriceOfStructure;}

	public int getUsageOfStructure() {return usageOfStructure;}
	public void setUsageOfStructure(int usageOfStructure) {this.usageOfStructure = usageOfStructure;}

	public FamilyMedicalHistory getM_FamilyMedicalHistory() {return m_FamilyMedicalHistory;}
	public void setM_FamilyMedicalHistory(FamilyMedicalHistory m_FamilyMedicalHistory) {this.m_FamilyMedicalHistory = m_FamilyMedicalHistory;}



	public void finalize() throws Throwable {

	}

	
}