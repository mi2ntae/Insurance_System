package customer;

import java.util.ArrayList;

import global.Constants.eGender;
import global.Constants.eJob;
import global.Constants.eRankOfCar;
import global.Constants.eRiskOfTripCountry;
import global.Constants.eUsageOfStructure;

public class InsurantListImpl implements InsurantList {

	// Components
	private ArrayList<Insurant> insurantList;
	
	// Composition Class
	 private Insurant selectedInsurant;

	// Constructor
	public InsurantListImpl() {
		this.insurantList = new ArrayList<Insurant>();
	}
	
	// Getters & Setters
	public ArrayList<Insurant> getInsurantList() {return insurantList;}
	public void setInsurantList(ArrayList<Insurant> insurantList) {this.insurantList = insurantList;}
	
	public Insurant getSelectedInsurant() {return selectedInsurant;}
	public void setSelectedInsurant(Insurant selectedInsurant) {this.selectedInsurant = selectedInsurant;}

	// Methods
	@Override
	public void insert(int accidentHistory, String address, int age, String id, String name, String phoneNum,
			long postedPriceOfStructure, eUsageOfStructure usageOfStructure, eGender gender, eJob job,
			eRankOfCar rankOfCar, eRiskOfTripCountry riskOfTripCountry) {
		Insurant Insurant = new Insurant();
		Insurant.setAccidentHistory(accidentHistory);
		Insurant.setAddress(address);
		Insurant.setAge(age);
		Insurant.setInsurantId(id);
		Insurant.setName(name);
		Insurant.setPhoneNumber(phoneNum);
		Insurant.setPostedPriceOfStructure(postedPriceOfStructure);
		Insurant.setUsageOfStructure(usageOfStructure);
		Insurant.setGender(gender);
		Insurant.setJob(job);
		Insurant.setRankOfCar(rankOfCar);
		Insurant.setRiskOfTripCountry(riskOfTripCountry);
		this.insurantList.add(Insurant);
	}
	
	public Insurant select(String insurantId) {
		for (Insurant insurant : this.insurantList) {
			if (insurant.getInsurantId().equals(insurantId)) {
				this.selectedInsurant = insurant;
				return insurant;
			}
		}
		return null;
	}

	public boolean delete(String insurantId) {
		int index = this.getInsurantIndex(insurantId);
		if(index > -1) {
			this.insurantList.remove(index);
			return true;
		} else {
			return false;
		}
		
	}

	public void updateNameById(String insurantId, String data) {
		int index = this.getInsurantIndex(insurantId);
		if(index > -1) {
			this.insurantList.get(index).setName(data);
		}
	}


	private int getInsurantIndex(String insurantId) {
		for (int i = 0; i < this.insurantList.size(); i++) {
			if (this.insurantList.get(i).getInsurantId() == insurantId) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public boolean isEmpty() {
		return this.insurantList.isEmpty();
	}
}