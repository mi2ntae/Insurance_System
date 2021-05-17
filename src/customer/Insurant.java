package customer;

import java.util.Scanner;

import global.Constants.eGender;
import global.Constants.eJob;
import global.Constants.eRankOfCar;
import global.Constants.eRiskOfTripCountry;
import global.Constants.eUsageOfStructure;

public class Insurant {

	// Attributes
	private String insurantId;
	private String name;
	private String address;
	private String phoneNumber;
	private int age;
	private int accidentHistory;
	private long postedPriceOfStructure;
	private eUsageOfStructure usageOfStructure;
	private eGender gender;
	private eJob job;
	private eRankOfCar rankOfCar;
	private eRiskOfTripCountry riskOfTripCountry;
	
	// Composition Class
	public FamilyMedicalHistory familyMedicalHistory;

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

	public long getPostedPriceOfStructure() {return postedPriceOfStructure;}
	public void setPostedPriceOfStructure(long postedPriceOfStructure) {this.postedPriceOfStructure = postedPriceOfStructure;}

	public eGender getGender() {return gender;}
	public void setGender(eGender gender) {this.gender = gender;}

	public eUsageOfStructure getUsageOfStructure() {return usageOfStructure;}
	public void setUsageOfStructure(eUsageOfStructure usageOfStructure) {this.usageOfStructure = usageOfStructure;}

	public eJob getJob() {return job;}
	public void setJob(eJob job) {this.job = job;}

	public eRankOfCar getRankOfCar() {return rankOfCar;}
	public void setRankOfCar(eRankOfCar rankOfCar) {this.rankOfCar = rankOfCar;}

	public FamilyMedicalHistory getFamilyMedicalHistory() {return familyMedicalHistory;}
	public void setFamilyMedicalHistory(FamilyMedicalHistory familyMedicalHistory) {this.familyMedicalHistory = familyMedicalHistory;}

	public eRiskOfTripCountry getRiskOfTripCountry() {return riskOfTripCountry;}
	public void setRiskOfTripCountry(eRiskOfTripCountry riskOfTripCountry) {this.riskOfTripCountry = riskOfTripCountry;}
	
	// Public Methods
	public String writeToFile() {
		String output = null;
		output = this.insurantId + ' ' + this.name + ' ' + this.address + ' ' + this.phoneNumber + ' ' + String.valueOf(this.age)+ ' '
				+ String.valueOf(this.accidentHistory) + ' ' + String.valueOf(this.postedPriceOfStructure) + ' ' + this.usageOfStructure.getNum()
				+ ' ' + this.gender.getNum() + ' ' + this.job.getNum() + ' ' + this.rankOfCar.getNum();
		
		return output;
		
	}
	
	public void readFromFile(Scanner sc) {
		this.name = sc.next();
		this.address = sc.next();
		this.phoneNumber = sc.next();
		this.customerId = sc.next();
		this.password = sc.next();
	}
	
}