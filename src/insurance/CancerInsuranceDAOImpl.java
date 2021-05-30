package insurance;

import java.sql.SQLException;
import java.util.ArrayList;

import global.Constants.eGender;
import global.Constants.eInsuranceType;
import main.DBConnector;

public class CancerInsuranceDAOImpl extends DBConnector implements InsuranceDAO{
	public boolean insert(Insurance insurance) {
		CancerInsurance newInsurance = (CancerInsurance)insurance;
		
		String str = "INSERT INTO cancerInsurance(insuranceId, rateOfFamilyMedicalDisease0, rateOfFamilyMedicalDisease1, rateOfFamilyMedicalDisease2,"
				+ " rateOfFamilyMedicalDisease3, rateOfFamilyMedicalDisease4, rateOfFamilyMedicalRelationship0, rateOfFamilyMedicalRelationship1,"
				+ " rateOfFamilyMedicalRelationship2, rateOfFamilyMedicalRelationship3"
				+ " ) values('" + newInsurance.getInsuranceId() + "'";
		for (double rate : newInsurance.getRateOfFamilyMedicalDisease()) str += "," + rate;
		for (double rate : newInsurance.getRateOfFamilyMedicalRelationship()) str += "," + rate;
		str += ")";

		if (this.execute(str)) return true;
		else return false;
	}

	public Insurance selectTypeInsurance(Insurance insurance) {
		CancerInsurance newInsurance = (CancerInsurance)insurance;
		
		String sql = "SELECT * FROM cancerInsurance";
		this.read(sql);
		
		try {
			while (rs.next()) {
				String input = rs.getString("insuranceId");
				if (input.equals(insurance.getInsuranceId())) {
					double[] rateOfFamilyMedicalDisease = new double[newInsurance.getRateOfFamilyMedicalDisease().length];
					for (int i = 0; i < newInsurance.getRateOfFamilyMedicalDisease().length; i++) rateOfFamilyMedicalDisease[i] = rs.getDouble("rateOfFamilyMedicalDisease"+i);
					newInsurance.setRateOfFamilyMedicalDisease(rateOfFamilyMedicalDisease);
					double[] rateOfFamilyMedicalRelationship = new double[newInsurance.getRateOfFamilyMedicalRelationship().length];
					for (int i = 0; i < newInsurance.getRateOfFamilyMedicalRelationship().length; i++) rateOfFamilyMedicalRelationship[i] = rs.getDouble("rateOfFamilyMedicalRelationship"+i);
					newInsurance.setRateOfFamilyMedicalRelationship(rateOfFamilyMedicalRelationship);
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

	@Override
	public Insurance selectInsurance(String insuranceId) {
		// TODO Auto-generated method stub
		return null;
	}
}
