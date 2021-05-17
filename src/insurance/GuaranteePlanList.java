package insurance;

import java.util.ArrayList;

public interface GuaranteePlanList {
	// getters&setters
	public ArrayList<GuaranteePlan> getGuaranteePlanList();
	public void setGuaranteePlanList(ArrayList<GuaranteePlan> insuranceList);
	// Methods
	public boolean insert(GuaranteePlan guaranteePlan);
	public GuaranteePlan select(String insuranceId);
	public boolean delete(String insuranceId);
	public void updateNameById(String guaranteePlan, String data);
}