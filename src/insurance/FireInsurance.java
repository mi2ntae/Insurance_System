package insurance;

import java.util.Scanner;

import customer.Insurant;

public class FireInsurance extends Insurance {

	// Attributes
	private double[] rateOfPostedPrice = {0.9, 1.0, 1.1, 1.2, 1.3}; // index[0: ~5천만원/ 1: ~5억/ 2: ~10억/ 3: ~20억/ 4: 그 이상]
	private double[] rateOfStructureUsage = {1.0, 0.9, 1.2, 1.2, 1.1, 1.1}; // index[0: 주택/ 1: 교육/ 2: 공장/ 3: 창고/ 4: 사무/ 5: 공공시설]

	// Constructor
	public FireInsurance(){
	}

	// getters & setters
	public double[] getRateOfPostedPrice() {return rateOfPostedPrice;}
	public void setRateOfPostedPrice(double[] rateOfPostedPrice) {this.rateOfPostedPrice = rateOfPostedPrice;}

	public double[] getRateOfStructureUsage() {return rateOfStructureUsage;}
	public void setRateOfStructureUsage(double[] rateOfStructureUsage) {this.rateOfStructureUsage = rateOfStructureUsage;}
		
	// Methods
	public int calculateFee(Insurant insurant){
		double fee = this.getBasicFee();

		// 재산 규모에 따른 요율 계산
		if (insurant.getPostedPriceOfStructure() > 0 && insurant.getPostedPriceOfStructure() <= 50000000) {
			fee *= this.getRateOfPostedPrice()[0];
		} else if (insurant.getPostedPriceOfStructure() > 50000000 && insurant.getPostedPriceOfStructure() <= 500000000) {
			fee *= this.getRateOfPostedPrice()[1];
		} else if (insurant.getPostedPriceOfStructure() > 500000000 && insurant.getPostedPriceOfStructure() <= 1000000000) {
			fee *= this.getRateOfPostedPrice()[2];
		} else if (insurant.getPostedPriceOfStructure() > 1000000000 && insurant.getPostedPriceOfStructure() <= 2000000000) {
			fee *= this.getRateOfPostedPrice()[3];
		} else if (insurant.getPostedPriceOfStructure() > 2000000000) {
			fee *= this.getRateOfPostedPrice()[4];
		} else {
			System.out.println("error : input이 숫자가 아님");
		}
		
		// 재산의 사용 용도에 따른 요율 계산
		switch(insurant.getUsageOfStructure()) {
		case house:
			fee *= this.getRateOfStructureUsage()[0];
			break;
		case study:
			fee *= this.getRateOfStructureUsage()[1];
			break;
		case factory:
			fee *= this.getRateOfStructureUsage()[2];
			break;
		case warehouse:
			fee *= this.getRateOfStructureUsage()[3];
			break;
		case office:
			fee *= this.getRateOfStructureUsage()[4];
			break;
		case publicFacility:
			fee *= this.getRateOfStructureUsage()[5];
			break;
		default:
			break;
		}
		
		return (int) fee;
	}

	public Insurance newInstance() {
		return new FireInsurance();
	}
	
	public String writeToSelectedFile() {
		String output = this.getInsuranceId() + ' ';
		for (double rate : rateOfPostedPrice) {
			output += rate;
			output += ' ';
		}
		for (double rate : rateOfStructureUsage) {
			output += rate;
			output += ' ';
		}
		output += '\n';
		return output;
	}

	public void readFromSelectedFile(Scanner scn) {
		for (int i = 0; i < this.rateOfPostedPrice.length; i++) {
			this.rateOfPostedPrice[i] = scn.nextDouble();
		}
		for (int i = 0; i < this.rateOfStructureUsage.length; i++) {
			this.rateOfStructureUsage[i] = scn.nextDouble();
		}
	}

}