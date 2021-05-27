package employee;

import interview.Interview;
import interview.InterviewList;
import interview.InterviewListImpl;

public class Salesperson extends Employee{

	public InterviewList interviewList;

	public Salesperson(){
		this.interviewList = new InterviewListImpl();
	}

	public void checkInterviewList(){

	}

	public void checkPerformance(){

	}

	public void checkWholeInsuranceList(){

	}

	public void writeReport(Interview interview,String input){
		interview.setContent(input);
	}

}