package business.customer;

import java.util.Scanner;

import global.Constants.eFamilyMedicalDisease;
import global.Constants.eFamilyMedicalRelationship;
import global.Constants.eGender;
import global.Constants.eJob;
import global.Constants.eRankOfCar;
import global.Constants.eRiskOfTripCountry;
import global.Constants.eTypeOfCar;
import global.Constants.eUsageOfStructure;

public class Insurant {

	// Attributes
	private String insurantId;
	private String customerId;
	private String name;
	private String address;
	private String phoneNumber;
	private int age;
	private int accidentHistory;
	private long postedPriceOfStructure;
	private eUsageOfStructure usageOfStructure;
	private eGender gender;
	private eJob job;
	private eTypeOfCar typeOfCar;
	private eRankOfCar rankOfCar;
	private eRiskOfTripCountry riskOfTripCountry;
	private eFamilyMedicalDisease familyMedicalDisease;
	private eFamilyMedicalRelationship familyMedicalRelationship;

	// Constructor
	public Insurant(){
		
	}

	// getters & setters	
	public String getCustomerId() {return customerId;}
	public void setCustomerId(String customerId) {this.customerId = customerId;}
	
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

	public eTypeOfCar getTypeOfCar() {return typeOfCar;}
	public void setTypeOfCar(eTypeOfCar typeOfCar) {this.typeOfCar = typeOfCar;}

	public eRankOfCar getRankOfCar() {return rankOfCar;}
	public void setRankOfCar(eRankOfCar rankOfCar) {this.rankOfCar = rankOfCar;}
	
	public eRiskOfTripCountry getRiskOfTripCountry() {return riskOfTripCountry;}
	public void setRiskOfTripCountry(eRiskOfTripCountry riskOfTripCountry) {this.riskOfTripCountry = riskOfTripCountry;}

	public eFamilyMedicalDisease getFamilyMedicalDisease() {return familyMedicalDisease;}
	public void setFamilyMedicalDisease(eFamilyMedicalDisease familyMedicalDisease) {this.familyMedicalDisease = familyMedicalDisease;}

	public eFamilyMedicalRelationship getFamilyMedicalRelationship() {return familyMedicalRelationship;}
	public void setFamilyMedicalRelationship(eFamilyMedicalRelationship familyMedicalRelationship) {this.familyMedicalRelationship = familyMedicalRelationship;}

}
