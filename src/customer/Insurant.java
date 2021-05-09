package customer;

import global.Constants.eInsurantGender;
import global.Constants.eInsurantJob;
import global.Constants.eInsurantRankOfCar;
import global.Constants.eInsurantRiskOfTripCountry;
import global.Constants.eInsurantUsageOfStructure;

public class Insurant {

	// Attributes
	private int accidentHistory;
	private String address;
	private int age;
	private String insurantId;
	private String name;
	private String phoneNumber;
	private long postedPriceOfStructure;
	private eInsurantUsageOfStructure usageOfStructure;
	private eInsurantGender gender;
	private eInsurantJob job;
	private eInsurantRankOfCar rankOfCar;
	private eInsurantRiskOfTripCountry riskOfTripCountry;
	
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

	public eInsurantGender getGender() {return gender;}
	public void setGender(eInsurantGender gender) {this.gender = gender;}

	public eInsurantUsageOfStructure getUsageOfStructure() {return usageOfStructure;}
	public void setUsageOfStructure(eInsurantUsageOfStructure usageOfStructure) {this.usageOfStructure = usageOfStructure;}

	public eInsurantJob getJob() {return job;}
	public void setJob(eInsurantJob job) {this.job = job;}

	public eInsurantRankOfCar getRankOfCar() {return rankOfCar;}
	public void setRankOfCar(eInsurantRankOfCar rankOfCar) {this.rankOfCar = rankOfCar;}

	public FamilyMedicalHistory getFamilyMedicalHistory() {return familyMedicalHistory;}
	public void setFamilyMedicalHistory(FamilyMedicalHistory familyMedicalHistory) {this.familyMedicalHistory = familyMedicalHistory;}

	public eInsurantRiskOfTripCountry getRiskOfTripCountry() {return riskOfTripCountry;}
	public void setRiskOfTripCountry(eInsurantRiskOfTripCountry riskOfTripCountry) {this.riskOfTripCountry = riskOfTripCountry;}	
	
}