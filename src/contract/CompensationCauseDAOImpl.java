package contract;

import java.sql.SQLException;

import main.DBConnector;

public class CompensationCauseDAOImpl extends DBConnector implements CompensationCauseDAO{
	public boolean insert(String accidentId, String cause) {
		String sql = "INSERT INTO compensationCause(accidentId, cause) values('" + accidentId + "', '" + cause + "');";
	return this.execute(sql);
	}

	public String selectByAccidentId(String accidentId) {
		String sql = "SELCT * FROM compensationCause WHERE contractId = '"+accidentId+"';";
		this.read(sql);
		try {
			return rs.getString("cause");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
