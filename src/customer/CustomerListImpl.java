package customer;

import java.util.ArrayList;

public class CustomerListImpl implements CustomerList {

	// Components -> 교수님에게 질문
	private ArrayList<Customer> customerList;
	
	// Composition Class
	 private Customer customer;

	// Constructor
	public CustomerListImpl() {
		this.customerList = new ArrayList<Customer>();
	}
	
	// Getters & Setters
	public ArrayList<Customer> getCustomerList() {return customerList;}
	public void setCustomerList(ArrayList<Customer> customerList) {this.customerList = customerList;}
	
	// Public Methods
	public boolean insert(Customer customer) {
		if (this.customerList.add(customer)) {
			return true;
		} else {
			return false;
		}
	}

	public Customer select(String customerId) {
		for (Customer customer : this.customerList) {
			if (customer.getCustomerId().equals(customerId)) {
				return customer;
			}
		}
		return null;
	}

	public boolean delete(String customerId) {
		int index = this.getCustomerIndex(customerId);
		if(index > -1) {
			this.customerList.remove(index);
			return true;
		} else {
			return false;
		}
		
	}

	public void updateNameById(String customerId, String data) {
		int index = this.getCustomerIndex(customerId);
		if(index > -1) {
			this.customerList.get(index).setName(data);
		}
	}
	
	public boolean checkDuplication(String input) {
		for(Customer customer: customerList) {
			if(customer.getCustomerId().equals(input)) {
				return false;
			}
		}
		return true;
	}

	// Private Methods
	private int getCustomerIndex(String customerId) {
		for (int i = 0; i < this.customerList.size(); i++) {
			if (this.customerList.get(i).getCustomerId() == customerId) {
				return i;
			}
		}
		return -1;
	}
	
}