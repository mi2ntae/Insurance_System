package customer;

import java.sql.SQLException;
import java.util.ArrayList;

import main.DBConnector;

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
		String sql = "SELECT * FROM Customer";
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arrayList;
	}

	@Override
	public boolean update(Customer customer) {
		String str = "UPDATE customer set name = " + customer.getName() + "','" + "address = " + customer.getAddress() + "','"
				+ "phoneNumber = " + customer.getPassword() + "','" + "password = " + customer.getPassword() 
				+ " WHERE CustomerId = " + customer.getCustomerId();
		if(this.execute(str)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean delete(String customerId) {
		String str = "DELETE FROM customer WHERE CustomerId = " + customerId;
		if(this.execute(str)) {
			return true;
		} else {
			return false;
		}
	}
}
