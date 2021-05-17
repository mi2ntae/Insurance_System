package insurance;

import java.util.Scanner;

import customer.Insurant;
import global.Constants.eGender;

public class CancerInsurance extends Insurance {
	// Attributes
	private double[] rateOfFamilyMedicalDisease = {1.6, 1.5, 1.4, 1.3, 1.2}; // index[0: thyroid/ 1: testicular/ 2: ovarian/ 3: esophageal/ 4: lung]
	private double[] rateOfFamilyMedicalRelationship = {1.4, 1.3, 1.2, 1.1}; // index[0: 1촌/ 1: 2촌/ 2: 3촌/ 3: 4촌]
		
	// Constructor
	public CancerInsurance() {
		
	}
	
	// getters & setters
	public double[] getRateOfFamilyMedicalDisease() {return rateOfFamilyMedicalDisease;}
	public void setRateOfFamilyMedicalDisease(double[] rateOfFamilyMedicalDisease) {this.rateOfFamilyMedicalDisease = rateOfFamilyMedicalDisease;}

	public double[] getRateOfFamilyMedicalRelationship() {return rateOfFamilyMedicalRelationship;}
	public void setRateOfFamilyMedicalRelationship(double[] rateOfFamilyMedicalRelationship) {this.rateOfFamilyMedicalRelationship = rateOfFamilyMedicalRelationship;}

	// public Methods
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
		} else if (insurant.getGender() == eGender.female){
			fee *= this.getRateOfGender()[1];
		}
		
		// 직업에 따른 요율 계산
		switch (insurant.getJob()) {
		case officeWorker:
			fee *= this.getRateOfJob()[0];
			break;
		case driver:
			fee *= this.getRateOfJob()[1];
			break;
		case factoryWorker:
			fee *= this.getRateOfJob()[2];
			break;
		case student:
			fee *= this.getRateOfJob()[3];
			break;
		case teacher:
			fee *= this.getRateOfJob()[4];
			break;
		case soldier:
			fee *= this.getRateOfJob()[5];
			break;
		case etc:
			fee *= this.getRateOfJob()[6];
			break;
		default:
			break;
		}
		
		// 가족병력에 따른 요율 계산
		switch (insurant.getFamilyMedicalHistory().getRelationship()) {
		case 1:
			fee *= rateOfFamilyMedicalRelationship[0];
			break;
		case 2:
			fee *= rateOfFamilyMedicalRelationship[1];
			break;
		case 3:
			fee *= rateOfFamilyMedicalRelationship[2];
			break;
		case 4:
			fee *= rateOfFamilyMedicalRelationship[3];
			break;
		}
		
		switch (insurant.getFamilyMedicalHistory().getDisease()) {
		case thyroid:
			fee *= rateOfFamilyMedicalDisease[0];
			break;
		case esophageal:
			fee *= rateOfFamilyMedicalDisease[1];
			break;
		case lung:
			fee *= rateOfFamilyMedicalDisease[2];
			break;
		case ovarian:
			fee *= rateOfFamilyMedicalDisease[3];
			break;
		case testicular:
			fee *= rateOfFamilyMedicalDisease[4];
			break;
		}
		
		return (int)fee;
	}
	
	public Insurance newInstance() {
		return new CancerInsurance();
	}
	
	public String writeToSelectedFile() {
		String output = this.getInsuranceId() + ' ';
		for (double rate : rateOfFamilyMedicalDisease) {
			output += rate;
			output += ' ';
		}
		for (double rate : rateOfFamilyMedicalRelationship) {
			output += rate;
			output += ' ';
		}
		output += '\n';
		return output;
	}

	public void readFromSelectedFile(Scanner scn) {
		for (int i = 0; i < this.rateOfFamilyMedicalDisease.length; i++) {
			this.rateOfFamilyMedicalDisease[i] = scn.nextDouble();
		}
		for (int i = 0; i < this.rateOfFamilyMedicalRelationship.length; i++) {
			this.rateOfFamilyMedicalRelationship[i] = scn.nextDouble();
		}
	}

}