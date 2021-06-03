package employee;

import insurance.Insurance;
import insurance.InsuranceDAO;

public class InsuranceConfirmer extends Employee {
	private InsuranceDAO insuranceDAO;

	public InsuranceConfirmer(InsuranceDAO insuranceDAO){
		this.insuranceDAO = insuranceDAO;
	}

	public void confirmInsurance(Insurance insurance){
		insurance.setConfirmedStatus(true);
		this.insuranceDAO.updateConfirmedStatus(insurance.getInsuranceId(), true);
	}

}