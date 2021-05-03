package insurace;

public class ActualCostInsurance extends Insurance {

	// Attributes
	private float[] rateOfFamilyMedicalHistory;
	private float selfBurdenRate;

	// Constructor
	public ActualCostInsurance(){

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

	// Methods
	public int calculateFee(int insurantId){
		return 0;
	}

}