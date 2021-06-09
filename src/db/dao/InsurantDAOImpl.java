package db.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import business.customer.Insurant;
import db.DBConnector;
import global.Constants.eFamilyMedicalDisease;
import global.Constants.eFamilyMedicalRelationship;
import global.Constants.eGender;
import global.Constants.eJob;
import global.Constants.eRankOfCar;
import global.Constants.eRiskOfTripCountry;
import global.Constants.eTypeOfCar;
import global.Constants.eUsageOfStructure;

public class InsurantDAOImpl extends DBConnector implements InsurantDAO {
	public boolean insert(Insurant insurant) {
		String str = "INSERT INTO insurant(insurantId, customerId , name, address, phoneNumber, age, accidentHistory, postedPriceOfStructure, usageOfStructure, gender, job, typeOfCar, rankOfCar, riskOfTripCountry, familyMedicalDisease, familyMedicalRelationship) values('"
				+ insurant.getInsurantId() + "','" + insurant.getCustomerId() + "','" + insurant.getName() + "','" + insurant.getAddress() + "','"
				+ insurant.getPhoneNumber() + "','" + insurant.getAge() + "','" + insurant.getAccidentHistory() + "','"
				+ insurant.getPostedPriceOfStructure() + "','" + insurant.getUsageOfStructure().getNum() + "','"
				+ insurant.getGender().getNum() + "','" + insurant.getJob().getNum() + "','"
				+ insurant.getTypeOfCar().getNum() + "','" + insurant.getRankOfCar().getNum()+ "','"
				+ insurant.getRiskOfTripCountry().getNum() + "','" + insurant.getFamilyMedicalDisease().getNum() + "','" + insurant.getFamilyMedicalRelationship().getNum() + "')";
		if (this.execute(str))
			return true;
		else
			return false;
	}

	public ArrayList<Insurant> select() {
		ArrayList<Insurant> arrayList = new ArrayList<Insurant>();
		
		String sql = "SELECT * FROM insurant";
		this.read(sql);
		try {
			while (rs.next()) {
				Insurant insurant = new Insurant();
				insurant.setInsurantId(rs.getString("insurantId"));
				insurant.setCustomerId(rs.getString("customerId"));
				insurant.setName(rs.getString("name"));
				insurant.setAddress(rs.getString("address"));
				insurant.setPhoneNumber(rs.getString("phoneNumber"));
				insurant.setAge(rs.getInt("age"));
				insurant.setAccidentHistory(rs.getInt("accidentHistory"));
				insurant.setPostedPriceOfStructure(rs.getLong("postedPriceOfStructure"));
				
				for(eUsageOfStructure usageOfStructure : eUsageOfStructure.values()) if(usageOfStructure.getNum() == rs.getInt("usageOfStructure")) insurant.setUsageOfStructure(usageOfStructure);
				for(eGender gender : eGender.values()) if(gender.getNum() == rs.getInt("gender")) insurant.setGender(gender);
				for(eJob job : eJob.values()) if(job.getNum() == rs.getInt("job")) insurant.setJob(job);
				for(eTypeOfCar typeOfCar : eTypeOfCar.values()) if(typeOfCar.getNum() == rs.getInt("typeOfCar")) insurant.setTypeOfCar(typeOfCar);
				for(eRankOfCar rankOfCar : eRankOfCar.values()) if(rankOfCar.getNum() == rs.getInt("rankOfCar")) insurant.setRankOfCar(rankOfCar);
				for(eRiskOfTripCountry riskOfTripCountry : eRiskOfTripCountry.values()) if(riskOfTripCountry.getNum() == rs.getInt("riskOfTripCountry")) insurant.setRiskOfTripCountry(riskOfTripCountry);
				for(eFamilyMedicalDisease familyMedicalDisease : eFamilyMedicalDisease.values()) if(familyMedicalDisease.getNum() == rs.getInt("familyMedicalDisease")) insurant.setFamilyMedicalDisease(familyMedicalDisease);
				for(eFamilyMedicalRelationship familyMedicalRelationship : eFamilyMedicalRelationship.values()) if(familyMedicalRelationship.getNum() == rs.getInt("familyMedicalRelationship")) insurant.setFamilyMedicalRelationship(familyMedicalRelationship);

				arrayList.add(insurant);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arrayList;
	}
	
	public Insurant selectByCustomerId(String customerId) {
		
		String sql = "SELECT * FROM insurant WHERE customerId = '" + customerId + "'";
		this.read(sql);
		try {
			while (rs.next()) {
				Insurant insurant = new Insurant();
				insurant.setInsurantId(rs.getString("insurantId"));
				insurant.setCustomerId(rs.getString("customerId"));
				insurant.setName(rs.getString("name"));
				insurant.setAddress(rs.getString("address"));
				insurant.setPhoneNumber(rs.getString("phoneNumber"));
				insurant.setAge(rs.getInt("age"));
				insurant.setAccidentHistory(rs.getInt("accidentHistory"));
				insurant.setPostedPriceOfStructure(rs.getLong("postedPriceOfStructure"));
				
				for(eUsageOfStructure usageOfStructure : eUsageOfStructure.values()) if(usageOfStructure.getNum() == rs.getInt("usageOfStructure")) insurant.setUsageOfStructure(usageOfStructure);
				for(eGender gender : eGender.values()) if(gender.getNum() == rs.getInt("gender")) insurant.setGender(gender);
				for(eJob job : eJob.values()) if(job.getNum() == rs.getInt("job")) insurant.setJob(job);
				for(eTypeOfCar typeOfCar : eTypeOfCar.values()) if(typeOfCar.getNum() == rs.getInt("typeOfCar")) insurant.setTypeOfCar(typeOfCar);
				for(eRankOfCar rankOfCar : eRankOfCar.values()) if(rankOfCar.getNum() == rs.getInt("rankOfCar")) insurant.setRankOfCar(rankOfCar);
				for(eRiskOfTripCountry riskOfTripCountry : eRiskOfTripCountry.values()) if(riskOfTripCountry.getNum() == rs.getInt("riskOfTripCountry")) insurant.setRiskOfTripCountry(riskOfTripCountry);
				for(eFamilyMedicalDisease familyMedicalDisease : eFamilyMedicalDisease.values()) if(familyMedicalDisease.getNum() == rs.getInt("familyMedicalDisease")) insurant.setFamilyMedicalDisease(familyMedicalDisease);
				for(eFamilyMedicalRelationship familyMedicalRelationship : eFamilyMedicalRelationship.values()) if(familyMedicalRelationship.getNum() == rs.getInt("familyMedicalRelationship")) insurant.setFamilyMedicalRelationship(familyMedicalRelationship);

				return insurant;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean updateName(String insurantId, String name) {
		String str = "UPDATE insurant set name = " + name + " WHERE insurantId = " + insurantId;
		if(this.execute(str)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean updateAddress(String insurantId, String address) {
		String str = "UPDATE insurant set address = " + address + " WHERE insurantId = " + insurantId;
		if(this.execute(str)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean updatePhoneNumber(String insurantId, String phoneNumber) {
		String str = "UPDATE insurant set phoneNumber = " + phoneNumber + " WHERE insurantId = " + insurantId;
		if(this.execute(str)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean delete(String insurantId) {
		String str = "DELETE FROM insurant WHERE insurantId = " + insurantId;
		if(this.execute(str)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean updateAge(String insurantId, int age) {
		String str = "UPDATE insurant set age = " + age + " WHERE insurantId = " + insurantId;
		if(this.execute(str)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean updateAccidentHistory(String insurantId, int accidentHistory) {
		String str = "UPDATE insurant set accidentHistory = " + accidentHistory + " WHERE insurantId = " + insurantId;
		if(this.execute(str)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean updatePostedPriceOfStructure(String insurantId, long postedPriceOfStructure) {
		String str = "UPDATE insurant set postedPriceOfStructure = " + postedPriceOfStructure + " WHERE insurantId = " + insurantId;
		if(this.execute(str)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean updateUsageOfStructure(String insurantId, int usageOfStructure) {
		String str = "UPDATE insurant set usageOfStructure = " + usageOfStructure + " WHERE insurantId = " + insurantId;
		if(this.execute(str)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean updateGender(String insurantId, int gender) {
		String str = "UPDATE insurant set gender = " + gender + " WHERE insurantId = " + insurantId;
		if(this.execute(str)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean updateJob(String insurantId, int job) {
		String str = "UPDATE insurant set job = " + job + " WHERE insurantId = " + insurantId;
		if(this.execute(str)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean updateTypeOfCar(String insurantId, int typeOfCar) {
		String str = "UPDATE insurant set typeOfCar = " + typeOfCar + " WHERE insurantId = " + insurantId;
		if(this.execute(str)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean updateRankOfCar(String insurantId, int rankOfCar) {
		String str = "UPDATE insurant set rankOfCar = " + rankOfCar + " WHERE insurantId = " + insurantId;
		if(this.execute(str)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean updateRiskOfTripCountry(String insurantId, int riskOfTripCountry) {
		String str = "UPDATE insurant set riskOfTripCountry = " + riskOfTripCountry + " WHERE insurantId = " + insurantId;
		if(this.execute(str)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Insurant selectInsurant(String insurantId) {
		Insurant insurant = new Insurant();
		String sql = "SELECT * FROM insurant WHERE insurantId = '" + insurantId +"'";
		this.read(sql);
		try {
			while (rs.next()) {
				insurant.setInsurantId(rs.getString("insurantId"));
				insurant.setCustomerId(rs.getString("customerId"));
				insurant.setName(rs.getString("name"));
				insurant.setAddress(rs.getString("address"));
				insurant.setPhoneNumber(rs.getString("phoneNumber"));
				insurant.setAge(rs.getInt("age"));
				insurant.setAccidentHistory(rs.getInt("accidentHistory"));
				insurant.setPostedPriceOfStructure(rs.getLong("postedPriceOfStructure"));
				int num = (rs.getInt("usageOfStructure"));
				for(eUsageOfStructure usageOfStructure : eUsageOfStructure.values()) {
					if(usageOfStructure.getNum() == num) {
						insurant.setUsageOfStructure(usageOfStructure);
					}
				}
				
				num = (rs.getInt("gender"));
				for(eGender gender : eGender.values()) {
					if(gender.getNum() == num) {
						insurant.setGender(gender);
					}
				}
				
				num = (rs.getInt("job"));
				for(eJob job : eJob.values()) {
					if(job.getNum() == num) {
						insurant.setJob(job);
					}
				}
				
				num = (rs.getInt("typeOfCar"));
				for(eTypeOfCar typeOfCar : eTypeOfCar.values()) {
					if(typeOfCar.getNum() == num) {
						insurant.setTypeOfCar(typeOfCar);
					}
				}
				
				num = (rs.getInt("rankOfCar"));
				for(eRankOfCar rankOfCar : eRankOfCar.values()) {
					if(rankOfCar.getNum() == num) {
						insurant.setRankOfCar(rankOfCar);
					}
				}
				
				num = (rs.getInt("riskOfTripCountry"));
				for(eRiskOfTripCountry riskOfTripCountry : eRiskOfTripCountry.values()) {
					if(riskOfTripCountry.getNum() == num) {
						insurant.setRiskOfTripCountry(riskOfTripCountry);
					}
				}
				
				num = (rs.getInt("familyMedicalDisease"));
				for(eFamilyMedicalDisease familyMedicalDisease : eFamilyMedicalDisease.values()) {
					if(familyMedicalDisease.getNum() == num) {
						insurant.setFamilyMedicalDisease(familyMedicalDisease);
					}
				}
				
				num = (rs.getInt("familyMedicalRelationship"));
				for(eFamilyMedicalRelationship familyMedicalRelationship : eFamilyMedicalRelationship.values()) {
					if(familyMedicalRelationship.getNum() == num) {
						insurant.setFamilyMedicalRelationship(familyMedicalRelationship);
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return insurant;
	}
}
