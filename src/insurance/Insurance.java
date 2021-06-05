package insurance;

import java.util.ArrayList;
import java.util.Scanner;

import customer.Insurant;
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
	private boolean del;
	private boolean clone;
	
	
	// Component
	private GuaranteePlanDAO guaranteePlanDAO;
	
	// Composition Class
	private MembershipCondition membershipCondition;
	private ArrayList<GuaranteePlan> guaranteePlanList;

	// Constructor
	public Insurance() {
		this.guaranteePlanDAO = new GuaranteePlanDAOImpl();
		this.membershipCondition = new MembershipCondition();
		this.guaranteePlanList = new ArrayList<GuaranteePlan>();
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
	
	public ArrayList<GuaranteePlan> getGuaranteePlanList() {return guaranteePlanList;}
	public void setGuaranteePlanList(ArrayList<GuaranteePlan> guaranteePlanList) {this.guaranteePlanList = guaranteePlanList;}
	
	public GuaranteePlanDAO getGuaranteePlanDAO() {return guaranteePlanDAO;}
	public void setGuaranteePlanDAO(GuaranteePlanDAO guaranteePlanDAO) {this.guaranteePlanDAO = guaranteePlanDAO;}
	
	public boolean isDel() {return del;}
	public void setDel(boolean del) {this.del = del;}

	// Public Methods
	abstract public int calculateFee(Insurant Insurnat);
	abstract public void readFromSelectedFile(Scanner scn);
	abstract public String writeToSelectedFile();
	abstract public Insurance newInstance();
	
	public void birngGuaranteePlan() {
		this.guaranteePlanList = this.guaranteePlanDAO.selectById(this.insuranceId);
	}
	
	public void addGuaranteePlan(String content, int compensation, boolean special, double rate) {
		GuaranteePlan guaranteePlan = new GuaranteePlan();
		guaranteePlan.setInsuranceId(this.insuranceId);
		guaranteePlan.setCompensation(compensation);
		guaranteePlan.setSpecial(special);
		guaranteePlan.setContent(content);
		guaranteePlan.setRate(rate);
		this.guaranteePlanDAO.insert(guaranteePlan);
	}
}