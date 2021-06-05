package insurance;

import java.sql.SQLException;
import java.util.ArrayList;

import global.Constants.eGender;
import global.Constants.eInsuranceType;
import main.DBConnector;

public class DentalInsuranceDAOImpl extends DBConnector implements InsuranceDAO{
	public boolean insert(Insurance insurance) {
		DentalInsurance newInsurance = (DentalInsurance) insurance;

		String str = "INSERT INTO dentalInsurance(insuranceId, annualLimitCount, useCount) values('"
				+ newInsurance.getInsuranceId() + "'," + newInsurance.getAnnualLimitCount()
				+ newInsurance.getUseCount() + ")";

		if (this.execute(str))
			return true;
		else return false;
	}

	public Insurance selectTypeInsurance(Insurance insurance) {
		DentalInsurance newInsurance = (DentalInsurance)insurance;
		
		String sql = "SELECT * FROM dentalInsurance";
		this.read(sql);
		
		try {
			while (rs.next()) {
				String input = rs.getString("insuranceId");
				if (input.equals(insurance.getInsuranceId())) {
					newInsurance.setAnnualLimitCount(rs.getInt("annualLimitCount"));
					newInsurance.setUseCount(rs.getInt("useCount"));
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

	@Override
	public boolean updateDel(String insuranceId, boolean del) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteInsuranceByTime() {
		// TODO Auto-generated method stub
		return false;
	}
}
