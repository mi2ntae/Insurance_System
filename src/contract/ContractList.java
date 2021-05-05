package contract;

public interface ContractList {
	// public Methods
	public boolean add(Contract contract);
	public Contract search(String contractId);
	public boolean remove(String contractId);
	public void updateEffectivenessById(String contractId, boolean effectiveness);
}