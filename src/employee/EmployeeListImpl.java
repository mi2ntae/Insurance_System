package employee;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import customer.Customer;

public class EmployeeListImpl implements EmployeeList {

	// Components -> 교수님에게 질문
	private ArrayList<Employee> employeeList;
	
	// Composition Class
	 private Employee employee;

	// Constructor
	public EmployeeListImpl() throws FileNotFoundException{
		this.employeeList = new ArrayList<Employee>();
		this.readFromFile();
	}
	
	// getters & setters
	public ArrayList<Employee> getEmployeeList() {return employeeList;}
	public void setEmployeeList(ArrayList<Employee> employeeList) {this.employeeList = employeeList;}
	
	// Public Methods
	public boolean insert(Employee employee) {
		if (this.employeeList.add(employee)) {
			this.writeToFile(employee);
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
	
	private void writeToFile(Employee employee) {
		File file = new File("data/employee");
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
		    writer.append(employee.writeToFile());
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
	
	private void readFromFile() throws FileNotFoundException {
		File file = new File("data/employee");
		Scanner sc = new Scanner(file);
		while (sc.hasNext()) {
			Employee employee = new Employee();
			employee.readFromFile(sc);
			this.employeeList.add(employee);
		}	
		
	}
	
}