package interview;

public class Interview {
	// Attributes
	private String interviewId;
	private String salespersonId;
	private String customerId;
	private boolean confirmedStatus;
	private String date;
	private String content;

	// Constructor
	public Interview(){

	}

	// Getters&Setters
	public String getInterviewId() {return interviewId;}
	public void setInterviewId(String interviewId) {this.interviewId = interviewId;}

	public String getSalespersonId() {return salespersonId;}
	public void setSalespersonId(String salespersonId) {this.salespersonId = salespersonId;}

	public String getCustomerId() {return customerId;}
	public void setCustomerId(String customerId) {this.customerId = customerId;}

	public boolean isConfirmedStatus() {return confirmedStatus;}
	public void setConfirmedStatus(boolean confirmedStatus) {this.confirmedStatus = confirmedStatus;}

	public String getDate() {return date;}
	public void setDate(String date) {this.date = date;}

	public String getContent() {return content;}
	public void setContent(String content) {this.content = content;}

}