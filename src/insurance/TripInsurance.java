package insurance;

import java.util.Scanner;

import customer.Insurant;
import global.Constants.eGender;

public class TripInsurance extends Insurance {
	// Attributes
	private double[] rateOfCountryRisk = {1.0, 1.3, 1.5, 2.5}; // index[0: 안전 / 1: 1단계 / 1: 2단계 / 2: 3단계] 

	// Constructor
	public TripInsurance(){

	}
	
	// getters & setters
	public double[] getRateOfCountryRank() {return rateOfCountryRisk;}
	public void setRateOfCountryRank(double[] rateOfCountryRank) {this.rateOfCountryRisk = rateOfCountryRank;}

	// Methods
	public int calculateFee(Insurant insurant){
		double fee = this.getBasicFee();

		// 나이에 따른 요율 계산
		if (insurant.getAge() >= 10 && insurant.getAge() < 20) {
			fee *= this.getRateOfAge()[0];
		} else if (insurant.getAge() >= 20 && insurant.getAge() < 30) {
			fee *= this.getRateOfAge()[1];
		} else if (insurant.getAge() >= 30 && insurant.getAge() < 40) {
			fee *= this.getRateOfAge()[2];
		} else if (insurant.getAge() >= 40 && insurant.getAge() < 50) {
			fee *= this.getRateOfAge()[3];
		} else if (insurant.getAge() >= 50 && insurant.getAge() < 60) {
			fee *= this.getRateOfAge()[4];
		} else if (insurant.getAge() >= 60 && insurant.getAge() < 70) {
			fee *= this.getRateOfAge()[5];
		} else {
			fee *= this.getRateOfAge()[6];
		}

		// 성별에 따른 요율 계산
		if (insurant.getGender() == eGender.male) {
			fee *= this.getRateOfGender()[0];
		} else if (insurant.getGender() == eGender.female) {
			fee *= this.getRateOfGender()[1];
		}

		// 국가 위험도에 따른 요율 계산
		switch(insurant.getRiskOfTripCountry()) {
		case safe:
			fee *= this.getRateOfCountryRank()[0];
			break;
		case first:
			fee *= this.getRateOfCountryRank()[1];
			break;
		case second:
			fee *= this.getRateOfCountryRank()[2];
			break;
		case third:
			fee *= this.getRateOfCountryRank()[3];
			break;
		default:
			break;
		}
		
		return (int) fee;
	}

	public Insurance newInstance() {
		return new TripInsurance();
	}
	
	public String writeToSelectedFile() {
		String output = this.getInsuranceId() + ' ';
		for (double rate : rateOfCountryRisk) {
			output += rate;
			output += ' ';
		}
		output += '\n';
		return output;
	}
	
	public void readFromSelectedFile(Scanner scn) {
		for (int i = 0; i < this.rateOfCountryRisk.length; i++) {
			this.rateOfCountryRisk[i] = scn.nextDouble();
		}
	}

}