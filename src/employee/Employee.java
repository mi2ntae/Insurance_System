package employee;

public class Employee {
	// Attributes
	private String employeeId;
	private String name;
	private String phoneNumber;
	private String password;
	
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

}
