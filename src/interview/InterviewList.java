package interview;

public interface InterviewList {
	// public Methods
	public boolean insert(Interview interview);
	public Interview search(String interviewId);
	public boolean delete(String interviewId);
	public void updateContentById(String interviewId, String content);
}