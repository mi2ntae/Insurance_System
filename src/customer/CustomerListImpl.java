package customer;

import java.util.ArrayList;

public class CustomerListImpl implements CustomerList {

	// Components -> 교수님에게 질문
	private ArrayList<Customer> customerList;
	
	// Composition Class
	 private Customer m_Customer;

	// Constructor
	public CustomerListImpl() {
		this.customerList = new ArrayList<Customer>();
	}
	
	// getters & setters
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
				System.out.println("이미 존재하는 ID입니다!(사용불가)");
				System.out.println("--------------------------");
				return false;
			}
		}
		return true;
	}

	// Private Methods
	private int getCustomerIndex(String CustomerId) {
		for (int i = 0; i < this.customerList.size(); i++) {
			if (this.customerList.get(i).getCustomerId() == CustomerId) {
				return i;
			}
		}
		return -1;
	}
	
}