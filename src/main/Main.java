package main;
import customer.Customer;
import customer.CustomerListImpl;

public class Main {

	public static void main(String[] args) {
		CustomerListImpl customerList = new CustomerListImpl();
		customerList.insert(new Customer("º“«œ∑Œ38", "5kso3", "±Ëº“√∂", "01071300465"));
		System.out.println(customerList.select("5kso3").getName());
		customerList.updateNameById("5kso3", "±ËπŒ≈¬");
		System.out.println(customerList.delete("5kso3"));
		System.out.println(customerList.delete("5kso3"));
	}
}
