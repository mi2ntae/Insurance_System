package insurance;

import java.util.Scanner;

import customer.Insurant;
import global.Constants.eGender;

public class DentalInsurance extends Insurance {

	// Attributes
	private int annualLimitCount;
	private int useCount;

	// Constructor
	public DentalInsurance(){

	}

	// getters & setters
	public int getAnnualLimitCount() {return annualLimitCount;}
	public void setAnnualLimitCount(int annualLimitCount) {	this.annualLimitCount = annualLimitCount;}
	
	public int getUseCount() {return useCount;}
	public void setUseCount(int useCount) {this.useCount = useCount;}

	// Methods
	public int calculateFee(Insurant insurant){
		if (annualLimitCount > useCount) {
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
			return (int) fee;
		} else {
			return -1;
		}
	}

	public Insurance newInstance() {
		return new DentalInsurance();
	}
	
	public String writeToSelectedFile() {
		String output = this.getInsuranceId() + ' ' + this.getAnnualLimitCount() + ' ' + this.getUseCount() + '\n';
		return output;
	}
	
	public void readFromSelectedFile(Scanner scn) {
		this.annualLimitCount = scn.nextInt();
		this.useCount = scn.nextInt();
	}

}