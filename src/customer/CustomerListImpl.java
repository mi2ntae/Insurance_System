package customer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class CustomerListImpl implements CustomerList {

	// Components -> 교수님에게 질문
	private ArrayList<Customer> customerList;
	
	// Composition Class
	 private Customer customer;

	// Constructor
	public CustomerListImpl() throws FileNotFoundException {
		this.customerList = new ArrayList<Customer>();
		this.readFromFile();
	}
	
	// Getters & Setters
	public ArrayList<Customer> getCustomerList() {return customerList;}
	public void setCustomerList(ArrayList<Customer> customerList) {this.customerList = customerList;}
	
	// Public Methods
	public boolean insert(Customer customer) {
		if (this.customerList.add(customer)) {
			this.writeToFile(customer);
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
	
	private void writeToFile(Customer customer) {
		File file = new File("data/customer");
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
		    writer.append(customer.writeToFile());
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
	
	private void readFromFile() throws FileNotFoundException {
		File file = new File("data/customer");
		Scanner sc = new Scanner(file);
		while (sc.hasNext()) {
			Customer customer = new Customer();
			customer.readFromFile(sc);
			this.customerList.add(customer);
		}	
		
	}
	
}