package insurace;

public interface InsuranceList {
	
	// Methods
	public boolean insert(Insurance insurance);
	public Insurance select(String insuranceId);
	public boolean delete(String insuranceId);
	public void updateNameById(String insuranceId, String data);
}