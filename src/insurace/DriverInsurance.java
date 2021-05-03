package insurace;

public class DriverInsurance extends Insurance {

	// Attributes
	private float[] rateOfAccident;
	private float[] rateOfCarRank;

	// Constructor
	public DriverInsurance(){

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

	// Methods
	public int calculateFee(int insurantId){
		return 0;
	}

}