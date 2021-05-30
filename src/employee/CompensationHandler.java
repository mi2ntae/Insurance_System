package employee;

import contract.Accident;
import contract.Contract;

public class CompensationHandler extends Employee {

	public CompensationHandler(){

	}

	public void finalize() throws Throwable {

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