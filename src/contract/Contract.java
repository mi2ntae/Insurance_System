package contract;

import insurace.Insurance;

public class Contract {

	// Attributes
	private int contractId;
	private int customerId;
	private boolean effectiveness;
	private int insuranceId;
	private int lifespanOfContract;
	private int paidFee;
	private int salespersonId;
	private int unpaidPeriod;
	
	// Composition Class
	public Insurance m_Insurance;
	public Accident m_Accident;

	// Constructor
	public Contract(){

	}
	
	// getters & setters
	public int getContractId() {return contractId;}
	public void setContractId(int contractId) {this.contractId = contractId;}

	public int getCustomerId() {return customerId;}
	public void setCustomerId(int customerId) {this.customerId = customerId;}

	public boolean isEffectiveness() {return effectiveness;}
	public void setEffectiveness(boolean effectiveness) {this.effectiveness = effectiveness;}

	public int getInsuranceId() {return insuranceId;}
	public void setInsuranceId(int insuranceId) {this.insuranceId = insuranceId;}

	public int getLifespanOfContract() {return lifespanOfContract;}
	public void setLifespanOfContract(int lifespanOfContract) {this.lifespanOfContract = lifespanOfContract;}

	public int getPaidFee() {return paidFee;}
	public void setPaidFee(int paidFee) {this.paidFee = paidFee;}

	public int getSalespersonId() {return salespersonId;}
	public void setSalespersonId(int salespersonId) {this.salespersonId = salespersonId;}

	public int getUnpaidPeriod() {return unpaidPeriod;}
	public void setUnpaidPeriod(int unpaidPeriod) {this.unpaidPeriod = unpaidPeriod;}

	public Insurance getM_Insurance() {return m_Insurance;}
	public void setM_Insurance(Insurance m_Insurance) {this.m_Insurance = m_Insurance;}

	public Accident getM_Accident() {return m_Accident;}
	public void setM_Accident(Accident m_Accident) {this.m_Accident = m_Accident;}



	public void finalize() throws Throwable {

	}

	
	public void joinInsurance(int customerId, int insuranceId){

	}

	
//	public boolean payFee(enum monthOfPayment){
//		return false;
//	}

	
//	public void reportAccident(String content, enum type){
//
//	}

	public void requestEmergencyRescueService(String location){

	}

	public void requestInsuranceRecharter(){

	}

	public void requestInsuranceRevival(){

	}

}