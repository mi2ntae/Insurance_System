package employee;

import interview.Interview;
import interview.InterviewDAO;

public class Salesperson extends Employee{
	
	private InterviewDAO interviewDAO;

	public Salesperson(InterviewDAO interviewDAO){
		this.interviewDAO = interviewDAO;
	}

	public void checkInterviewList(){

	}

	public void checkPerformance(){

	}

	public void checkWholeInsuranceList(){

	}

	public void writeReport(Interview interview,String input){
		this.interviewDAO.updateContent(interview.getInterviewId(), input);
		this.interviewDAO.updateConfirmedStatus(interview.getInterviewId(), true);
	}

}