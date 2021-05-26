package interview;

import java.util.ArrayList;

public interface InterviewDAO {
	public boolean insert(Interview interView);
	public ArrayList<Interview> select();
	public boolean update(Interview interview);
	public boolean delete(String interviewId);
}
