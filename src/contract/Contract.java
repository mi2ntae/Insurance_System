package contract;

import insurace.Insurance;

public class Contract {
	// Attributes
	private String contractId;
	private String customerId;
	private String insuranceId;
	private boolean effectiveness;
	private int lifespanOfContract;
	private int paidFee;
	private int salespersonId;
	private int unpaidPeriod;
	
	// Composition Class
	public Insurance insurance;
	public Accident accident;

	// Constructor
	public Contract(){
		
	}
	
	// Getters&Setters
	public String getContractId() {return contractId;}
	public void setContractId(String contractId) {this.contractId = contractId;}

	public String getCustomerId() {return customerId;}
	public void setCustomerId(String customerId) {this.customerId = customerId;}

	public boolean isEffectiveness() {return effectiveness;}
	public void setEffectiveness(boolean effectiveness) {this.effectiveness = effectiveness;}

	public String getInsuranceId() {return insuranceId;}
	public void setInsuranceId(String insuranceId) {this.insuranceId = insuranceId;}

	public int getLifespanOfContract() {return lifespanOfContract;}
	public void setLifespanOfContract(int lifespanOfContract) {this.lifespanOfContract = lifespanOfContract;}

	public int getPaidFee() {return paidFee;}
	public void setPaidFee(int paidFee) {this.paidFee = paidFee;}

	public int getSalespersonId() {return salespersonId;}
	public void setSalespersonId(int salespersonId) {this.salespersonId = salespersonId;}

	public int getUnpaidPeriod() {return unpaidPeriod;}
	public void setUnpaidPeriod(int unpaidPeriod) {this.unpaidPeriod = unpaidPeriod;}

	public Insurance getInsurance() {return insurance;}
	public void setInsurance(Insurance insurance) {this.insurance = insurance;}

	public Accident getAccident() {return accident;}
	public void setAccident(Accident accident) {this.accident = accident;}

	// public Method
	public void joinInsurance(String customerId, String insuranceId){
		this.customerId = customerId;
		this.insuranceId = insuranceId;
	}
//
//	public boolean payFee(enum monthOfPayment){
//		return false;
//	}
//
//	public void reportAccident(String content, enum type){
//
//	}
//
//	public void requestEmergencyRescueService(String location){
//
//	}
//
//	public void requestInsuranceRecharter(){
//
//	}
//
//	public void requestInsuranceRevival(){
//
//	}

}