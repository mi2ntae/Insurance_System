package contract;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Scanner;

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

	public void setCause(String cause) {
		File file = new File("data/compensationCause");
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
			writer.append(this.accidentId + " " + cause);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String writeToFile() {
		String output;
		output = this.contractId + " " + this.accidentId + " " + this.content + " " + this.compensation + " " + this.damageCost + " " + String.valueOf(this.handlingStatus) + "\n"; 
		return output;
	}

	public void readFromFile(Scanner scn, String input) {
		this.contractId = input;
		this.accidentId = scn.next();
		this.content = scn.next();
		this.compensation = scn.nextInt();
		this.damageCost = scn.nextInt();
		this.handlingStatus = Boolean.parseBoolean(scn.next());
	}
}