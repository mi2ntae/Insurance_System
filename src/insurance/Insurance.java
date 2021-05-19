package insurance;

import java.io.FileNotFoundException;
import java.util.Scanner;

import customer.Insurant;
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
	private double[] rateOfJob = {1.0, 1.2, 1.3, 1.0, 1.0, 1.2, 1.1}; // index[0 : 사무직/ 1 : 운송업/ 2 : 현장직/ 3 : 학생/ 4 : 교사(수)직/ 5 : 군인/ 6 : 기타]
	private int specialContractFee;
	private int warrantyPeriod;
	private boolean confirmedStatus = false;
	private boolean specialContract;
	private boolean clone;
	
	// Composition Class
	private MembershipCondition membershipCondition;
	private GuaranteePlanList guaranteePlanList;

	// Constructor
	public Insurance() {
		this.membershipCondition = new MembershipCondition();
		this.guaranteePlanList = new GuaranteePlanListImpl();
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
	
	public eGender getGender() {return gender;}
	public void setGender(eGender gender) {this.gender = gender;}
	
	public boolean isSpecialContract() {return specialContract;}
	public void setSpecialContract(boolean specialContract) {this.specialContract = specialContract;}
	
	public boolean isClone() {return clone;}
	public void setClone(boolean clone) {this.clone = clone;}
	
	public GuaranteePlanList getGuaranteePlanList() {return guaranteePlanList;}
	public void setGuaranteePlanList(GuaranteePlanList guaranteePlanList) {this.guaranteePlanList = guaranteePlanList;}

	// Public Methods
	abstract public int calculateFee(Insurant Insurnat);
	abstract public void readFromSelectedFile(Scanner scn);
	abstract public String writeToSelectedFile();
	abstract public Insurance newInstance();
	
	public void addGuaranteePlan(String content, int compensation, boolean special, double rate) {
		GuaranteePlan guaranteePlan = new GuaranteePlan();
		guaranteePlan.setInsuranceId(this.insuranceId);
		guaranteePlan.setCompensation(compensation);
		guaranteePlan.setSpecial(special);
		guaranteePlan.setContent(content);
		guaranteePlan.setRate(rate);
		this.guaranteePlanList.insert(guaranteePlan);
	}
	
	public String writeToFile() {
		String output = String.valueOf(this.type.getNum()) + ' ' +  this.insuranceId + ' ' + this.name + ' ' + this.gender.getNum() + ' ' + String.valueOf(this.basicFee) + ' ' + String.valueOf(this.specialContractFee) + ' ';
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

	public void readFromFile(Scanner scn, int input) throws FileNotFoundException {
		for (eInsuranceType type : eInsuranceType.values()) {
			if (type.getNum() == input)
				this.type = type;
		}
		this.insuranceId = scn.next();
		this.name = scn.next();
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
		
		((GuaranteePlanListImpl)this.guaranteePlanList).readFromFile(insuranceId);
	}
	

}