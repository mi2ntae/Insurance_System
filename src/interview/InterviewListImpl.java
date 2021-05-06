package interview;

import java.util.ArrayList;

public class InterviewListImpl implements InterviewList {
	// Components
	private ArrayList<Interview> interviewList;
	
	// Constructor
	public InterviewListImpl() {
		this.interviewList = new ArrayList<Interview>();
	}

	// Getters&Setters
	public ArrayList<Interview> getinterviewList() {return interviewList;}
	public void setinterviewList(ArrayList<Interview> interviewList) {this.interviewList = interviewList;}

	// public Method
	public boolean insert(Interview interview) {
		if (this.interviewList.add(interview)) {
			return true;
		} else {
			return false;
		}
	}

	public Interview search(String interviewId) {
		for (Interview interview : this.interviewList) {
			if (interview.getInterviewId().equals(interviewId)) {
				return interview;
			}
		}
		return null;
	}

	public boolean delete(String interviewId) {
		int deleteIndex = getInterviewIndex(interviewId);
		if(deleteIndex != -1) {
			this.interviewList.remove(deleteIndex);
			return true;
		} else {
			return false;
		}
		
	}

	public void updateContentById(String interviewId, String content) {
		int updateIndex = getInterviewIndex(interviewId);
		if(updateIndex != -1) {
			this.interviewList.get(updateIndex).setContent(content);
		}
	}
	
	// private Method
	private int getInterviewIndex(String interviewId) {
		for (int i = 0; i < this.interviewList.size(); i++) {
			if (this.interviewList.get(i).getInterviewId().equals(interviewId)) {
				return i;
			}
		}
		return -1;
	}
	
}