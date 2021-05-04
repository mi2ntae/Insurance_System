package contract;

public class Accident {

	// Attributes
	private int accidentId;
	private int compensation;
	private String content;
	private int contractId;
	private boolean handlingStatus;
	private enum type{};

	// Constructor
	public Accident(){

	}
	
	public void finalize() throws Throwable {

	}

	// getters & setters
	public int getAccidentId() {return accidentId;}
	public void setAccidentId(int accidentId) {this.accidentId = accidentId;}

	public int getCompensation() {return compensation;}
	public void setCompensation(int compensation) {this.compensation = compensation;}

	public String getContent() {return content;}
	public void setContent(String content) {this.content = content;}

	public int getContractId() {return contractId;}
	public void setContractId(int contractId) {this.contractId = contractId;}

	public boolean isHandlingStatus() {return handlingStatus;}
	public void setHandlingStatus(boolean handlingStatus) {this.handlingStatus = handlingStatus;}

}