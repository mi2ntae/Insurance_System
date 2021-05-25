package customer;

import java.sql.SQLException;
import java.util.ArrayList;

import main.DBConnector;

public class CustomerDAO extends DBConnector{
	public boolean insert(Customer customer) {
		String str = customer.getName() + ' ' + customer.getAddress() + ' ' + customer.getPhoneNumber() + ' '
				+ customer.getCustomerId() + ' ' + customer.getPassword() + '\n';
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
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return arrayList;
	}
}
