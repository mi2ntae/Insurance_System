package employee;

import java.util.Scanner;

import global.Constants.eEmployeeRole;

public class Employee {
	// Attributes
	private String employeeId;
	private String name;
	private String phoneNumber;
	private String password;
	private eEmployeeRole employeeRole;
	
	// Constructor
	public Employee() {
	}	
	
	// getters & setters
	public String getEmployeeId() {return employeeId;}
	public void setEmployeeId(String employeeId) {this.employeeId = employeeId;}

	public String getName() {return name;}
	public void setName(String name) {this.name = name;}

	public String getPhoneNumber() {return phoneNumber;}
	public void setPhoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber;}

	public String getPassword() {return password;}
	public void setPassword(String password) {this.password = password;}

	public eEmployeeRole getEmployeeRole() {return employeeRole;}
	public void setEmployeeRole(eEmployeeRole employeeRole) {this.employeeRole = employeeRole;}
	
	// Public Methods
	public String writeToFile() {
		String output = null;
		output = this.name + ' ' + this.phoneNumber + ' ' + this.employeeRole.getNum() + ' ' + this.employeeId + ' ' + this.password + '\n';
		return output;
		
	}
	
	public void readFromFile(Scanner sc) {
		this.setName(sc.next());
		this.setPhoneNumber(sc.next());
		int input = sc.nextInt();
		for(eEmployeeRole employeeRole : eEmployeeRole.values()) {
			if(employeeRole.getNum() == input) this.setEmployeeRole(employeeRole);
		}
		this.setEmployeeId(sc.next());
		this.setPassword(sc.next());
	}
	
	// Private Methods
}
