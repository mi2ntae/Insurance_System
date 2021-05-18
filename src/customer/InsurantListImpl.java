package customer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import global.Constants.eGender;
import global.Constants.eJob;
import global.Constants.eTypeOfCar;
import global.Constants.eRiskOfTripCountry;
import global.Constants.eUsageOfStructure;

public class InsurantListImpl implements InsurantList {

	// Components
	private ArrayList<Insurant> insurantList;
	
	// Composition Class
	 private Insurant selectedInsurant;

	// Constructor
	public InsurantListImpl() throws FileNotFoundException {
		this.insurantList = new ArrayList<Insurant>();
		this.readFromFile();
	}
	
	// Getters & Setters
	public ArrayList<Insurant> getInsurantList() {return insurantList;}
	public void setInsurantList(ArrayList<Insurant> insurantList) {this.insurantList = insurantList;}
	
	public Insurant getSelectedInsurant() {return selectedInsurant;}
	public void setSelectedInsurant(Insurant selectedInsurant) {this.selectedInsurant = selectedInsurant;}

	// Public Methods
	public void insert(Insurant insurant) {
		this.insurantList.add(insurant);
		this.writeToFile(insurant);
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

	public boolean isEmpty() {
		return this.insurantList.isEmpty();
	}
	
	// Private Methods
	private void writeToFile(Insurant insurant) {
		File file = new File("data/insurant");
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
		    writer.append(insurant.writeToFile());
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
	
	private void readFromFile() throws FileNotFoundException {
		File file = new File("data/insurant");
		Scanner sc = new Scanner(file);
		while (sc.hasNext()) {
			Insurant insurant = new Insurant();
			insurant.readFromFile(sc);
			this.insurantList.add(insurant);
		}	
		
	}
}