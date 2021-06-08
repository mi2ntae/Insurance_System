package business.employee;

import business.insurance.Insurance;
import db.dao.InsuranceDAO;

public class InsuranceConfirmer extends Employee {
	private InsuranceDAO insuranceDAO;

	public InsuranceConfirmer(InsuranceDAO insuranceDAO){
		this.insuranceDAO = insuranceDAO;
	}

	public void confirmInsurance(Insurance insurance){
		this.insuranceDAO.updateConfirmedStatus(insurance.getInsuranceId(), true);
	}

}