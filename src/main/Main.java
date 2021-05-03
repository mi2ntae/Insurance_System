package main;
import customer.Customer;
import customer.CustomerListImpl;

public class Main {

	public static void main(String[] args) {
		CustomerListImpl customerList = new CustomerListImpl();
		customerList.insert(new Customer("경기도 광명시", "5kso3", "김소철", "01071300465"));
		System.out.println(customerList.select("5kso3").getName());
		customerList.updateNameById("5kso3", "김민태");
		System.out.println(customerList.delete("majormin"));
		System.out.println(customerList.delete("5kso3"));
	}
}
