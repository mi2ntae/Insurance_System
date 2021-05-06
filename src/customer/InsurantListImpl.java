package customer;

import java.util.ArrayList;

public class InsurantListImpl implements InsurantList {

	// Components
	private ArrayList<Insurant> insurantList;
	
	// Composition Class
	 private Insurant insurant;

	// Constructor
	public InsurantListImpl() {
		this.insurantList = new ArrayList<Insurant>();
	}
	
	// getters & setters
	public ArrayList<Insurant> getInsurantList() {return insurantList;}
	public void setInsurantList(ArrayList<Insurant> insurantList) {this.insurantList = insurantList;}
	
	// Methods
	public boolean insert(Insurant insurant) {
		if (this.insurantList.add(insurant)) {
			return true;
		} else {
			return false;
		}
	}

	public Insurant select(String insurantId) {
		for (Insurant insurant : this.insurantList) {
			if (insurant.getInsurantId().equals(insurantId)) {
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

}