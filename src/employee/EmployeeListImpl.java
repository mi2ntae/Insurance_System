package employee;

import java.util.ArrayList;

public class EmployeeListImpl implements EmployeeList {

	// Components -> 교수님에게 질문
	private ArrayList<Employee> employeeList;
	
	// Composition Class
	 private Employee employee;

	// Constructor
	public EmployeeListImpl() {
		this.employeeList = new ArrayList<Employee>();
	}
	
	// getters & setters
	public ArrayList<Employee> getEmployeeList() {return employeeList;}
	public void setEmployeeList(ArrayList<Employee> employeeList) {this.employeeList = employeeList;}
	
	// Public Methods
	public boolean insert(Employee employee) {
		if (this.employeeList.add(employee)) {
			return true;
		} else {
			return false;
		}
	}

	public Employee select(String employeerId) {
		for (Employee employee : this.employeeList) {
			if (employee.getEmployeeId().equals(employeerId)) {
				return employee;
			}
		}
		return null;
	}

	public boolean delete(String employeerId) {
		int index = this.getEmployeeIndex(employeerId);
		if(index > -1) {
			this.employeeList.remove(index);
			return true;
		} else {
			return false;
		}
		
	}

	public void updateNameById(String employeerId, String data) {
		int index = this.getEmployeeIndex(employeerId);
		if(index > -1) {
			this.employeeList.get(index).setName(data);
		}
	}
	
	public boolean checkDuplication(String input) {
		for(Employee employee: employeeList) {
			if(employee.getEmployeeId().equals(input)) {
				return false;
			}
		}
		return true;
	}

	// Private Methods
	private int getEmployeeIndex(String employeerId) {
		for (int i = 0; i < this.employeeList.size(); i++) {
			if (this.employeeList.get(i).getEmployeeId() == employeerId) {
				return i;
			}
		}
		return -1;
	}
	
}