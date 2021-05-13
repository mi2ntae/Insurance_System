package contract;

import customer.Customer;
import customer.Insurant;
import insurance.Insurance;

public class Contract {
	// Attributes
	private String contractId;
	private Customer customer;
	private Insurance insurance;
	private Insurant insurant;
	private boolean effectiveness;
	private int lifespanOfContract;
	private int paidFee;
	private int salespersonId;
	private int unpaidPeriod;
	
	// Composition Class
	public Accident accident;

	// Constructor
	public Contract(){
		
	}
	
	// Getters&Setters
	public String getContractId() {return contractId;}
	public void setContractId(String contractId) {this.contractId = contractId;}

	public Customer getCustomer() {return customer;}
	public void setCustomer(Customer customerId) {this.customer = customerId;}

	public boolean isEffectiveness() {return effectiveness;}
	public void setEffectiveness(boolean effectiveness) {this.effectiveness = effectiveness;}

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

	public Insurant getInsurant() {return insurant;}
	public void setInsurant(Insurant insurant) {this.insurant = insurant;}

	public Accident getAccident() {return accident;}
	public void setAccident(Accident accident) {this.accident = accident;}

	// public Method
	public void joinInsurance(Customer customer, Insurance insurance, Insurant insurant){
		this.customer = customer;
		this.insurance = insurance;
		this.insurant = insurant;
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