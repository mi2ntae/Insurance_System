package contract;

public interface AccidentList {
	// public Methods
	public boolean insert(Accident accident);
	public Accident search(String accidentId);
	public boolean delete(String accidentId);
	public void updateContentById(String accidentId, String content);
}