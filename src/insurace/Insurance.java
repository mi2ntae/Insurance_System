package insurace;

public abstract class Insurance {

	// Attributes
	private int basicFee;
	private boolean confirmedStatus;
	private String insuranceId;
	private String name;
	private float[] rateOfAge; // index[0 : 영유아/ 1 : 10대/ 2 : 20대/ 3 : 30대/ 4 : 40대/ 5 : 50대/ 6 : 노년층]
	private float[] rateOfGender; // index[0 : 남성/ 1 : 여성]
	private float[] rateOfJob; // index[0 : 직장인/ 1 : 운전기사/ 2 : 공장 노동직/ 3 : 학생/ 4 : 교사(수)직/ 5 : 군인/ 6 : 기타]
	private int specialContractFee;
	private int warrantyPeriod;
	
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

	public float[] getRateOfAge() {return rateOfAge;}
	public void setRateOfAge(float[] rateOfAge) {this.rateOfAge = rateOfAge;}

	public float[] getRateOfGender() {return rateOfGender;}
	public void setRateOfGender(float[] rateOfGender) {this.rateOfGender = rateOfGender;}

	public float[] getRateOfJob() {return rateOfJob;}
	public void setRateOfJob(float[] rateOfJob) {this.rateOfJob = rateOfJob;}
	
	public int getSpecialContractFee() {return specialContractFee;}
	public void setSpecialContractFee(int specialContractFee) {this.specialContractFee = specialContractFee;}

	public int getWarrantyPeriod() {return warrantyPeriod;}
	public void setWarrantyPeriod(int warrantyPeriod) {this.warrantyPeriod = warrantyPeriod;}

	public MembershipCondition getM_membershipCondition() {return membershipCondition;}
	public void setM_membershipCondition(MembershipCondition m_membershipCondition) {this.membershipCondition = m_membershipCondition;}

	public GuaranteePlan getM_GuaranteePlan() {return guaranteePlan;}
	public void setM_GuaranteePlan(GuaranteePlan m_GuaranteePlan) {this.guaranteePlan = m_GuaranteePlan;}
	
	public void finalize() throws Throwable {
	}
	
	// Methods
	abstract public int calculateFee(int insurantId);

}