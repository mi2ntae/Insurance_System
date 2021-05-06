package customer;

public interface InsurantList {
	
	// Methods
	public boolean insert(Insurant insurant);
	public Insurant select(String insurantId);
	public boolean delete(String insurantId);
	public void updateNameById(String insurantId, String data);
}