package insurance;

public class GuaranteePlan {
	// Attributes
	private String insuranceId;
	private boolean special;
	private String content;
	private int compensation;
	private double rate;

	// Constructor
	public GuaranteePlan(){

	}

	// getters & setters
	public String getInsuranceId() {return insuranceId;}
	public void setInsuranceId(String insuranceId) {this.insuranceId = insuranceId;}

	public boolean isSpecial() {return special;}
	public void setSpecial(boolean special) {this.special = special;}

	public String getContent() {return content;}
	public void setContent(String content) {this.content = content;}

	public int getCompensation() {return compensation;}
	public void setCompensation(int compensation) {this.compensation = compensation;}

	public double getRate() {return rate;}
	public void setRate(double rate) {this.rate = rate;}
}