package customer;

import java.sql.SQLException;
import java.util.ArrayList;

import global.Constants.eGender;
import global.Constants.eJob;
import global.Constants.eRankOfCar;
import global.Constants.eRiskOfTripCountry;
import global.Constants.eTypeOfCar;
import global.Constants.eUsageOfStructure;
import main.DBConnector;

public class InsurantDAOImpl extends DBConnector implements InsurantDAO {
	public boolean insert(Insurant insurant) {
		String str = "INSERT INTO insurant(insurantId, name, address, phoneNumber, age, accidentHistory, postedPriceOfStructure, usageOfStructure, gender, job, typeOfCar, rankOfCar, riskOfTripCountry) values('"
				+ insurant.getInsurantId() + "','" + insurant.getName() + "','" + insurant.getAddress() + "','"
				+ insurant.getPhoneNumber() + "','" + insurant.getAge() + "','" + insurant.getAccidentHistory() + "','"
				+ insurant.getPostedPriceOfStructure() + "','" + insurant.getUsageOfStructure().getNum() + "','"
				+ insurant.getGender().getNum() + "','" + insurant.getJob().getNum() + "','"
				+ insurant.getTypeOfCar().getNum() + "','" + insurant.getRankOfCar().getNum()+ "','"
				+ insurant.getRiskOfTripCountry().getNum() + "')";
		if (this.execute(str))
			return true;
		else
			return false;
	}

	public ArrayList<Insurant> select() {
		ArrayList<Insurant> arrayList = new ArrayList<Insurant>();

		String sql = "SELECT * FROM Customer";
		try {
			while (rs.next()) {
				Insurant insurant = new Insurant();
				insurant.setInsurantId(rs.getString("insurantId"));
				insurant.setName(rs.getString("name"));
				insurant.setAddress(rs.getString("address"));
				insurant.setPhoneNumber(rs.getString("phoneNumber"));
				insurant.setAge(rs.getInt("age"));
				insurant.setAccidentHistory(rs.getInt("accidentHistory"));
				insurant.setPostedPriceOfStructure(rs.getLong("postedPriceOfStructure"));
				
				// 여기다가 enum값을 넣는거 구현
				switch(rs.getInt("usageOfStructure")) {
				case 1:
					insurant.setUsageOfStructure(eUsageOfStructure.house);
					break;
				case 2:
					insurant.setUsageOfStructure(eUsageOfStructure.study);
					break;
				case 3:
					insurant.setUsageOfStructure(eUsageOfStructure.factory);
					break;
				case 4:
					insurant.setUsageOfStructure(eUsageOfStructure.warehouse);
					break;
				case 5:
					insurant.setUsageOfStructure(eUsageOfStructure.office);
					break;
				case 6:
					insurant.setUsageOfStructure(eUsageOfStructure.publicFacility);
					break;
				}
				
				switch(rs.getInt("gender")) {
				case 1:
					insurant.setGender(eGender.male);
					break;
				case 2:
					insurant.setGender(eGender.female);
					break;
				case 3:
					insurant.setGender(eGender.both);
					break;
				}
				
				switch(rs.getInt("job")) {
				case 1:
					insurant.setJob(eJob.officeWorker);
					break;
				case 2:
					insurant.setJob(eJob.driver);
					break;
				case 3:
					insurant.setJob(eJob.factoryWorker);
					break;
				case 4:
					insurant.setJob(eJob.student);
					break;
				case 5:
					insurant.setJob(eJob.teacher);
					break;
				case 6:
					insurant.setJob(eJob.soldier);
					break;
				case 7:
					insurant.setJob(eJob.etc);
					break;
				}
				
				switch(rs.getInt("typeOfCar")) {
				case 0:
					insurant.setTypeOfCar(null);
					break;
				case 1:
					insurant.setTypeOfCar(eTypeOfCar.bus);
					break;
				case 2:
					insurant.setTypeOfCar(eTypeOfCar.van);
					break;
				case 3:
					insurant.setTypeOfCar(eTypeOfCar.suv);
					break;
				case 4:
					insurant.setTypeOfCar(eTypeOfCar.foreign);
					break;
				case 5:
					insurant.setTypeOfCar(eTypeOfCar.etc);
					break;
				}
				
				switch(rs.getInt("rankOfCar")) {
				case 0:
					insurant.setRankOfCar(null);
					break;
				case 1:
					insurant.setRankOfCar(eRankOfCar.Luxury);
					break;
				case 2:
					insurant.setRankOfCar(eRankOfCar.high);
					break;
				case 3:
					insurant.setRankOfCar(eRankOfCar.middle);
					break;
				case 4:
					insurant.setRankOfCar(eRankOfCar.low);
					break;
				}
				
				switch(rs.getInt("riskOfTripCountry")) {
				case 1:
					insurant.setRiskOfTripCountry(eRiskOfTripCountry.safe);
					break;
				case 2:
					insurant.setRiskOfTripCountry(eRiskOfTripCountry.first);
					break;
				case 3:
					insurant.setRiskOfTripCountry(eRiskOfTripCountry.safe);
					break;
				case 4:
					insurant.setRiskOfTripCountry(eRiskOfTripCountry.third);
					break;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arrayList;
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
}
