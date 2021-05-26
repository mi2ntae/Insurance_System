package employee;

import java.sql.SQLException;
import java.util.ArrayList;

import contract.Accident;
import global.Constants.eEmployeeRole;
import main.DBConnector;

public class EmployeeDAOImpl extends DBConnector implements EmployeeDAO{

	@Override
	public boolean insert(Employee employee) {
		String sql = "INSERT INTO employee(employeeId, password, name, phoneNumber, role"
				+ ", saleHistory) values ('"+employee.getEmployeeId()+"', '"+employee.getPassword()
				+"', '"+employee.getName()+"', '"+employee.getPhoneNumber()+"', "+employee.getEmployeeRole().getNum()
				+", "+employee.getSaleHistory()+");";
		return this.execute(sql);
	}

	@Override
	public ArrayList<Employee> select() {
		ArrayList<Employee> employeeList = new ArrayList<Employee>();

		String sql = "SELECT * FROM employee";
		this.read(sql);
		try {
			while (rs.next()) {
				Employee employee = new Employee();
				employee.setEmployeeId(rs.getString("employeeId"));
				employee.setPassword(rs.getString("password"));
				employee.setName(rs.getString("name"));
				employee.setPhoneNumber(rs.getString("phoneNumber"));
				int role = rs.getInt("role");
				for (eEmployeeRole employeeRole: eEmployeeRole.values()) {
					if (employeeRole.getNum() == role) {
						employee.setEmployeeRole(employeeRole);
					}
				}
				employee.setSaleHistory(rs.getInt("saleHistory"));
				employeeList.add(employee);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employeeList;
	}

	@Override
	public boolean updateSaleHistory(String employeeId, int saleHistory) {
		String sql = "UPDATE employee SET saleHistory = "+saleHistory+" WHERE employeeId = '"+employeeId+"';";
		return this.execute(sql);
	}

	@Override
	public boolean delete(String employeeId) {
		String sql = "DELETE employee WHERE employeeId = '"+employeeId+"';";
		return this.execute(sql);
	}

}
