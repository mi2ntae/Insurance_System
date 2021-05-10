package insurance;

import java.util.ArrayList;

public class InsuranceListImpl implements InsuranceList {

	// Components
	private ArrayList<Insurance> insuranceList;
	
	// Composition Class
	 private Insurance m_Insurance;

	// Constructor
	public InsuranceListImpl() {
		this.insuranceList = new ArrayList<Insurance>();
	}
	
	// getters & setters
	public ArrayList<Insurance> getInsuranceList() {return insuranceList;}
	public void setInsuranceList(ArrayList<Insurance> insuranceList) {this.insuranceList = insuranceList;}

	public void finalize() throws Throwable {

	}

	// Methods
	public boolean insert(Insurance insurance) {
		if (this.insuranceList.add(insurance)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean delete(String insuranceId) {
		int index = this.getInsuranceIndex(insuranceId);
		if(index >= 0) {
			this.insuranceList.remove(index);
			return true;
		} else {
			return false;
		}
		
	}

	public Insurance select(String insuranceId) {
		for (Insurance insurance : this.insuranceList) {
			if (insurance.getInsuranceId().equals(insuranceId)) {
				return insurance;
			}
		}
		return null;
	}

	public void updateNameById(String insuranceId, String data) {
		int index = this.getInsuranceIndex(insuranceId);
		if(index > -1) {
			this.insuranceList.get(index).setName(data);
		}
	}

	private int getInsuranceIndex(String insuranceId) {
		for (int i = 0; i < this.insuranceList.size(); i++) {
			if (this.insuranceList.get(i).getInsuranceId() == insuranceId) {
				return i;
			}
		}
		return -1;
	}

}