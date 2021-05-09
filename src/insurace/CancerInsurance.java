package insurace;

import customer.Insurant;
import global.Constants.eInsurantGender;

public class CancerInsurance extends Insurance {
	// Attributes
	private double[] rateOfFamilyMedicaldisease = {1.6, 1.5, 1.4, 1.3, 1.2};
	private double[] rateOfFamilyMedicalRelationship = {1.4, 1.3, 1.2, 1.1};
		
	// Constructor
	public CancerInsurance(){
		
	}
	
	// getters & setters
	public double[] getRateOfFamilyMedicaldisease() {return rateOfFamilyMedicaldisease;}
	public void setRateOfFamilyMedicaldisease(double[] rateOfFamilyMedicaldisease) {this.rateOfFamilyMedicaldisease = rateOfFamilyMedicaldisease;}

	public double[] getRateOfFamilyMedicalRelationship() {return rateOfFamilyMedicalRelationship;}
	public void setRateOfFamilyMedicalRelationship(double[] rateOfFamilyMedicalRelationship) {this.rateOfFamilyMedicalRelationship = rateOfFamilyMedicalRelationship;}

	// public Methods
	public int calculateFee(int insurantId){
		double fee = this.getBasicFee();
		Insurant insurant = new Insurant();

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
		if (insurant.getGender() == eInsurantGender.male) {
			fee *= this.getRateOfGender()[0];
		} else if (insurant.getGender() == eInsurantGender.female){
			fee *= this.getRateOfGender()[1];
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
			fee *= rateOfFamilyMedicaldisease[0];
			break;
		case esophageal:
			fee *= rateOfFamilyMedicaldisease[1];
			break;
		case lung:
			fee *= rateOfFamilyMedicaldisease[2];
			break;
		case ovarian:
			fee *= rateOfFamilyMedicaldisease[3];
			break;
		case testicular:
			fee *= rateOfFamilyMedicaldisease[4];
			break;
		}
		
		return (int)fee;
	}

}