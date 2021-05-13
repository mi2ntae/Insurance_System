package employee;

import java.util.ArrayList;

public interface EmployeeList {
	// getters & setters
	public ArrayList<Employee> getEmployeeList();
	public void setEmployeeList(ArrayList<Employee> employeeList);
	// Methods
	public boolean insert(Employee employee);
	public Employee select(String employeeId);
	public boolean delete(String employeeId);
	public void updateNameById(String employeeId, String data);
	public boolean checkDuplication(String input);
}