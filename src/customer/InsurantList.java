package customer;

import java.util.ArrayList;

import global.Constants.eGender;
import global.Constants.eJob;
import global.Constants.eRankOfCar;
import global.Constants.eRiskOfTripCountry;
import global.Constants.eUsageOfStructure;

public interface InsurantList {
	
	// Methods
	public void insert(int accidentHistory, String address, int age, String id, String name, String phoneNum,
			long postedPriceOfStructure, eUsageOfStructure usageOfStructure, eGender gender, eJob job,
			eRankOfCar rankOfCar, eRiskOfTripCountry riskOfTripCountry);
	public Insurant select(String insurantId);
	public boolean delete(String insurantId);
	public void updateNameById(String insurantId, String data);
	public boolean isEmpty();
	
	// Getter & Setter
	public ArrayList<Insurant> getInsurantList();
	public void setInsurantList(ArrayList<Insurant> insurantList);
	
	public Insurant getSelectedInsurant();
	public void setSelectedInsurant(Insurant selectedInsurant);
}