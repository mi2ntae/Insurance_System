package interview;

import java.sql.SQLException;
import java.util.ArrayList;
import main.DBConnector;

public class InterviewDAOImpl extends DBConnector implements InterviewDAO{

	@Override
	public boolean insert(Interview interview) {
		String str = "INSERT INTO Interview (interviewId, salespersonId, customerId, confirmedStatus, date, content) values ('"
				+ interview.getInterviewId() + "','" + interview.getSalespersonId() + "','" + interview.getCustomerId()
				+ interview.isConfirmedStatus() + "','" + interview.getDate() + "','" + interview.getContent() + "')";
		if (this.execute(str)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public ArrayList<Interview> select() {
		ArrayList<Interview> arrayList = new ArrayList<Interview>();
		String sql = "SELECT * FROM Interview";
		try {
			while (rs.next()) {
				Interview interview = new Interview();
				interview.setInterviewId(rs.getString("interviewId"));
				interview.setSalespersonId(rs.getString("salespersonId"));
				interview.setCustomerId(rs.getString("customerId"));
				interview.setConfirmedStatus(rs.getBoolean("confirmedStatus"));
				interview.setDate(rs.getString("date"));
				interview.setContent(rs.getString("content"));
				arrayList.add(interview);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arrayList;
	}


	@Override
	public boolean delete(String interviewId) {
		String str = "DELETE FROM Interview WHERE interviewId = " + interviewId;
		if(this.execute(str)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean updateConfirmedStatus(String interviewId, boolean confirmedStatus) {
		String str = "UPDATE interview set confirmedStatus = " + confirmedStatus + " WHERE interviewId = " + interviewId;
		if(this.execute(str)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean updateDate(String interviewId, String date) {
		String str = "UPDATE interview set date = " + date + " WHERE interviewId = " + interviewId;
		if(this.execute(str)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean updateContent(String interviewId, String content) {
		String str = "UPDATE interview set content = " + content + " WHERE interviewId = " + interviewId;
		if(this.execute(str)) {
			return true;
		} else {
			return false;
		}
	}
}
