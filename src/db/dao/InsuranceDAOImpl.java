package db.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import business.contract.Contract;
import business.insurance.ActualCostInsurance;
import business.insurance.CancerInsurance;
import business.insurance.DentalInsurance;
import business.insurance.DriverInsurance;
import business.insurance.FireInsurance;
import business.insurance.Insurance;
import business.insurance.TripInsurance;
import db.DBConnector;
import global.Constants;
import global.Constants.eGender;
import global.Constants.eInsuranceType;

public class InsuranceDAOImpl extends DBConnector implements InsuranceDAO{
	public boolean insert(Insurance insurance) {
		String str = "INSERT INTO insurance(insuranceId, NAME, TYPE, gender, basicFee, specialContractFee, warrantyPeriod,"
				+ " rateOfAge0, rateOfAge1, rateOfAge2, rateOfAge3, rateOfAge4, rateOfAge5, rateOfAge6,"
				+ " rateOfGender0, rateOfGender1, rateOfJob0, rateOfJob1, rateOfJob2,"
				+ " rateOfJob3, rateOfJob4, rateOfJob5, rateOfJob6, confirmedStatus, specialContract) values('"
				+ insurance.getInsuranceId() + "','" + insurance.getName() + "'," + insurance.getType().getNum() + ","
				+ insurance.getGender().getNum() + "," + insurance.getBasicFee() + ","
				+ insurance.getSpecialContractFee() + "," + insurance.getWarrantyPeriod() + ",";
		for (double rate : insurance.getRateOfAge()) str += rate + ",";
		for (double rate : insurance.getRateOfGender()) str += rate + ",";
		for (double rate : insurance.getRateOfJob()) {
			if (rate == 0) continue;
			str += rate + ",";
		}
		str += insurance.isConfirmedStatus() + "," + insurance.isSpecialContract() + ")";
		
		

		if(this.execute(str)) {
			switch(insurance.getType()) {
			case actualCostInsurance: if(insertActualCostInsurance(insurance)) return true;
			case cancerInsurance: if(insertCancerInsurance(insurance)) return true;
			case dentalInsurance: if(insertDentalInsurance(insurance)) return true;
			case driverInsurance: if(insertDriverInsurance(insurance)) return true;
			case fireInsurance: if(insertFireInsurance(insurance)) return true;
			case tripInsurance: if(insertTripInsurance(insurance)) return true;
			default : return false;
			}
		}else {
			return false;
		}
	}
	
	public boolean insertActualCostInsurance(Insurance insurance) {
		ActualCostInsurance newInsurance = (ActualCostInsurance) insurance;
		String str = "INSERT INTO actualCostInsurance(insuranceId, selfBurdenRate) values('"
				+ newInsurance.getInsuranceId() + "'," + newInsurance.getSelfBurdenRate() + ")";
		if (this.execute(str)) return true;
		else return false;
	}

	public boolean insertCancerInsurance(Insurance insurance) {
		CancerInsurance newInsurance = (CancerInsurance)insurance;
		
		String str = "INSERT INTO cancerInsurance(insuranceId, rateOfFamilyMedicalDisease0, rateOfFamilyMedicalDisease1, rateOfFamilyMedicalDisease2,"
				+ " rateOfFamilyMedicalDisease3, rateOfFamilyMedicalDisease4, rateOfFamilyMedicalRelationship0, rateOfFamilyMedicalRelationship1,"
				+ " rateOfFamilyMedicalRelationship2, rateOfFamilyMedicalRelationship3"
				+ " ) values('" + newInsurance.getInsuranceId() + "'";
		for (double rate : newInsurance.getRateOfFamilyMedicalDisease()) str += "," + rate;
		for (double rate : newInsurance.getRateOfFamilyMedicalRelationship()) str += "," + rate;
		str += ")";

		System.out.println(str);
		if (this.execute(str)) return true;
		else return false;
	}

	public boolean insertDentalInsurance(Insurance insurance) {
		DentalInsurance newInsurance = (DentalInsurance) insurance;

		String str = "INSERT INTO dentalInsurance(insuranceId, annualLimitCount) values('"
				+ newInsurance.getInsuranceId() + "'," + newInsurance.getAnnualLimitCount()+")";

		if (this.execute(str))
			return true;
		else return false;
	}

	public boolean insertDriverInsurance(Insurance insurance) {
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

	public boolean insertFireInsurance(Insurance insurance) {
		FireInsurance newInsurance = (FireInsurance)insurance;
		
		String str = "INSERT INTO fireInsurance(insuranceId, rateOfPostedPrice0, rateOfPostedPrice1, rateOfPostedPrice2, rateOfPostedPrice3,"
				+ " rateOfPostedPrice4, rateOfStructureUsage0, rateOfStructureUsage1, rateOfStructureUsage2, rateOfStructureUsage3,"
				+ " rateOfStructureUsage4, rateOfStructureUsage5) values('" + newInsurance.getInsuranceId() + "'";
		for (double rate : newInsurance.getRateOfPostedPrice()) {
			str += "," + rate;
		}
		for (double rate : newInsurance.getRateOfStructureUsage()) {
			if(rate == 0) {
				continue;
			}
			str += "," + rate;
		}
		str += ")";
		if (this.execute(str)) return true;
		else return false;
	}

	public boolean insertTripInsurance(Insurance insurance) {
		TripInsurance newInsurance = (TripInsurance)insurance;
		
		String str = "INSERT INTO tripInsurance(insuranceId, rateOfCountryRisk0, rateOfCountryRisk1, rateOfCountryRisk2, rateOfCountryRisk3"
				+ ") values('" + newInsurance.getInsuranceId() + "'";
		for (double rate : newInsurance.getRateOfCountryRank()) str += "," + rate;
		str += ")";
		if (this.execute(str)) return true;
		else return false;
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
						insurance.setDel(rs.getBoolean("del"));
						arrayList.add(insurance);
					}
				}
			}
			for(Insurance insurance : arrayList) {
				switch(insurance.getType()) {
				case actualCostInsurance: insurance = selectActualCostInsurance(insurance); break;
				case cancerInsurance: insurance = selectCancerInsurance(insurance); break;
				case dentalInsurance: insurance = selectDentalInsurance(insurance); break;
				case driverInsurance: insurance = selectDriverInsurance(insurance); break;
				case fireInsurance: insurance = selectFireInsurance(insurance); break;
				case tripInsurance: insurance = selectTripInsurance(insurance); break;
				default : break;
				}
			}
			for(Insurance insurance : arrayList) {
				insurance.setGuaranteePlanList(guaranteePlanDAO.selectById(insurance.getInsuranceId()));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arrayList;
	}
	
	public ArrayList<String> selectInsuranceId(){
		ArrayList<String> arrayList = new ArrayList<String>();
		String sql = "SELECT insuranceId FROM insurance;";
		
		this.read(sql);
		try {
			while(rs.next()) {
				arrayList.add(rs.getString("insuranceId"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arrayList;
	}
	
	public ArrayList<Insurance> selectForConfirm(){
		ArrayList<Insurance> arrayList = new ArrayList<Insurance>();
		String sql = "SELECT insuranceId, confirmedStatus, del, type FROM insurance;";
		
		this.read(sql);
		try {
			while (rs.next()) {
				int input = rs.getInt("type");
				for (eInsuranceType insuranceType : eInsuranceType.values()) {
					if (insuranceType.getNum() == input) {
						Insurance insurance = insuranceType.getSelectedInsurance().newInstance();
						insurance.setInsuranceId(rs.getString("insuranceId"));
						insurance.setConfirmedStatus(rs.getBoolean("confirmedStatus"));
						insurance.setDel(rs.getBoolean("del"));
						for (eInsuranceType type : eInsuranceType.values()) if (type.getNum() == input) insurance.setType(type);
						arrayList.add(insurance);
					}

				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arrayList;
	}
	
	public ArrayList<Insurance> selectSimpleData(){
		ArrayList<Insurance> arrayList = new ArrayList<Insurance>();
		String sql = "SELECT insuranceId, confirmedStatus, del, type, name, basicFee FROM insurance;";
		
		this.read(sql);
		try {
			while(rs.next()) {
				int input = rs.getInt("type");
				for (eInsuranceType insuranceType : eInsuranceType.values()) {
					if (insuranceType.getNum() == input) {
						Insurance insurance = insuranceType.getSelectedInsurance().newInstance();
						insurance.setInsuranceId(rs.getString("insuranceId"));
						insurance.setConfirmedStatus(rs.getBoolean("confirmedStatus"));
						insurance.setDel(rs.getBoolean("del"));
						insurance.setName(rs.getString("name"));
						insurance.setBasicFee(rs.getInt("basicFee"));
						for (eInsuranceType type : eInsuranceType.values()) if (type.getNum() == input) insurance.setType(type);
						arrayList.add(insurance);
					}

				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arrayList;
	}
	
	public Insurance selectInsurance(String insuranceId) {
		String sql = "SELECT * FROM insurance WHERE insuranceId = '" + insuranceId + "'";
		GuaranteePlanDAO guaranteePlanDAO = new GuaranteePlanDAOImpl();
		
		this.read(sql);
		try {
			if (rs.next()) {
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
						for (int i = 0; i < insurance.getRateOfAge().length; i++) rateOfAge[i] = rs.getDouble("rateOfAge" + i);
						double[] rateOfGender = new double[insurance.getRateOfGender().length];
						for (int i = 0; i < insurance.getRateOfGender().length; i++) rateOfGender[i] = rs.getDouble("rateOfGender" + i);
						double[] rateOfJob = new double[insurance.getRateOfJob().length];
						for (int i = 0; i < insurance.getRateOfJob().length; i++) rateOfJob[i] = rs.getDouble("rateOfJob" + i);
						insurance.setRateOfAge(rateOfAge);
						insurance.setRateOfGender(rateOfGender);
						insurance.setRateOfJob(rateOfJob);
						insurance.setConfirmedStatus(rs.getBoolean("confirmedStatus"));
						insurance.setSpecialContract(rs.getBoolean("specialContract"));
						insurance.setDel(rs.getBoolean("del"));

						switch(insurance.getType()) {
						case actualCostInsurance: insurance = selectActualCostInsurance(insurance); break;
						case cancerInsurance: insurance = selectCancerInsurance(insurance); break;
						case dentalInsurance: insurance = selectDentalInsurance(insurance); break;
						case driverInsurance: insurance = selectDriverInsurance(insurance); break;
						case fireInsurance: insurance = selectFireInsurance(insurance); break;
						case tripInsurance: insurance = selectTripInsurance(insurance); break;
						default : break;
						}
						

						insurance.setGuaranteePlanList(guaranteePlanDAO.selectById(insurance.getInsuranceId()));
						return insurance;
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Insurance selectActualCostInsurance(Insurance insurance) {
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

	public Insurance selectCancerInsurance(Insurance insurance) {
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

	public Insurance selectDentalInsurance(Insurance insurance) {
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

	public Insurance selectDriverInsurance(Insurance insurance) {
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

	public Insurance selectFireInsurance(Insurance insurance) {
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

	public Insurance selectTripInsurance(Insurance insurance) {
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
	
	

	public boolean updateConfirmedStatus(String insuranceId, boolean confirmedStatus) {
		String str = "UPDATE insurance SET confirmedStatus = "+confirmedStatus+" WHERE insuranceId = '" + insuranceId + "'";
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
	
	public boolean updateDel(String insuranceId, boolean del) {
		String sql = "UPDATE insurance SET del = "+del+" WHERE insuranceID = '"+insuranceId+"';";
		return super.execute(sql);
	}
	
	public boolean delete(String insuranceId) {
		String str = "DELETE FROM insurance WHERE insuranceId = '" + insuranceId + "'";
		if (this.execute(str)) return true;
		else return false;
	}

	public boolean deleteInsuranceByTime() {
		String sql = "";
		ContractDAO contractDAO = new ContractDAOImpl();
		InsuranceDAO insuranceDAO = new InsuranceDAOImpl();
		for (String insuranceId: insuranceDAO.selectInsuranceId()) {
			boolean isOver = true;
			boolean isContract = false;
			ArrayList<Contract> tmpContract = new ArrayList<Contract>();
			for (Contract contract: contractDAO.selectForTime()) {
				if (insuranceId.equals(contract.getInsuranceId())) {
					isContract = true;
					tmpContract.add(contract);
					break;
				}
			}
			if (!isContract) {
				sql = "DELETE FROM insurance WHERE del = true AND insuranceId = "+insuranceId+";";
				super.execute(sql);
			} else {
				for (Contract contract: tmpContract) {
					if (contract.getLifespan() > Constants.thisYear*100+Constants.thisMonth) {
						isOver = false;
					}
				}
				if (isOver) {
					sql = "DELETE FROM insurance WHERE del = true AND insuranceId = "+insuranceId+";";
					super.execute(sql);
				} 
			}
		}
	
		return true;
	}
	public Insurance selectTypeInsurance(Insurance insurance) {
		return null;
	}

	//asdasdasdasdasd


	

	
}
