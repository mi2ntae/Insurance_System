package contract;

import java.util.Scanner;

import customer.Customer;
import customer.Insurant;
import insurance.Insurance;

public class Contract {
	// Attributes
	private String contractId;
	private String insurantId;
	private String insuranceId;
	private String salespersonId;
	private boolean effectiveness;
	private boolean special;
	private int lifespanOfContract;
	private int fee;
	private int paidFee;
	private int unpaidPeriod;
	
	// Composition Class
	public AccidentList accidentList;
	
	// Associate
	private Customer customer;
	private Insurance insurance;
	private Insurant insurant;

	// Constructor
	public Contract(){
		accidentList = new AccidentListImpl();
	}
	
	// Getters&Setters
	public String getContractId() {return contractId;}
	public void setContractId(String contractId) {this.contractId = contractId;}
	
	public String getInsurantId() {return insurantId;}
	public void setInsurantId(String insurantId) {this.insurantId = insurantId;}

	public String getInsuranceId() {return insuranceId;}
	public void setInsuranceId(String insuranceId) {this.insuranceId = insuranceId;}

	public Customer getCustomer() {return customer;}
	public void setCustomer(Customer customerId) {this.customer = customerId;}

	public boolean isEffectiveness() {return effectiveness;}
	public void setEffectiveness(boolean effectiveness) {this.effectiveness = effectiveness;}
	
	public boolean isSpecial() {return special;}
	public void setSpecial(boolean special) {this.special = special;}

	public int getFee() {return fee;}
	public void setFee(int fee) {this.fee = fee;}

	public int getLifespanOfContract() {return lifespanOfContract;}
	public void setLifespanOfContract(int lifespanOfContract) {this.lifespanOfContract = lifespanOfContract;}

	public int getPaidFee() {return paidFee;}
	public void setPaidFee(int paidFee) {this.paidFee = paidFee;}

	public String getSalespersonId() {return salespersonId;}
	public void setSalespersonId(String salespersonId) {this.salespersonId = salespersonId;}

	public int getUnpaidPeriod() {return unpaidPeriod;}
	public void setUnpaidPeriod(int unpaidPeriod) {this.unpaidPeriod = unpaidPeriod;}

	public Insurance getInsurance() {return insurance;}
	public void setInsurance(Insurance insurance) {this.insurance = insurance;}

	public Insurant getInsurant() {return insurant;}
	public void setInsurant(Insurant insurant) {this.insurant = insurant;}
		
	public AccidentList getAccidentList() {return accidentList;}
	public void setAccidentList(AccidentList accidentList) {this.accidentList = accidentList;}

	// public Method
	public void joinInsurance(Customer customer, Insurance insurance, Insurant insurant){
		this.customer = customer;
		this.insurance = insurance;
		this.insurant = insurant;
	}

	public void addAccident(String accidentId, String content, int damageCost, boolean handlingStatus) {
		Accident accident = new Accident();
		accident.setContractId(this.contractId);
		accident.setAccidentId(accidentId);
		accident.setContent(content);
		accident.setDamageCost(damageCost);
		accident.setHandlingStatus(handlingStatus);
		this.accidentList.insert(accident);
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

	public String writeToFile() {
		String output = this.contractId + ' ' + this.insurantId + ' ' + this.insuranceId + ' ' + this.salespersonId
				+ ' ' + String.valueOf(this.effectiveness) + ' ' + String.valueOf(this.lifespanOfContract) + ' '
				+ String.valueOf(this.fee) + ' ' + String.valueOf(this.paidFee) + ' '
				+ String.valueOf(this.unpaidPeriod) + ' ' + String.valueOf(this.special) + '\n';
		return output;
	}

	public void readFromFile(Scanner scn) {
		this.contractId = scn.next();
		this.insurantId = scn.next();
		this.insuranceId = scn.next();
		this.salespersonId = scn.next();
		this.effectiveness = Boolean.parseBoolean(scn.next());
		this.lifespanOfContract = scn.nextInt();
		this.fee = scn.nextInt();
		this.paidFee = scn.nextInt();
		this.unpaidPeriod = scn.nextInt();
		this.special = Boolean.parseBoolean(scn.next());
	}

}