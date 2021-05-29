package contract;

import java.util.ArrayList;

public interface ContractDAO {
	public boolean insert(Contract contract);
	public ArrayList<Contract> select();
	public Contract selectContract(String contractId);
	public boolean[] selectPayHistory(String contractId);
	public boolean updateFee(String contractId, int fee);
	public boolean updateEffectiveness(String contractId, boolean effectiveness);
	public boolean updatePayHistory(String contractId, int month);
	public boolean updateAnnualPayHistory();
	public boolean delete(String contractId);
	
}
