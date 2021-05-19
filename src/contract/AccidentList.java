package contract;

import java.util.ArrayList;

public interface AccidentList {
	// getters & setters
	public ArrayList<Accident> getAccidentList();
	public void setAccidentList(ArrayList<Accident> accidentList);
	// public Methods
	public boolean insert(Accident accident);
	public Accident search(String accidentId);
	public boolean delete(String accidentId);
	public void updateContentById(String accidentId, String content);
}