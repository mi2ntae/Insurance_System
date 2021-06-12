package db.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import business.insurance.DriverInsurance;
import business.insurance.Insurance;
import db.DBConnector;

public class DriverInsuranceDAOImpl extends DBConnector implements InsuranceDAO{
	public boolean insert(Insurance insurance) {
		DriverInsurance newInsurance = (DriverInsurance)insurance;
		
		String str = "INSERT INTO driverInsurance(insuranceId, rateOfAccidentHistory0, rateOfAccidentHistory1, rateOfAccidentHistory2,"
				+ " rateOfAccidentHistory3, rateOfAccidentHistory4, rateOfAccidentHistory5, rateOfCarType0, rateOfCarType1, rateOfCarType2,"
				+ " rateOfCarType3, rateOfCarType4, rateOfCarRank0, rateOfCarRank1, rateOfCarRank2, rateOfCarRank3"
				+ " ) values('" + newInsurance.getInsuranceId() + "'";
		for (double rate : newInsurance.getRateOfAccidentHistory()) str += "," + rate;
		for (double rate : newInsurance.getRateOfCarType()) str += "," + rate;
		for (double rate : newInsurance.getRateOfCarRank()) str += "," + rate;
		str += ")";

		if (this.execute(str)) return true;
		else return false;
	}

	public Insurance selectTypeInsurance(Insurance insurance) {
		DriverInsurance newInsurance = (DriverInsurance)insurance;
		
		String sql = "SELECT * FROM driverInsurance";
		this.read(sql);
		
		try {
			while (rs.next()) {
				String input = rs.getString("insuranceId");
				if (input.equals(insurance.getInsuranceId())) {
					double[] rateOfAccidentHistory = new double[newInsurance.getRateOfAccidentHistory().length];
					for (int i = 0; i < newInsurance.getRateOfAccidentHistory().length; i++) rateOfAccidentHistory[i] = rs.getDouble("rateOfAccidentHistory"+i);
					newInsurance.setRateOfAccidentHistory(rateOfAccidentHistory);
					double[] rateOfCarType = new double[newInsurance.getRateOfCarType().length];
					for (int i = 0; i < newInsurance.getRateOfCarType().length; i++) rateOfCarType[i] = rs.getDouble("rateOfCarType"+i);
					newInsurance.setRateOfCarType(rateOfCarType);
					double[] rateOfCarRank = new double[newInsurance.getRateOfCarRank().length];
					for (int i = 0; i < newInsurance.getRateOfCarRank().length; i++) rateOfCarRank[i] = rs.getDouble("rateOfCarRank"+i);
					newInsurance.setRateOfCarRank(rateOfCarRank);
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
	public ArrayList<Insurance> selectForConfirm() {return null;}
	public ArrayList<String> selectInsuranceId(){return null;}

}
