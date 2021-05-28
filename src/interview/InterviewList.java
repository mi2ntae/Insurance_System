package interview;

import java.util.ArrayList;

public interface InterviewList {
	// public Methods
	public boolean insert(Interview interview);
	public Interview search(String interviewId);
	public boolean delete(String interviewId);
	public void updateContentById(String interviewId, String content);
	
	public ArrayList<Interview> getinterviewList();
	public void setinterviewList(ArrayList<Interview> interviewList);

}