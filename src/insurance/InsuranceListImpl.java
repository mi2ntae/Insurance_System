package insurance;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import employee.Employee;
import global.Constants.eInsuranceType;

public class InsuranceListImpl implements InsuranceList {

	// Components
	private ArrayList<Insurance> insuranceList;
	
	// Composition Class

	// Constructor
	public InsuranceListImpl() throws FileNotFoundException {
		this.insuranceList = new ArrayList<Insurance>();
		this.readFromFile();
	}
	
	// getters & setters
	public ArrayList<Insurance> getInsuranceList() {return insuranceList;}
	public void setInsuranceList(ArrayList<Insurance> insuranceList) {this.insuranceList = insuranceList;}

	// Public Methods
	public boolean insert(Insurance insurance) {
		if (this.insuranceList.add(insurance)) {
			this.writeToFile(insurance);
			this.writeToSelectedFile(insurance);
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

	// Private Methods
	private int getInsuranceIndex(String insuranceId) {
		for (int i = 0; i < this.insuranceList.size(); i++) {
			if (this.insuranceList.get(i).getInsuranceId() == insuranceId) {
				return i;
			}
		}
		return -1;
	}
	
	private void writeToFile(Insurance insurance) {
		File file = new File("data/insurance");
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
		    writer.append(insurance.writeToFile());
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
	
	private void writeToSelectedFile(Insurance insurance) {
		File file = new File("data/" + insurance.getType().getName());
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
		    writer.append(insurance.writeToSelectedFile());
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
	
	private void readFromFile() throws FileNotFoundException {
		File file = new File("data/insurance");
		Scanner scn = new Scanner(file);
		int i = 0;
		while (scn.hasNext()) {
			int input = scn.nextInt();
			for (eInsuranceType insuranceType : eInsuranceType.values()) {
				if (insuranceType.getNum() == input) {
					Insurance insurance = insuranceType.getSelectedInsurance().newInstance();
					insurance.readFromFile(scn, input);
					insurance = this.readFromSelectedFile(insurance);
					this.insuranceList.add(insurance);
				}
			}
		}	
	}
	
	private Insurance readFromSelectedFile(Insurance insurance) throws FileNotFoundException {
		File file = new File("data/" + insurance.getType().getName());
		Scanner scn = new Scanner(file);
		while (scn.hasNext()) {
			if (scn.next().equals(insurance.getInsuranceId())) {
				insurance.readFromSelectedFile(scn);
				return insurance;
			}
		}
		return null;
	}
}