package db.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import business.customer.Customer;
import business.customer.Insurant;
import db.DBConnector;

public class CustomerDAOImpl extends DBConnector implements CustomerDAO{
	public boolean insert(Customer customer) {
		String str = "INSERT INTO customer(name, address, phoneNumber, customerId, password) values('"
				+ customer.getName() + "','" + customer.getAddress() + "','" + customer.getPhoneNumber() + "','"
				+ customer.getCustomerId() + "','" + customer.getPassword() + "')";
		if (this.execute(str))
			return true;
		else
			return false;
	}

	public ArrayList<Customer> select() {
		ArrayList<Customer> arrayList = new ArrayList<Customer>();
		InsurantDAO insurantDAO = new InsurantDAOImpl();
		String sql = "SELECT * FROM customer";
		this.read(sql);
		try {
			while (rs.next()) {
				Customer customer = new Customer();
				customer.setName(rs.getString("name"));
				customer.setAddress(rs.getString("address"));
				customer.setPhoneNumber(rs.getString("phoneNumber"));
				customer.setCustomerId(rs.getString("customerId"));
				customer.setPassword(rs.getString("password"));
				
				arrayList.add(customer);
			}
			
			for(Insurant insurant : insurantDAO.select()) {
				for(Customer customer : arrayList) {
					if(customer.getCustomerId().equals(insurant.getCustomerId())) {
						customer.getInsurantList().add(insurant);
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arrayList;
	}

	@Override
	public boolean updateAddress(String customerId, String address) {
		String str = "UPDATE customer set address = " + address + " WHERE CustomerId = " + customerId;
		if(this.execute(str)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean updatePhoneNumber(String customerId, String phoneNumber) {
		String str = "UPDATE customer set phoneNumber = " + phoneNumber + " WHERE CustomerId = " + customerId;
		if(this.execute(str)) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public boolean delete(String customerId) {
		String str = "DELETE FROM customer WHERE CustomerId = '" + customerId + "'";
		if(this.execute(str)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Customer selectCustomer(String customerId) {
		boolean flag = false;
		InsurantDAO insurantDAO = new InsurantDAOImpl();
		Customer customer = new Customer();
		String sql = "SELECT * FROM customer WHERE CustomerId = '" + customerId +"'";
		this.read(sql);
		try {
			while (rs.next()) {
				customer.setName(rs.getString("name"));
				customer.setAddress(rs.getString("address"));
				customer.setPhoneNumber(rs.getString("phoneNumber"));
				customer.setCustomerId(rs.getString("customerId"));
				customer.setPassword(rs.getString("password"));
				flag = true;
			}
			if(flag) {
				for (Insurant insurant : insurantDAO.select()) {
					if (customer.getCustomerId().equals(insurant.getCustomerId())) {
						customer.getInsurantList().add(insurant);
					}
				}
				return customer;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Customer selectCustomerByIdPw(String customerId, String password) {
		boolean flag = false;
		InsurantDAO insurantDAO = new InsurantDAOImpl();
		Customer customer = new Customer();
		String sql = "SELECT * FROM customer WHERE CustomerId = '" + customerId +"' AND password = '" + password + "'";
		this.read(sql);
		try {
			while (rs.next()) {
				customer.setName(rs.getString("name"));
				customer.setAddress(rs.getString("address"));
				customer.setPhoneNumber(rs.getString("phoneNumber"));
				customer.setCustomerId(rs.getString("customerId"));
				customer.setPassword(rs.getString("password"));
				flag = true;
			}
			if(flag) {
				for (Insurant insurant : insurantDAO.select()) {
					if (customer.getCustomerId().equals(insurant.getCustomerId())) {
						customer.getInsurantList().add(insurant);
					}
				}
				return customer;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
