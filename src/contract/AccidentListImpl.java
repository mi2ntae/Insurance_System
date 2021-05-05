package contract;

import java.util.ArrayList;

public class AccidentListImpl implements AccidentList {
	// Components
	private ArrayList<Accident> accidentList;
	
	// Constructor
	public AccidentListImpl() {
		this.accidentList = new ArrayList<Accident>();
	}

	// Gettes&Setters
	public ArrayList<Accident> getAccidentList() {return accidentList;}
	public void setAccidentList(ArrayList<Accident> accidentList) {this.accidentList = accidentList;}

	// public Method
	public boolean add(Accident accident) {
		if (this.accidentList.add(accident)) {
			return true;
		} else {
			return false;
		}
	}

	public Accident search(String accidentId) {
		for (Accident accident : this.accidentList) {
			if (accident.getAccidentId().equals(accidentId)) {
				return accident;
			}
		}
		return null;
	}

	public boolean remove(String accidentId) {
		int deleteIndex = getAccidentIndex(accidentId);
		if(deleteIndex != -1) {
			this.accidentList.remove(deleteIndex);
			return true;
		} else {
			return false;
		}
		
	}

	public void updateContentById(String accidentId, String name) {
		int updateIndex = getAccidentIndex(accidentId);
		if(updateIndex != -1) {
			this.accidentList.get(updateIndex).setContent(name);
		}
	}
	
	// private Method
	private int getAccidentIndex(String accidentId) {
		for (int i = 0; i < this.accidentList.size(); i++) {
			if (this.accidentList.get(i).getAccidentId().equals(accidentId)) {
				return i;
			}
		}
		return -1;
	}
	
}