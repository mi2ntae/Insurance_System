package insurance;

import java.util.Scanner;

import global.Constants.eEmployeeRole;
import global.Constants.eGender;
import global.Constants.eInsuranceType;

public abstract class Insurance {

	// Attributes
	private String insuranceId;
	private String name;
	private eInsuranceType type;
	private eGender gender;
	private int basicFee;
	private double[] rateOfAge = {1.1, 1.0, 1.0, 1.1, 1.2, 1.3, 1.4}; // index[0 : 영유아/ 1 : 10대/ 2 : 20대/ 3 : 30대/ 4 : 40대/ 5 : 50대/ 6 : 노년층]
	private double[] rateOfGender = {1.0, 1.1}; // index[0 : 남성/ 1 : 여성]
	private double[] rateOfJob = {1.0, 1.2, 1.3, 1.0, 1.0, 1.2, 1.1}; // index[0 : 직장인/ 1 : 운전기사/ 2 : 공장 노동직/ 3 : 학생/ 4 : 교사(수)직/ 5 : 군인/ 6 : 기타]
	private int specialContractFee;
	private int warrantyPeriod;
	private boolean confirmedStatus = false;
	private boolean specialContract;
	private boolean clone;
	
	// Composition Class
	private MembershipCondition membershipCondition;
	private GuaranteePlan guaranteePlan;

	// Constructor
	public Insurance(){
		this.membershipCondition = new MembershipCondition();
		this.guaranteePlan = new GuaranteePlan();
	}
	
	// getters & setters
	public int getBasicFee() {return basicFee;}
	public void setBasicFee(int basicFee) {this.basicFee = basicFee;}

	public boolean isConfirmedStatus() {return confirmedStatus;}
	public void setConfirmedStatus(boolean confirmedStatus) {this.confirmedStatus = confirmedStatus;}

	public String getInsuranceId() {return insuranceId;}
	public void setInsuranceId(String insuranceId) {this.insuranceId = insuranceId;}

	public String getName() {return name;}
	public void setName(String name) {this.name = name;}

	public eInsuranceType getType() {return type;}
	public void setType(eInsuranceType type) {this.type = type;}
	
	public double[] getRateOfAge() {return rateOfAge;}
	public void setRateOfAge(double[] rateOfAge) {this.rateOfAge = rateOfAge;}

	public double[] getRateOfGender() {return rateOfGender;}
	public void setRateOfGender(double[] rateOfGender) {this.rateOfGender = rateOfGender;}

	public double[] getRateOfJob() {return rateOfJob;}
	public void setRateOfJob(double[] rateOfJob) {this.rateOfJob = rateOfJob;}
	
	public int getSpecialContractFee() {return specialContractFee;}
	public void setSpecialContractFee(int specialContractFee) {this.specialContractFee = specialContractFee;}

	public int getWarrantyPeriod() {return warrantyPeriod;}
	public void setWarrantyPeriod(int warrantyPeriod) {this.warrantyPeriod = warrantyPeriod;}

	public MembershipCondition getM_membershipCondition() {return membershipCondition;}
	public void setM_membershipCondition(MembershipCondition m_membershipCondition) {this.membershipCondition = m_membershipCondition;}

	public GuaranteePlan getM_GuaranteePlan() {return guaranteePlan;}
	public void setM_GuaranteePlan(GuaranteePlan m_GuaranteePlan) {this.guaranteePlan = m_GuaranteePlan;}
	
	public eGender getGender() {return gender;}
	public void setGender(eGender gender) {this.gender = gender;}
	
	public boolean isSpecialContract() {return specialContract;}
	public void setSpecialContract(boolean specialContract) {this.specialContract = specialContract;}
	
	public boolean isClone() {return clone;}
	public void setClone(boolean clone) {this.clone = clone;}
	
	// Public Methods
	abstract public int calculateFee(int insurantId);
	
	public String writeToFile() {
		String output = null;
		output = this.insuranceId + ' ' + this.name + ' ' + this.type.getNum() + ' ' + this.gender.getNum() + ' ' + this.basicFee + ' ' + this.specialContractFee + ' ';
		for (double rate : rateOfAge) {
			output += rate;
			output += ' ';
		}
		for (double rate : rateOfGender) {
			output += rate;
			output += ' ';
		}
		for (double rate : rateOfJob) {
			output += rate;
			output += ' ';
		}
		output += this.warrantyPeriod;
		output += ' ' + String.valueOf(this.specialContract) + ' ' + String.valueOf(this.confirmedStatus) + '\n';
		return output;
	}

	public void readFromFile(Scanner scn) {
		this.insuranceId = scn.next();
		this.name = scn.next();
		int input = scn.nextInt();
		for (eInsuranceType insuranceType : eInsuranceType.values()) {
			if (insuranceType.getNum() == input)
				this.type = insuranceType;
		}
		input = scn.nextInt();
		for (eGender gender : eGender.values()) {
			if (gender.getNum() == input)
				this.gender = gender;
		}
		this.basicFee = scn.nextInt();
		this.specialContractFee = scn.nextInt();
		for (int i = 0; i < this.rateOfAge.length; i++) {
			this.rateOfAge[i] = scn.nextDouble();
		}
		for (int i = 0; i < this.rateOfGender.length; i++) {
			this.rateOfGender[i] = scn.nextDouble();
		}
		for (int i = 0; i < this.rateOfJob.length; i++) {
			this.rateOfJob[i] = scn.nextDouble();
		}
		this.warrantyPeriod = scn.nextInt();
		this.specialContract = Boolean.parseBoolean(scn.next());
		this.confirmedStatus = Boolean.parseBoolean(scn.next());
	}

}