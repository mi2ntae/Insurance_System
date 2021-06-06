package employee;

import java.util.ArrayList;

import contract.Contract;
import contract.ContractDAO;

public class UnderWriter extends Employee{

	private ArrayList<Contract> contractList;
	private ContractDAO contractDAO;

	public UnderWriter(ContractDAO contractDAO){
		this.contractDAO = contractDAO;
	}

	public void requestJointAcquisition(){

	}

	// 계약 승인
	public void approveContract(Contract contract) {
		contractDAO.updateEffectiveness(contract.getContractId(), true);
		// DB에서 effectiveness = true;
	}
	
	// 계약 거부
	public void refuseContract(Contract contract) {
		this.contractDAO.delete(contract.getContractId());
	}
}