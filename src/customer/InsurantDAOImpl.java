package customer;

import java.sql.SQLException;
import java.util.ArrayList;

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
				
				
				
				insurant.setInsurantId(rs.getString("insurantId"));
				insurant.setInsurantId(rs.getString("insurantId"));
				insurant.setInsurantId(rs.getString("insurantId"));
				insurant.setInsurantId(rs.getString("insurantId"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return arrayList;
	}
}
