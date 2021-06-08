package business.employee;

import business.contract.Accident;

public class CompensationHandler extends Employee {

	public CompensationHandler(){

	}

	public void calculateCompensation(){

	}

	public void payCooperativeFirm(){

	}

	public void renewFee(){

	}

	public void confirmCompensation(Accident accident, int tmptCompensation) {
		accident.setHandlingStatus(true);
		accident.setCompensation(tmptCompensation);
	}

}