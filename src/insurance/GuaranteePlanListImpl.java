package insurance;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class GuaranteePlanListImpl implements GuaranteePlanList {

	// Components
	private ArrayList<GuaranteePlan> guaranteePlanList;
	
	// Composition Class

	// Constructor
	public GuaranteePlanListImpl() {
		this.guaranteePlanList = new ArrayList<GuaranteePlan>();
	}
	
	// getters & setters
	public ArrayList<GuaranteePlan> getGuaranteePlanList() {return guaranteePlanList;}
	public void setGuaranteePlanList(ArrayList<GuaranteePlan> guaranteePlanList) {this.guaranteePlanList = guaranteePlanList;}

	// Public Methods
	public boolean insert(GuaranteePlan guaranteePlan) {
		if (this.guaranteePlanList.add(guaranteePlan)) {
			this.writeToFile(guaranteePlan);
			return true;
		} else {
			return false;
		}
	}

	public boolean delete(String insuranceId) {
		int index = this.getInsuranceIndex(insuranceId);
		if(index >= 0) {
			this.guaranteePlanList.remove(index);
			return true;
		} else {
			return false;
		}
		
	}

	public GuaranteePlan select(String insuranceId) {
		for (GuaranteePlan guaranteePlan : this.guaranteePlanList) {
			if (guaranteePlan.getInsuranceId().equals(insuranceId)) {
				return guaranteePlan;
			}
		}
		return null;
	}

	public void updateNameById(String insuranceId, String data) {
		int index = this.getInsuranceIndex(insuranceId);
	}

	public void readFromFile(String insuranceId) throws FileNotFoundException {
		File file = new File("data/guaranteePlan");
		Scanner scn = new Scanner(file);
		while (scn.hasNext()) {
			String input = scn.next();
			if (input.equals(insuranceId)) {
				GuaranteePlan guaranteePlan = new GuaranteePlan();
				guaranteePlan.readFromFile(scn, input);
				this.guaranteePlanList.add(guaranteePlan);
			}
		}
	}
	
	// Private Methods
	private int getInsuranceIndex(String insuranceId) {
		for (int i = 0; i < this.guaranteePlanList.size(); i++) {
			if (this.guaranteePlanList.get(i).getInsuranceId() == insuranceId) {
				return i;
			}
		}
		return -1;
	}
	
	private void writeToFile(GuaranteePlan guaranteePlan) {
		File file = new File("data/guaranteePlan");
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
		    writer.append(guaranteePlan.writeToFile());
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}

}