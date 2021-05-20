package contract;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import customer.CustomerList;
import insurance.InsuranceList;

public interface ContractList {
	// public Methods
	public boolean insert(Contract contract);
	public Contract select(String contractId);
	public boolean delete(String contractId);
	public void updateEffectivenessById(String contractId, boolean effectiveness);

	// Initialize
	public void initialize(InsuranceList insuranceList, CustomerList customerList) throws FileNotFoundException;
	
	// Getters&Setters
	public ArrayList<Contract> getContractList();
	public void setContractList(ArrayList<Contract> contractList);
}