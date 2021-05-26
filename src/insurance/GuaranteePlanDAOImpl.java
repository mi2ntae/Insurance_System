package insurance;

import java.sql.SQLException;
import java.util.ArrayList;

import main.DBConnector;

public class GuaranteePlanDAOImpl extends DBConnector implements GuaranteePlanDAO{
	public boolean insert(GuaranteePlan guaranteePlan) {
		String str = "INSERT INTO guaranteePlan(insuranceId, content, compensation, rate, special) values('"
				+ guaranteePlan.getInsuranceId() + "','" + guaranteePlan.getContent() + "'," + guaranteePlan.getCompensation() + ","
				+ guaranteePlan.getRate() + "," + guaranteePlan.isSpecial() + ")";
		
		if (this.execute(str))return true;
		else return false;
	}

	public ArrayList<GuaranteePlan> select() {
		ArrayList<GuaranteePlan> arrayList = new ArrayList<GuaranteePlan>();

		String sql = "SELECT * FROM Customer";
		this.read(sql);
		
		try {
			while (rs.next()) {
				GuaranteePlan guaranteePlan = new GuaranteePlan();
				guaranteePlan.setInsuranceId(rs.getString("insuranceId"));
				guaranteePlan.setContent(rs.getString("content"));
				guaranteePlan.setCompensation(rs.getInt("compensation"));
				guaranteePlan.setRate(rs.getDouble("rate"));
				guaranteePlan.setSpecial(rs.getBoolean("special"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return arrayList;
	}
}
