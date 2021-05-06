package interview;

public interface InterviewList {
	// public Methods
	public boolean add(Interview interview);
	public Interview search(String interviewId);
	public boolean remove(String interviewId);
	public void updateContentById(String interviewId, String content);
}