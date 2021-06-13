package business.employee;

import business.contract.Contract;
import db.dao.ContractDAO;

public class UnderWriter extends Employee{

	private ContractDAO contractDAO;

	public UnderWriter(ContractDAO contractDAO){
		this.contractDAO = contractDAO;
	}

	public void approveContract(Contract contract) {
		contractDAO.updateEffectiveness(contract.getContractId(), true);
		contractDAO.updateUnpaidPeriod(contract.getContractId(), 1);
	}
	
	public void refuseContract(Contract contract) {
		this.contractDAO.delete(contract.getContractId());
	}
}