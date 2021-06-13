package db.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import business.contract.Contract;
import db.DBConnector;

public class ContractDAOImpl extends DBConnector implements ContractDAO{
	public boolean insert(Contract contract) {
		boolean isOkay = false;
		String str = "INSERT INTO contract(contractId, insurantId, insuranceId, effectiveness, special, lifespan"
				+ ", fee, unpaidPeriod) values('"+contract.getContractId()+"', '"+contract.getInsurantId()+"', '"+contract.getInsuranceId()
				+"', false, "+contract.isSpecial()+", "+contract.getLifespan()
				+", "+contract.getFee()+", "+contract.getUnpaidPeriod()+");";
		isOkay = super.execute(str);
		str = "INSERT INTO payHistory values('"+contract.getContractId()+"', false, false, false, false, false, false, false, false, false, false, false, false);";
		if (isOkay && this.execute(str)) {
			return true;
		} else {
			return false;
		}
	}
	
	public ArrayList<Contract> select() {
		ArrayList<Contract> contractList = new ArrayList<Contract>();
		AccidentDAO accidentDAO = new AccidentDAOImpl();
		
		String sql = "SELECT * FROM contract";
		this.read(sql);
		try {
			while (rs.next()) {
				Contract contract = new Contract();
				contract.setContractId(rs.getString("contractId"));
				contract.setInsurantId(rs.getString("insurantId"));
				contract.setInsuranceId(rs.getString("insuranceId"));
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
		
		for (Contract contract: contractList) {
			contract.setAccidentList(accidentDAO.selectByContractId(contract.getContractId()));
		}
		
		for (Contract contract: contractList) {
			contract.setPayHistory(this.selectPayHistory(contract.getContractId()));
		}
		return contractList;
	}

	public ArrayList<Contract> selectForTime() {
		ArrayList<Contract> contractList = new ArrayList<Contract>();
		
		String sql = "SELECT contractId, insuranceId, effectiveness, lifespan, unpaidPeriod FROM contract";
		this.read(sql);
		try {
			while (rs.next()) {
				Contract contract = new Contract();
				contract.setContractId(rs.getString("contractId"));
				contract.setInsuranceId(rs.getString("insuranceId"));
				contract.setEffectiveness(rs.getBoolean("effectiveness"));
				contract.setLifespan(rs.getInt("lifespan"));
				contract.setUnpaidPeriod(rs.getInt("unpaidPeriod"));
				contractList.add(contract);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return contractList;
	}
	
	public ArrayList<String> selectContractId() {
		ArrayList<String> contractList = new ArrayList<String>();
		
		String sql = "SELECT contractId FROM contract";
		this.read(sql);
		try {
			while (rs.next()) {
				contractList.add(rs.getString("contractId"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return contractList;
	}

	public Contract selectContract(String contractId) {
		AccidentDAO accidentDAO = new AccidentDAOImpl();
		String sql = "SELECT * FROM contract where contractId = '"+contractId+"';";
		this.read(sql);
		boolean flag = false;
		Contract contract = new Contract();
		try {
			while (rs.next()) {
				contract.setContractId(rs.getString("contractId"));
				contract.setInsurantId(rs.getString("insurantId"));
				contract.setInsuranceId(rs.getString("insuranceId"));
				contract.setEffectiveness(rs.getBoolean("effectiveness"));
				contract.setSpecial(rs.getBoolean("special"));
				contract.setLifespan(rs.getInt("lifespan"));
				contract.setFee(rs.getInt("fee"));
				contract.setUnpaidPeriod(rs.getInt("unpaidPeriod"));
				flag = true;
			}
			if(flag) {
				contract.setAccidentList(accidentDAO.selectByContractId(contract.getContractId()));
				contract.setPayHistory(this.selectPayHistory(contract.getContractId()));
				return contract;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Contract> selectIds(){
		String sql = "SELECT contractId, insuranceId, insurantId, effectiveness, unpaidPeriod FROM contract;";
		ArrayList<Contract> contractList = new ArrayList<Contract>();

		this.read(sql);
		try {
			while (rs.next()) {
				Contract contract = new Contract();
				contract.setContractId(rs.getString("contractId"));
				contract.setInsuranceId(rs.getString("insuranceId"));
				contract.setInsurantId(rs.getString("insurantId"));
				contract.setEffectiveness(rs.getBoolean("effectiveness"));
				contract.setUnpaidPeriod(rs.getInt("unpaidPeriod"));
				contractList.add(contract);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return contractList;
	}
	
	public Contract selectUnpaidAndFee(String contractId) {
		Contract contract = new Contract();
		String sql = "SELECT contractId, fee, unpaidPeriod FROM contract WHERE contractid = '"+contractId+"';";
		
		this.read(sql);
		try {
			while (rs.next()) {
				contract.setContractId(rs.getString("contractId"));
				contract.setFee(rs.getInt("fee"));
				contract.setUnpaidPeriod(rs.getInt("unpaidPeriod"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return contract;
	}
	
	public ArrayList<Contract> selectByInsurant(String insurantId) {
		ArrayList<Contract> contractList = new ArrayList<Contract>();
		AccidentDAO accidentDAO = new AccidentDAOImpl();

		String sql = "SELECT * FROM contract where insurantId = '"+ insurantId +"';";
		this.read(sql);
		
		Contract contract = new Contract();
		try {
			while (rs.next()) {
				contract.setContractId(rs.getString("contractId"));
				contract.setInsuranceId(rs.getString("insuranceId"));
				contract.setEffectiveness(rs.getBoolean("effectiveness"));
				contract.setLifespan(rs.getInt("lifespan"));
				contract.setUnpaidPeriod(rs.getInt("unpaidPeriod"));
				contractList.add(contract);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		contract.setAccidentList(accidentDAO.selectByContractId(contract.getContractId()));
		contract.setPayHistory(this.selectPayHistory(contract.getContractId()));
		
		return contractList;
	}
	

	
	public boolean[] selectPayHistory(String contractId) {
		boolean[] payHistory = new boolean[12];
		String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Deb"};
		String sql = "SELECT * FROM payHistory WHERE contractId = '"+contractId+"';";
		this.read(sql);
		try {
			while (rs.next()) {
				for (int i = 0; i < months.length; i++) {
					payHistory[i] = rs.getBoolean(months[i]);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return payHistory;
	}

	public boolean updateFee(String contractId, int fee) {
		String sql = "UPDATE contract SET fee = "+fee+" WHERE contractId = '"+contractId+"';";
		return this.execute(sql);
	}

	public boolean updateEffectiveness(String contractId, boolean effectiveness) {
		String sql = "UPDATE contract SET effectiveness = "+effectiveness+" WHERE contractId = '"+contractId+"';";
		return this.execute(sql);
	}
	
	public boolean updatePayHistory(String contractId, int month) {
		String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Deb"};
		String sql = "UPDATE payHistory SET "+months[month]+" = true WHERE contractId = '"+contractId+"';";
		return this.execute(sql);
	}
	
	public boolean updateAnnualPayHistory() {
		String sql = "UPDATE payHistory SET Jan = false, Feb = false, Mar = false, Apr = false, May = false, Jun = false, Jul = false, Aug = false, "
				+ "Sep = false, Oct = false, Nov = false, Deb = false;";
		return this.execute(sql);
	}
	
	public boolean delete(String contractId) {
		String sql = "DELETE FROM contract WHERE contractId = '"+contractId+"';";
		return this.execute(sql);
	}
	
	public boolean updateLifespan(String contractId, int lifespan) {
		String sql = "UPDATE contract SET lifespan = "+lifespan+" WHERE contractId = '"+contractId+"';";
		return this.execute(sql);
	}
	
	public boolean updateUnpaidPeriod(String contractId, int unpaidPeriod) {
		String sql = "UPDATE contract SET unpaidPeriod = "+unpaidPeriod+" WHERE contractId = '"+contractId+"';";
		return this.execute(sql);
	}

	public boolean updateSpecial(String contractId, boolean special) {
		String sql = "UPDATE contract SET special = "+ special +" WHERE contractId = '"+contractId+"';";
		return this.execute(sql);
		
	}

}
