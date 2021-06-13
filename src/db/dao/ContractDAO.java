package db.dao;

import java.util.ArrayList;

import business.contract.Contract;

public interface ContractDAO {
	public boolean insert(Contract contract);
	public ArrayList<Contract> select();
	public ArrayList<Contract> selectForTime();
	public Contract selectContract(String contractId);
	public ArrayList<Contract> selectByInsurant(String insurantId);
	public ArrayList<Contract> selectIds();
	public Contract selectUnpaidAndFee(String contractId);
	public boolean[] selectPayHistory(String contractId);
	public boolean updateFee(String contractId, int fee);
	public boolean updateEffectiveness(String contractId, boolean effectiveness);
	public boolean updatePayHistory(String contractId, int month);
	public boolean updateAnnualPayHistory();
	public boolean delete(String contractId);
	public boolean updateLifespan(String contractId, int lifespan);
	public boolean updateUnpaidPeriod(String contractId, int unpaidPeriod);
	public boolean updateSpecial(String contractId, boolean special);
	public ArrayList<String> selectContractId();

}
