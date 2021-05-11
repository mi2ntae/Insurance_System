package customer;

public interface CustomerList {
	
	// Methods
	public boolean insert(Customer Customer);
	public Customer select(String CustomerId);
	public boolean delete(String CustomerId);
	public void updateNameById(String CustomerId, String data);
	public boolean checkDuplication(String input);
}