package business.employee;

import business.contract.Contract;
import db.dao.ContractDAO;

public class ContractManager extends Employee {
	private ContractDAO contractDAO;

	public ContractManager(ContractDAO contractDAO){
		this.contractDAO = contractDAO;
	}

	public void manageUnpaidContract(Contract contract){
		this.contractDAO.updateEffectiveness(contract.getContractId(), false);
	}
}