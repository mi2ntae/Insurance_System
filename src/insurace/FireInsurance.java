package insurace;

import customer.Insurant;
import global.Constants.eInsurantGender;

public class FireInsurance extends Insurance {

	// Attributes
	private double[] rateOfPostedPrice;
	private double[] rateOfStructureUsage; // index[0: 주택/ 1: 교육/ 2: 공장/ 3: 창고/ 4: 사무/ 5: 공공시설]

	// Constructor
	public FireInsurance(){

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

	// Methods
	public int calculateFee(int insurantId){
		double fee = this.getBasicFee();
		Insurant insurant = new Insurant();

		// 재산 규모에 따른 요율 계산
		
		
		return 0;
	}

}