package contract;

import java.util.ArrayList;

import customer.Customer;
import customer.Insurant;
import insurance.Insurance;

public class Contract {
	// Attributes
	private String contractId;
	private String insurantId;
	private String insuranceId;
	private boolean effectiveness;
	private boolean special;
	private int lifespan;
	private int fee;
	private int paidFee;
	private int unpaidPeriod;
	private boolean[] payHistory = new boolean[12];
	private ArrayList<Accident> accidentList;
	
	private ContractDAO contractDAO;
	private AccidentDAO accidentDAO;
	

	// Constructor
	public Contract(){
		accidentDAO = new AccidentDAOImpl();
	}
	
	// Getters&Setters
	public String getContractId() {return contractId;}
	public void setContractId(String contractId) {this.contractId = contractId;}
	
	public String getInsurantId() {return insurantId;}
	public void setInsurantId(String insurantId) {this.insurantId = insurantId;}

	public String getInsuranceId() {return insuranceId;}
	public void setInsuranceId(String insuranceId) {this.insuranceId = insuranceId;}

	public boolean isEffectiveness() {return effectiveness;}
	public void setEffectiveness(boolean effectiveness) {this.effectiveness = effectiveness;}
	
	public boolean isSpecial() {return special;}
	public void setSpecial(boolean special) {this.special = special;}

	public int getFee() {return fee;}
	public void setFee(int fee) {this.fee = fee;}

	public int getLifespan() {return lifespan;}
	public void setLifespan(int lifespan) {this.lifespan = lifespan;}

	public int getPaidFee() {return paidFee;}
	public void setPaidFee(int paidFee) {this.paidFee = paidFee;}

	public int getUnpaidPeriod() {return unpaidPeriod;}
	public void setUnpaidPeriod(int unpaidPeriod) {this.unpaidPeriod = unpaidPeriod;}
		
	public ArrayList<Accident> getAccidentList() {return accidentList;}
	public void setAccidentList(ArrayList<Accident> accidentList) {this.accidentList = accidentList;}

	public boolean[] getPayHistory() {return payHistory;}
	public void setPayHistory(boolean[] payHistory) {this.payHistory = payHistory;}
	
	public void connectContractDAO(ContractDAO contractDAO) {this.contractDAO = contractDAO;}
	
	// public Method
	public void joinInsurance(Customer customer, Insurance insurance, Insurant insurant){
		this.insuranceId = insurance.getInsuranceId();
		this.insurantId = insurant.getInsurantId();
	}

	public void addAccident(String accidentId, String content, int damageCost, boolean handlingStatus) {
		Accident accident = new Accident();
		accident.setContractId(this.contractId);
		accident.setAccidentId(accidentId);
		accident.setContent(content);
		accident.setDamageCost(damageCost);
		accident.setHandlingStatus(handlingStatus);
		this.accidentDAO.insert(accident);
	}

	public void payFee(Contract contract, int month) {
		if (this.contractDAO.updatePayHistory(contract.getContractId(), month)) {
			contract.getPayHistory()[month] = true;
		}
	}

	public void bringAccident() {
		this.accidentList = this.accidentDAO.selectByContractId(this.contractId);
	}

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