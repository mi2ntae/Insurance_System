package insurance;

import java.sql.SQLException;
import java.util.ArrayList;

import global.Constants.eGender;
import global.Constants.eInsuranceType;
import main.DBConnector;

public class ActualCostInsuranceDAOImpl extends DBConnector implements InsuranceDAO{
	public boolean insert(Insurance insurance) {
		ActualCostInsurance newInsurance = (ActualCostInsurance)insurance;
		
		String str = "INSERT INTO actualCostInsurance(insuranceId, selfBurdenRate) values('" + newInsurance.getInsuranceId() + "','" + newInsurance.getSelfBurdenRate()  + ")";

		if (this.execute(str)) return true;
		else return false;
	}

	public Insurance selectTypeInsurance(Insurance insurance) {
		ActualCostInsurance newInsurance = (ActualCostInsurance)insurance;
		
		String sql = "SELECT * FROM actualCostInsurance";
		this.read(sql);
		
		try {
			while (rs.next()) {
				String input = rs.getString("insuranceId");
				if (input.equals(insurance.getInsuranceId())) {
					newInsurance.setSelfBurdenRate(rs.getDouble("selfBurdenRate"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return newInsurance;
	}

	public ArrayList<Insurance> select() {return null;}

	@Override
	public boolean updateConfirmedStatus(String insuranceId, boolean confirmedStatus) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(String insuranceId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Insurance selectInsurance(String insuranceId) {
		// TODO Auto-generated method stub
		return null;
	}
}
