package employee;

public interface EmployeeList {
	
	// Methods
	public boolean insert(Employee employee);
	public Employee select(String employeeId);
	public boolean delete(String employeeId);
	public void updateNameById(String employeeId, String data);
	public boolean checkDuplication(String input);
}