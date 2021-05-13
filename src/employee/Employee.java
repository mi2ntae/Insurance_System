package employee;

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
	
//	System.out.println("1.보험개발자");
//	System.out.println("2.보험상품 확정자");
//	System.out.println("3.영업사원");
//	System.out.println("4.계약관리인");
//	System.out.println("5.보상처리사");
//	System.out.println("6.U/W");
}
