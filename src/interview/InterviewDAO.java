package interview;

import java.util.ArrayList;

public interface InterviewDAO {
	public boolean insert(Interview interView);
	public ArrayList<Interview> select();
	public boolean updateConfirmedStatus(String interviewId, boolean confirmedStatus);
	public boolean updateDate(String interviewId, String date);
	public boolean updateContent(String interviewId, String content);
	public boolean delete(String interviewId);
}
