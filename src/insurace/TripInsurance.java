package insurace;

public class TripInsurance extends Insurance {

	// Attributes
	private float[] rateOfCountryRank;

	// Constructor
	public TripInsurance(){

	}
	
	// getters & setters
	public float[] getRateOfCountryRank() {return rateOfCountryRank;}
	public void setRateOfCountryRank(float[] rateOfCountryRank) {this.rateOfCountryRank = rateOfCountryRank;}

	public void finalize() throws Throwable {
		super.finalize();
	}

	// Methods
	public int calculateFee(int insurantId){
		return 0;
	}

}