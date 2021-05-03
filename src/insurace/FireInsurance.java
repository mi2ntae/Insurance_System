package insurace;

public class FireInsurance extends Insurance {

	// Attributes
	private float[] rateOfPostedPrice;
	private float[] rateOfStructureUsage;

	// Constructor
	public FireInsurance(){

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

	// Methods
	public int calculateFee(int insurantId){
		return 0;
	}

}