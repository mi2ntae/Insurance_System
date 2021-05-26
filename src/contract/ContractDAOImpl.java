package contract;

import java.sql.SQLException;
import java.util.ArrayList;

import main.DBConnector;

public class ContractDAOImpl extends DBConnector implements ContractDAO{
	public boolean insert(Contract contract) {
		String str = "INSERT INTO contract(contractId, insurantId, insuranceId, customerId, salespersonId, effectiveness, special, lifespan"
				+ ", fee, unpaidPeriod) values('"+contract.getContractId()+"', '"+contract.getInsurantId()+"', '"+contract.getInsuranceId()
				+"', '"+contract.getCustomerId()+"', '"+contract.getSalespersonId()+"', false, "+contract.isSpecial()+", "+contract.getLifespan()
				+", "+contract.getFee()+", "+contract.getUnpaidPeriod()+");";
		return this.execute(str);
	}
	
	public ArrayList<Contract> select() {
		ArrayList<Contract> contractList = new ArrayList<Contract>();

		String sql = "SELECT * FROM contract";
		this.read(sql);
		try {
			while (rs.next()) {
				Contract contract = new Contract();
				contract.setContractId(rs.getString("contractId"));
				contract.setInsurantId(rs.getString("insurantId"));
				contract.setInsuranceId(rs.getString("insuranceId"));
				contract.setCustomerId(rs.getString("customerId"));
				contract.setSalespersonId(rs.getString("salespersonId"));
				contract.setEffectiveness(rs.getBoolean("effectiveness"));
				contract.setSpecial(rs.getBoolean("special"));
				contract.setLifespan(rs.getInt("lifespan"));
				contract.setFee(rs.getInt("fee"));
				contract.setUnpaidPeriod(rs.getInt("unpaidPeriod"));
				contractList.add(contract);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return contractList;
	}

	public Contract selectContract(String contractId) {
		String sql = "SELECT * FROM contract where contractId = '"+contractId+"';";
		this.read(sql);
		
		Contract contract = new Contract();
		try {
			while (rs.next()) {
				contract.setContractId(rs.getString("contractId"));
				contract.setInsurantId(rs.getString("insurantId"));
				contract.setInsuranceId(rs.getString("insuranceId"));
				contract.setCustomerId(rs.getString("customerId"));
				contract.setSalespersonId(rs.getString("salespersonId"));
				contract.setEffectiveness(rs.getBoolean("effectiveness"));
				contract.setSpecial(rs.getBoolean("special"));
				contract.setLifespan(rs.getInt("lifespan"));
				contract.setFee(rs.getInt("fee"));
				contract.setUnpaidPeriod(rs.getInt("unpaidPeriod"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return contract;
	}
	
	public boolean updateFee(String contractId, int fee) {
		String sql = "UPDATE contract SET fee = "+fee+" WHERE contractId = '"+contractId+"';";
		return this.execute(sql);
	}

	public boolean updateEffectiveness(String contractId, boolean effectiveness) {
		String sql = "UPDATE contract SET effectiveness = "+effectiveness+" WHERE contractId = '"+contractId+"';";
		return this.execute(sql);
	}
	
	public boolean delete(String contractId) {
		String sql = "DELETE contract WHERE contractId = '"+contractId+"';";
		return this.execute(sql);
	}
}
