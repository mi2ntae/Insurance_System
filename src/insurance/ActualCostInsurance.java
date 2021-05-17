package insurance;

import java.util.Scanner;

import customer.Insurant;
import global.Constants.eGender;

public class ActualCostInsurance extends Insurance {

	// Attributes
	private double selfBurdenRate;

	// Constructor
	public ActualCostInsurance(){

	}

	// getters & setters
	public double getSelfBurdenRate() {return selfBurdenRate;}
	public void setSelfBurdenRate(double selfBurdenRate) {this.selfBurdenRate = selfBurdenRate;}

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
		
		return (int)fee;
	}
	
	public Insurance newInstance() {
		return new ActualCostInsurance();
	}
	
	public String writeToSelectedFile() {
		String output = this.getInsuranceId() + ' ' + this.selfBurdenRate + '\n';
		return output;
	}

	public void readFromSelectedFile(Scanner scn) {
		this.selfBurdenRate = scn.nextDouble();
	}
}