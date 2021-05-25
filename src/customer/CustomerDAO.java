package customer;

import java.util.ArrayList;

public interface CustomerDAO {
	public boolean insert(Customer customer);
	public ArrayList<Customer> select();
}
