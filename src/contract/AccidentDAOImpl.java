package contract;

import java.sql.SQLException;
import java.util.ArrayList;

import main.DBConnector;

public class AccidentDAOImpl extends DBConnector implements AccidentDAO{
	public boolean insert(Accident accident) {
		String sql = "INSERT INTO accident(accidentId, contractId, content, compensation, damageCost, handlingStatus) values('"
					+accident.getAccidentId()+"', '"+accident.getContractId()+"', '"+accident.getContent()+"', "+accident.getCompensation()
					+", "+accident.getDamageCost()+", false);";
		return this.execute(sql);
	}
	
	public ArrayList<Accident> select() {
		ArrayList<Accident> accidentList = new ArrayList<Accident>();

		String sql = "SELECT * FROM accident";
		this.read(sql);
		try {
			while (rs.next()) {
				Accident accident = new Accident();
				accident.setAccidentId(rs.getString("accidentId"));
				accident.setContractId(rs.getString("contractId"));
				accident.setContent(rs.getString("content"));
				accident.setCompensation(rs.getInt("compensation"));
				accident.setDamageCost(rs.getInt("damageCost"));
				accident.setHandlingStatus(rs.getBoolean("handlingStatus"));
				accidentList.add(accident);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return accidentList;
	}

	public Accident selectAccident(String accidentId) {
		String sql = "SELECT * FROM accident where accidentId = '"+accidentId+"';";
		this.read(sql);
		
		Accident accident = new Accident();
		try {
			while (rs.next()) {
				accident.setAccidentId(rs.getString("accidentId"));
				accident.setContractId(rs.getString("contractId"));
				accident.setContent(rs.getString("content"));
				accident.setCompensation(rs.getInt("compensation"));
				accident.setDamageCost(rs.getInt("damageCost"));
				accident.setHandlingStatus(rs.getBoolean("handlingStatus"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return accident;
	}
	
	public boolean updateCompensation(String accidentId, int compensation) {
		String sql = "UPDATE accident SET compensation = "+compensation+" WHERE accidentId = '"+accidentId+"';";
		return this.execute(sql);
	}

	public boolean updateHandlingStatus(String accidentId, boolean handlingStatus) {
		String sql = "UPDATE accident SET handlingStatus = "+handlingStatus+" WHERE accidentId = '"+accidentId+"';";
		return this.execute(sql);
	}
	
	public boolean delete(String accidentId) {
		String sql = "DELETE accident WHERE accidentId = '"+accidentId+"';";
		return this.execute(sql);
	}

}
