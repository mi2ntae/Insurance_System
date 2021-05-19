package contract;

public class Accident {
	// Attributes
	private String accidentId;
	private String contractId;
	private String content;
	private int compensation;
	private int damageCost;
	private boolean handlingStatus;
	
	// Constructor
	public Accident(){

	}

	// Getters&Setters
	public String getAccidentId() {return accidentId;}
	public void setAccidentId(String accidentId) {this.accidentId = accidentId;}

	public int getCompensation() {return compensation;}
	public void setCompensation(int compensation) {this.compensation = compensation;}

	public String getContent() {return content;}
	public void setContent(String content) {this.content = content;}

	public String getContractId() {return contractId;}
	public void setContractId(String contractId) {this.contractId = contractId;}

	public boolean isHandlingStatus() {return handlingStatus;}
	public void setHandlingStatus(boolean handlingStatus) {this.handlingStatus = handlingStatus;}

	public int getDamageCost() {return damageCost;}
	public void setDamageCost(int damageCost) {this.damageCost = damageCost;}	
}