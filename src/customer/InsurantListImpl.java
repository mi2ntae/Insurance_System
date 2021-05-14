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
	public void insert(Insurant insurant) {
		this.insurantList.add(insurant);
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