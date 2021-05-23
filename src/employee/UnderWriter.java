package employee;

import contract.Contract;
import contract.ContractList;

public class UnderWriter {

	public ContractList contractList;

	public UnderWriter(){

	}
	
	public void assoicate(ContractList contractList) {
		this.contractList = contractList;
	}

	public void requestJointAcquisition(){

	}

	// 계약 승인
	public void approveContract(Contract contract) {
		contract.setEffectiveness(true);
		contract.setUnpaidPeriod(1);
		// DB에서 effectiveness = true;
	}
	
	// 계약 거부
	public void refuseContract(Contract contract) {
		this.contractList.delete(contract.getContractId());
	}
}