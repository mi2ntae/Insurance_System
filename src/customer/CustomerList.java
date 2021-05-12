package customer;

public interface CustomerList {
	
	// Methods
	public boolean insert(Customer customer);
	public Customer select(String customerId);
	public boolean delete(String customerId);
	public void updateNameById(String customerId, String data);
	public boolean checkDuplication(String input);
}