package contract;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class AccidentListImpl implements AccidentList {
	// Components
	private ArrayList<Accident> accidentList;
	
	// Constructor
	public AccidentListImpl() {
		this.accidentList = new ArrayList<Accident>();
	}

	// Getters&Setters
	public ArrayList<Accident> getAccidentList() {return accidentList;}
	public void setAccidentList(ArrayList<Accident> accidentList) {this.accidentList = accidentList;}

	// public Method
	public boolean insert(Accident accident) {
		if (this.accidentList.add(accident)) {
			writeToFile(accident);
			return true;
		} else {
			return false;
		}
	}
	
	public Accident select(String accidentId) {
		for (Accident accident : this.accidentList) {
			if (accident.getAccidentId().equals(accidentId)) {
				return accident;
			}
		}
		return null;
	}

	public boolean delete(String accidentId) {
		int deleteIndex = getAccidentIndex(accidentId);
		if(deleteIndex != -1) {
			this.accidentList.remove(deleteIndex);
			return true;
		} else {
			return false;
		}
		
	}

	public void updateContentById(String accidentId, String content) {
		int updateIndex = getAccidentIndex(accidentId);
		if(updateIndex != -1) {
			this.accidentList.get(updateIndex).setContent(content);
		}
	}

	public void readFromFile(String contractId) throws FileNotFoundException {
		File file = new File("data/accident");
		Scanner scn = new Scanner(file);
		while (scn.hasNext()) {
			String input = scn.next();
			if (input.equals(contractId)) {
				Accident accident = new Accident();
				accident.readFromFile(scn, input);
				this.accidentList.add(accident);
			}
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
	
	private void writeToFile(Accident accident) {
		File file = new File("data/accident");
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
			writer.append(accident.writeToFile());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
}