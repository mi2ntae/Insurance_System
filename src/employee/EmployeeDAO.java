package employee;

import java.util.ArrayList;

public interface EmployeeDAO {
	public boolean insert(Employee employee);
	public ArrayList<Employee> select();
	public Employee selectEmployee(String employeeId);
	public Employee selectEmployeeByIdPw(String employeeId, String password);
	public Employee selectSlaesPerson(String employeeId);
	public ArrayList<Employee> selectSlaesPersons();
	public boolean updateSaleHistory(String employeeId, int saleHistory);
	public boolean delete(String employeeId);
}
