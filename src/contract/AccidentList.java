package contract;

public interface AccidentList {
	// public Methods
	public boolean add(Accident accident);
	public Accident search(String accidentId);
	public boolean remove(String accidentId);
	public void updateContentById(String accidentId, String name);
}