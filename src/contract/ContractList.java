package contract;

public interface ContractList {
	// public Methods
	public boolean insert(Contract contract);
	public Contract search(String contractId);
	public boolean delete(String contractId);
	public void updateEffectivenessById(String contractId, boolean effectiveness);
}