package employee;

import java.util.ArrayList;

public interface EmployeeDAO {
	public boolean insert(Employee employee);
	public ArrayList<Employee> select();
	public boolean updateSaleHistory(String employeeId, int saleHistory);
	public boolean delete(String employeeId);
}
