package customer;

import java.util.ArrayList;

public interface CustomerDAO {
	public boolean insert(Customer customer);
	public ArrayList<Customer> select();
	public boolean update(Customer customer);
	public boolean delete(String customerId);
}
