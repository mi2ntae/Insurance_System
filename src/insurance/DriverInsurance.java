package insurance;

import java.util.Scanner;

import customer.Insurant;
import global.Constants.eGender;

public class DriverInsurance extends Insurance {

	// Attributes
	private double[] rateOfAccidentHistory = {1.0, 1.2, 1.3, 1.4, 1.5, 1.6};
	private double[] rateOfCarType = {2, 1.7, 1.6, 1.5, 1.0};
	private double[] rateOfCarRank = {2, 1.8, 1.2, 1.0};

	// Constructor
	public DriverInsurance(){

	}
	
	// Getters & Setters
	public double[] getRateOfAccidentHistory() {return rateOfAccidentHistory;}
	public void setRateOfAccidentHistory(double[] rateOfAccidentHistory) {this.rateOfAccidentHistory = rateOfAccidentHistory;}
	
	
	public double[] getRateOfCarType() {return rateOfCarType;}
	public void setRateOfCarType(double[] rateOfCarType) {this.rateOfCarType = rateOfCarType;}

	public double[] getRateOfCarRank() {return rateOfCarType;}	
	public void setRateOfCarRank(double[] rateOfCarRank) {this.rateOfCarType = rateOfCarRank;}
	
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
		switch(insurant.getTypeOfCar()) {
		case bus :
			fee *= this.rateOfCarType[0];
			break;
		case van :
			fee *= this.rateOfCarType[1];
			break;
		case suv :
			fee *= this.rateOfCarType[2];
			break;
		case foreign :
			fee *= this.rateOfCarType[3];
			break;
		case etc :
			fee *= this.rateOfCarType[4];
			break;
		}
		
		int count = insurant.getAccidentHistory();
		if(count == 0) {
			fee *= this.rateOfAccidentHistory[0];
		} else if(count < 2) {
			fee *= this.rateOfAccidentHistory[1];
		} else if(count < 4) {
			fee *= this.rateOfAccidentHistory[2];
		} else if(count < 6) {
			fee *= this.rateOfAccidentHistory[3];
		} else if(count < 8) {
			fee *= this.rateOfAccidentHistory[4];
		} else {
			fee *= this.rateOfAccidentHistory[5];
		}

		switch (insurant.getRankOfCar()) {
		case Luxury:
			fee *= this.rateOfCarRank[0];
			break;
		case high:
			fee *= this.rateOfCarRank[1];
			break;
		case middle:
			fee *= this.rateOfCarRank[2];
			break;
		case low:
			fee *= this.rateOfCarRank[3];
			break;
		}
		
		return (int)fee;
	}

	public Insurance newInstance() {
		return new DriverInsurance();
	}
	
	public String writeToSelectedFile() {
		String output = this.getInsuranceId() + ' ';
		for (double rate : rateOfAccidentHistory) {
			output += rate;
			output += ' ';
		}
		for (double rate : rateOfCarType) {
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
		for (int i = 0; i < this.rateOfAccidentHistory.length; i++) {
			this.rateOfAccidentHistory[i] = scn.nextDouble();
		}
		for (int i = 0; i < this.rateOfCarType.length; i++) {
			this.rateOfCarType[i] = scn.nextDouble();
		}
		for (int i = 0; i < this.rateOfCarRank.length; i++) {
			this.rateOfCarRank[i] = scn.nextDouble();
		}
	}

}