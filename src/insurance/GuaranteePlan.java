package insurance;

import java.util.Scanner;

import global.Constants.eEmployeeRole;

public class GuaranteePlan {
	// Attributes
	private String insuranceId;
	private boolean special;
	private String content;
	private int compensation;
	private double rate;

	// Constructor
	public GuaranteePlan(){

	}

	// getters & setters
	public String getInsuranceId() {return insuranceId;}
	public void setInsuranceId(String insuranceId) {this.insuranceId = insuranceId;}

	public boolean isSpecial() {return special;}
	public void setSpecial(boolean special) {this.special = special;}

	public String getContent() {return content;}
	public void setContent(String content) {this.content = content;}

	public int getCompensation() {return compensation;}
	public void setCompensation(int compensation) {this.compensation = compensation;}

	public double getRate() {return rate;}
	public void setRate(double rate) {this.rate = rate;}
	
	// Public Methods
	public String writeToFile() {
		String output = null;
		output = this.insuranceId + ' ' + String.valueOf(this.special) + ' ' + this.content + ' ' + this.compensation + ' ' + this.rate + '\n';
		return output;
		
	}
	
	public void readFromFile(Scanner scn, String input) {
		this.insuranceId = input;
		this.special = Boolean.parseBoolean(scn.next());
		this.content = scn.next();
		this.compensation = scn.nextInt();
		this.rate = scn.nextDouble();
	}
}