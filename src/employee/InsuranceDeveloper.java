package employee;

import java.util.ArrayList;

import global.Constants.eInsuranceType;
import insurance.ActualCostInsurance;
import insurance.CancerInsurance;
import insurance.DentalInsurance;
import insurance.DriverInsurance;
import insurance.FireInsurance;
import insurance.Insurance;
import insurance.InsuranceDAO;
import insurance.TripInsurance;

public class InsuranceDeveloper extends Employee {
	private ArrayList<Insurance> insuranceList;
	private InsuranceDAO insuranceDAO;
	
	public InsuranceDeveloper(InsuranceDAO insuranceDAO, ArrayList<Insurance> insuranceList){
		this.insuranceList = insuranceList;
		this.insuranceDAO = insuranceDAO;
	}

	public Insurance designInsurance(Insurance insurance, int type){
		switch (type) {
		case 1:
			insurance = new DriverInsurance();
			insurance.setType(eInsuranceType.driverInsurance);
			break;
		case 2:
			insurance = new DentalInsurance();
			insurance.setType(eInsuranceType.dentalInsurance);
			break;
		case 3:
			insurance = new ActualCostInsurance();
			insurance.setType(eInsuranceType.actualCostInsurance);
			break;
		case 4:
			insurance = new FireInsurance();
			insurance.setType(eInsuranceType.fireInsurance);
			break;
		case 5:
			insurance = new CancerInsurance();
			insurance.setType(eInsuranceType.cancerInsurance);
			break;
		case 6:
			insurance = new TripInsurance();
			insurance.setType(eInsuranceType.tripInsurance);
			break;
		}
		return insurance;
		
	}

	public void postManageInsurance(Insurance insurance){
		
	}

	public void readSurveyResult(){

	}

	public Insurance setDetailOfInsurance(Insurance insurance, String type, double[] rate){
		switch(type) {
		case "age":
			insurance.setRateOfAge(rate);
			break;
		case "gender":
			insurance.setRateOfGender(rate);
			break;
		case "job":
			insurance.setRateOfJob(rate);
			break;
		}
		return insurance;
	}
	
	public boolean finishInsurance(Insurance insurance) {
		boolean isOkay = false;
		if (this.insuranceDAO.insert(insurance)) {
			this.insuranceList.add(insurance);
			isOkay = true;
		}
		return isOkay;
	}
}