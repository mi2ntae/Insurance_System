package customer;

import java.util.ArrayList;

public class CustomerListImpl implements CustomerList {

	// Components -> 교수님에게 질문
	private ArrayList<Customer> CustomerList;
	
	// Composition Class
	 private Customer m_Customer;

	// Constructor
	public CustomerListImpl() {
		this.CustomerList = new ArrayList<Customer>();
	}
	
	// getters & setters
	public ArrayList<Customer> getCustomerList() {return CustomerList;}
	public void setCustomerList(ArrayList<Customer> customerList) {CustomerList = customerList;}
	
	// Methods
	public boolean insert(Customer customer) {
		if (this.CustomerList.add(customer)) {
			return true;
		} else {
			return false;
		}
	}

	public Customer select(String customerId) {
		for (Customer customer : this.CustomerList) {
			if (customer.getCustomerId().equals(customerId)) {
				return customer;
			}
		}
		return null;
	}

	public boolean delete(String customerId) {
		int index = this.getCustomerIndex(customerId);
		if(index > -1) {
			this.CustomerList.remove(index);
			return true;
		} else {
			return false;
		}
		
	}

	public void updateNameById(String customerId, String data) {
		int index = this.getCustomerIndex(customerId);
		if(index > -1) {
			this.CustomerList.get(index).setName(data);
		}
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