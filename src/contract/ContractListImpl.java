package contract;

import java.util.ArrayList;

public class ContractListImpl implements ContractList {
	// Components
	private ArrayList<Contract> contractList;
	
	// Constructor
	public ContractListImpl() {
		this.contractList = new ArrayList<Contract>();
	}

	// Getters&Setters
	public ArrayList<Contract> getContractList() {return contractList;}
	public void setContractList(ArrayList<Contract> contractList) {this.contractList = contractList;}

	// public Method
	public boolean insert(Contract contract) {
		if (this.contractList.add(contract)) {
			return true;
		} else {
			return false;
		}
	}

	public Contract search(String contractId) {
		for (Contract contract : this.contractList) {
			if (contract.getContractId().equals(contractId)) {
				return contract;
			}
		}
		return null;
	}

	public boolean delete(String contractId) {
		int deleteIndex = getContractIndex(contractId);
		if(deleteIndex != -1) {
			this.contractList.remove(deleteIndex);
			return true;
		} else {
			return false;
		}
		
	}

	public void updateEffectivenessById(String contractId, boolean effectiveness) {
		int updateIndex = getContractIndex(contractId);
		if(updateIndex != -1) {
			this.contractList.get(updateIndex).setEffectiveness(effectiveness);
		}
	}
	
	// private Method
	private int getContractIndex(String contractId) {
		for (int i = 0; i < this.contractList.size(); i++) {
			if (this.contractList.get(i).getContractId().equals(contractId)) {
				return i;
			}
		}
		return -1;
	}
	
}