package customer;

import java.util.ArrayList;

public class CustomerListImpl implements CustomerList {

	// Components
	private ArrayList<Customer> CustomerList;
	
	// Composition Class
	 private Customer m_Customer;

	// Constructor
	public CustomerListImpl() {
		this.CustomerList = new ArrayList<Customer>();
	}

	public void finalize() throws Throwable {

	}

	public boolean insert(Customer Customer) {
		if (this.CustomerList.add(Customer)) {
			return true;
		} else {
			return false;
		}
	}

	public Customer select(String CustomerId) {
		for (Customer customer : this.CustomerList) {
			if (customer.getCustomerId().equals(CustomerId)) {
				return customer;
			}
		}
		return null;
	}

	public boolean delete(String CustomerId) {
		int index = this.getCustomerIndex(CustomerId);
		if(index > -1) {
			this.CustomerList.remove(index);
			return true;
		} else {
			return false;
		}
		
	}

	public void updateNameById(String CustomerId, String data) {
		int index = this.getCustomerIndex(CustomerId);
		if(index > -1) {
			this.CustomerList.get(index).setName(data);
		}
	}

	public ArrayList<Customer> getCustomerList() {
		return CustomerList;
	}

	public void setCustomerList(ArrayList<Customer> customerList) {
		CustomerList = customerList;
	}

	private int getCustomerIndex(String CustomerId) {
		for (int i = 0; i < this.CustomerList.size(); i++) {
			if (this.CustomerList.get(i).getCustomerId() == CustomerId) {
				return i;
			}
		}
		return -1;
	}

}