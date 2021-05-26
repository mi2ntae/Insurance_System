package insurance;

import java.sql.SQLException;
import java.util.ArrayList;

import global.Constants.eGender;
import global.Constants.eInsuranceType;
import main.DBConnector;

public class FireInsuranceDAOImpl extends DBConnector implements InsuranceDAO{
	public boolean insert(Insurance insurance) {
		FireInsurance newInsurance = (FireInsurance)insurance;
		
		String str = "INSERT INTO fireInsurance(insuranceId, rateOfPostedPrice0, rateOfPostedPrice1, rateOfPostedPrice2, rateOfPostedPrice3,"
				+ " rateOfPostedPrice4, rateOfStructureUsage0, rateOfStructureUsage1, rateOfStructureUsage2, rateOfStructureUsage3,"
				+ " rateOfStructureUsage4, rateOfStructureUsage5) values('" + newInsurance.getInsuranceId() + "'";
		for (double rate : newInsurance.getRateOfPostedPrice()) str += "," + rate;
		for (double rate : newInsurance.getRateOfStructureUsage()) str += "," + rate;
		str += ")";

		if (this.execute(str)) return true;
		else return false;
	}

	public Insurance selectTypeInsurance(Insurance insurance) {
		FireInsurance newInsurance = (FireInsurance)insurance;
		
		String sql = "SELECT * FROM fireInsurance";
		this.read(sql);
		
		try {
			while (rs.next()) {
				String input = rs.getString("insuranceId");
				if (input.equals(insurance.getInsuranceId())) {
					double[] rateOfPostedPrice = new double[newInsurance.getRateOfPostedPrice().length];
					for (int i = 0; i < newInsurance.getRateOfPostedPrice().length; i++) rateOfPostedPrice[i] = rs.getDouble("rateOfPostedPrice"+i);
					newInsurance.setRateOfPostedPrice(rateOfPostedPrice);
					double[] rateOfStructureUsage = new double[newInsurance.getRateOfStructureUsage().length];
					for (int i = 0; i < newInsurance.getRateOfStructureUsage().length; i++) rateOfStructureUsage[i] = rs.getDouble("rateOfStructureUsage"+i);
					newInsurance.setRateOfStructureUsage(rateOfStructureUsage);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return newInsurance;
	}

	public ArrayList<Insurance> select() {return null;}

	@Override
	public boolean updateConfirmedStatus(String insuranceId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(String insuranceId) {
		// TODO Auto-generated method stub
		return false;
	}
}
