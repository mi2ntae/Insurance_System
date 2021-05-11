package insurance;

import java.util.ArrayList;

public interface InsuranceList {
	// getters&setters
	public ArrayList<Insurance> getInsuranceList();
	public void setInsuranceList(ArrayList<Insurance> insuranceList);
	// Methods
	public boolean insert(Insurance insurance);
	public Insurance select(String insuranceId);
	public boolean delete(String insuranceId);
	public void updateNameById(String insuranceId, String data);
}