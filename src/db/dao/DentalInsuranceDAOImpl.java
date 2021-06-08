package db.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import business.insurance.DentalInsurance;
import business.insurance.Insurance;
import db.DBConnector;

public class DentalInsuranceDAOImpl extends DBConnector implements InsuranceDAO{
	public boolean insert(Insurance insurance) {
		DentalInsurance newInsurance = (DentalInsurance) insurance;

		String str = "INSERT INTO dentalInsurance(insuranceId, annualLimitCount) values('"
				+ newInsurance.getInsuranceId() + "'," + newInsurance.getAnnualLimitCount()+")";

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
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return newInsurance;
	}

	public ArrayList<Insurance> select() {return null;}
	public boolean updateConfirmedStatus(String insuranceId, boolean confirmedStatus) {return false;}
	public boolean delete(String insuranceId) {return false;}
	public Insurance selectInsurance(String insuranceId) {return null;}
	public boolean updateDel(String insuranceId, boolean del) {return false;}
	public boolean deleteInsuranceByTime() {return false;}
}
