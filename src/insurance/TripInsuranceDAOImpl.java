package insurance;

import java.sql.SQLException;
import java.util.ArrayList;

import global.Constants.eGender;
import global.Constants.eInsuranceType;
import main.DBConnector;

public class TripInsuranceDAOImpl extends DBConnector implements InsuranceDAO{
	public boolean insert(Insurance insurance) {
		TripInsurance newInsurance = (TripInsurance)insurance;
		
		String str = "INSERT INTO tripInsurance(insuranceId, rateOfCountryRisk0, rateOfCountryRisk1, rateOfCountryRisk2, rateOfCountryRisk3"
				+ ") values('" + newInsurance.getInsuranceId() + "'";
		for (double rate : newInsurance.getRateOfCountryRank()) str += "," + rate;
		str += ")";
		if (this.execute(str)) return true;
		else return false;
	}

	public Insurance selectTypeInsurance(Insurance insurance) {
		TripInsurance newInsurance = (TripInsurance)insurance;
		
		String sql = "SELECT * FROM tripInsurance";
		this.read(sql);
		
		try {
			while (rs.next()) {
				String input = rs.getString("insuranceId");
				if (input.equals(insurance.getInsuranceId())) {
					double[] rateOfCountryRisk = new double[newInsurance.getRateOfCountryRank().length];
					for (int i = 0; i < newInsurance.getRateOfCountryRank().length; i++) rateOfCountryRisk[i] = rs.getDouble("rateOfCountryRisk"+i);
					newInsurance.setRateOfCountryRank(rateOfCountryRisk);
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
