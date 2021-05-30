package insurance;

import java.sql.SQLException;
import java.util.ArrayList;

import global.Constants.eGender;
import global.Constants.eInsuranceType;
import main.DBConnector;

public class InsuranceDAOImpl extends DBConnector implements InsuranceDAO{
	public boolean insert(Insurance insurance) {
		String str = "INSERT INTO insurance(insuranceId, NAME, TYPE, gender, basicFee, specialContractFee, warrantyPeriod,"
				+ " rateOfAge0, rateOfAge1, rateOfAge2, rateOfAge3, rateOfAge4, rateOfAge5, rateOfAge6,"
				+ " rateOfGender0, rateOfGender1, rateOfJob0, rateOfJob1, rateOfJob2,"
				+ " rateOfJob3, rateOfJob4, rateOfJob5, rateOfJob6, confirmedStatus, specialContract) values('"
				+ insurance.getInsuranceId() + "','" + insurance.getName() + "'," + insurance.getType().getNum() + ","
				+ insurance.getGender().getNum() + "," + insurance.getBasicFee() + ","
				+ insurance.getSpecialContractFee() + "," + insurance.getWarrantyPeriod() + ",";
		int cnt = 0;
		for (double rate : insurance.getRateOfAge()) {str += rate + ",";
		cnt ++;
		}
		System.out.println(cnt);
		cnt = 0;
		for (double rate : insurance.getRateOfGender()) {
			str += rate + ",";
			cnt++;
		}
		System.out.println(cnt);
		cnt = 0;
		for (double rate : insurance.getRateOfJob()) {
			str += rate + ",";
			cnt++;
		}
		System.out.println(cnt);
		str += insurance.isConfirmedStatus() + "," + insurance.isSpecialContract() + ")";
		
		InsuranceDAO insuranceDao = insurance.getType().getInsuranceDAO();

		System.out.println(str);
		if(this.execute(str)) {
			if(insuranceDao.insert(insurance))return true;
			else return false;
		}else {
			return false;
		}
	}

	public ArrayList<Insurance> select() {
		ArrayList<Insurance> arrayList = new ArrayList<Insurance>();
		GuaranteePlanDAO guaranteePlanDAO = new GuaranteePlanDAOImpl();

		String sql = "SELECT * FROM insurance";
		
		this.read(sql);
		
		try {
			while (rs.next()) {
				int input = rs.getInt("type");
				for (eInsuranceType insuranceType : eInsuranceType.values()) {
					if (insuranceType.getNum() == input) {
						Insurance insurance = insuranceType.getSelectedInsurance().newInstance();
						insurance.setInsuranceId(rs.getString("insuranceId"));
						insurance.setName(rs.getString("name"));
						for (eInsuranceType type : eInsuranceType.values()) if (type.getNum() == input) insurance.setType(type);
						for (eGender gender : eGender.values()) if (gender.getNum() == rs.getInt("gender")) insurance.setGender(gender);
						insurance.setBasicFee(rs.getInt("basicFee"));
						insurance.setSpecialContractFee(rs.getInt("specialContractFee"));
						insurance.setWarrantyPeriod(rs.getInt("warrantyPeriod"));
						double[] rateOfAge = new double[insurance.getRateOfAge().length];
						for (int i = 0; i < insurance.getRateOfAge().length; i++) rateOfAge[i] = rs.getDouble("rateOfAge"+i);
						double[] rateOfGender = new double[insurance.getRateOfGender().length];
						for (int i = 0; i < insurance.getRateOfGender().length; i++) rateOfGender[i] = rs.getDouble("rateOfGender"+i);
						double[] rateOfJob = new double[insurance.getRateOfJob().length];
						for (int i = 0; i < insurance.getRateOfJob().length; i++) rateOfJob[i] = rs.getDouble("rateOfJob"+i);
						insurance.setRateOfAge(rateOfAge);
						insurance.setRateOfGender(rateOfGender);
						insurance.setRateOfJob(rateOfJob);
						insurance.setConfirmedStatus(rs.getBoolean("confirmedStatus"));
						insurance.setSpecialContract(rs.getBoolean("specialContract"));

						
						arrayList.add(insurance);
					}
				}
			}
			for(Insurance insurance : arrayList) {
				InsuranceDAO insuranceDao = insurance.getType().getInsuranceDAO();
				insurance = insuranceDao.selectTypeInsurance(insurance);
			}
			for(Insurance insurance : arrayList) {
				insurance.setGuaranteePlanList(guaranteePlanDAO.selectById(insurance.getInsuranceId()));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return arrayList;
	}

	public boolean updateConfirmedStatus(String insuranceId) {
		String str = "UPDATE insurance SET confirmedStatus = 1 WHERE insuranceId = '" + insuranceId + "'";
		if (this.execute(str)) return true;
		else return false;
	}
	
	public boolean updateBasicFee(String insuranceId, int basicFee) {
		String str = "UPDATE insurance SET basicFee = " + basicFee + " WHERE insuranceId = '" + insuranceId + "'";
		if (this.execute(str)) return true;
		else return false;
	}
	
	public boolean updateSpeCialContractFee(String insuranceId, int speCialContractFee) {
		String str = "UPDATE insurance SET speCialContractFee = " + speCialContractFee + " WHERE insuranceId = '" + insuranceId + "'";
		if (this.execute(str)) return true;
		else return false;
	}
	
	public boolean delete(String insuranceId) {
		String str = "DELETE FROM insurance WHERE insuranceId = '" + insuranceId + "'";
		if (this.execute(str)) return true;
		else return false;
	}

	public Insurance selectTypeInsurance(Insurance insurance) {
		return null;
	}

}
