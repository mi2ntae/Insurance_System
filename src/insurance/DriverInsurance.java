package insurance;

import java.util.Scanner;

import customer.Insurant;
import global.Constants.eGender;

public class DriverInsurance extends Insurance {

	// Attributes
	private double[] rateOfAccident = {1.0, 1.2, 1.3, 1.4};
	private double[] rateOfCarRank = {2, 1.5, 1.0};

	// Constructor
	public DriverInsurance(){

	}
	
	// Getters & Setters
	public double[] getRateOfAccident() {return rateOfAccident;}
		public void setRateOfAccident(double[] rateOfAccident) {this.rateOfAccident = rateOfAccident;}
	
	public double[] getRateOfCarRank() {return rateOfCarRank;}	
	public void setRateOfCarRank(double[] rateOfCarRank) {this.rateOfCarRank = rateOfCarRank;}
	
	// Methods
	public int calculateFee(Insurant insurant){
		double fee = this.getBasicFee();
		
		// 나이에 따른 요율 계산
		if (insurant.getAge() >= 10 && insurant.getAge() < 20) {
			fee *= this.getRateOfAge()[0];
		} else if (insurant.getAge() >= 20 && insurant.getAge() < 30) {
			fee = this.getBasicFee()*this.getRateOfAge()[1];
		} else if (insurant.getAge() >= 30 && insurant.getAge() < 40) {
			fee = this.getBasicFee()*this.getRateOfAge()[2];
		} else if (insurant.getAge() >= 40 && insurant.getAge() < 50) {
			fee = this.getBasicFee()*this.getRateOfAge()[3];
		} else if (insurant.getAge() >= 50 && insurant.getAge() < 60) {
			fee = this.getBasicFee()*this.getRateOfAge()[4];
		} else if (insurant.getAge() >= 60 && insurant.getAge() < 70) {
			fee = this.getBasicFee()*this.getRateOfAge()[5];
		} else {
			fee = this.getBasicFee()*this.getRateOfAge()[6];
		}
				
		// 성별에 따른 요율 계산
		if (insurant.getGender() == eGender.male) {
			fee *= this.getRateOfGender()[0];
		} else if (insurant.getGender() == eGender.female){
			fee *= this.getRateOfGender()[1];
		}
		
		
		//자동차 등급에 따른 요율 계산
		switch(insurant.getRankOfCar()) {
		case high :
			fee *= this.rateOfCarRank[0];
			break;
		case middle :
			fee *= this.rateOfCarRank[1];
			break;
		case low :
			fee *= this.rateOfCarRank[2];
			break;
		}
		
		
		return (int)fee;
	}
	
	public String writeToSelectedFile() {
		String output = this.getInsuranceId() + ' ';
		for (double rate : rateOfAccident) {
			output += rate;
			output += ' ';
		}
		for (double rate : rateOfCarRank) {
			output += rate;
			output += ' ';
		}
		output += '\n';
		return output;
	}

	public void readFromSelectedFile(Scanner scn) {
		for (int i = 0; i < this.rateOfAccident.length; i++) {
			this.rateOfAccident[i] = scn.nextDouble();
		}
		for (int i = 0; i < this.rateOfCarRank.length; i++) {
			this.rateOfCarRank[i] = scn.nextDouble();
		}
	}

}