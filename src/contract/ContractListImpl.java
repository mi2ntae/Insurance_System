package contract;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import customer.CustomerList;
import customer.Insurant;
import insurance.Insurance;
import insurance.InsuranceList;

public class ContractListImpl implements ContractList {
	// Components
	private ArrayList<Contract> contractList;
	
	// Constructor
	public ContractListImpl() throws FileNotFoundException {
		this.contractList = new ArrayList<Contract>();
	}
	
	// Initialize
	@Override
	public void initialize(InsuranceList insuranceList, CustomerList customerList) throws FileNotFoundException {
		this.readFromFile(insuranceList, customerList);
	}

	// Getters&Setters
	public ArrayList<Contract> getContractList() {return contractList;}
	public void setContractList(ArrayList<Contract> contractList) {this.contractList = contractList;}

	// public Method
	public boolean insert(Contract contract) {
		if (this.contractList.add(contract)) {
			this.writeToFile(contract);
			return true;
		} else {
			return false;
		}
	}

	public Contract select(String contractId) {
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

	private void writeToFile(Contract contract) {
		File file = new File("data/contract");
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
			writer.append(contract.writeToFile());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void readFromFile(InsuranceList insuranceList, CustomerList customerList) throws FileNotFoundException {
		File file = new File("data/contract");
		Scanner sc = new Scanner(file);
		while (sc.hasNext()) {
			Contract contract = new Contract();
			contract.readFromFile(sc, insuranceList, customerList);
			this.contractList.add(contract);
		}
	}
}