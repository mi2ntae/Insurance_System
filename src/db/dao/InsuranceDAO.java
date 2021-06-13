package db.dao;

import java.util.ArrayList;

import business.insurance.Insurance;

public interface InsuranceDAO {
	
	public boolean insert(Insurance insurance);
	public boolean insertActualCostInsurance(Insurance insurance);
	public boolean insertCancerInsurance(Insurance insurance);
	public boolean insertDentalInsurance(Insurance insurance);
	public boolean insertDriverInsurance(Insurance insurance);
	public boolean insertFireInsurance(Insurance insurance);
	public boolean insertTripInsurance(Insurance insurance);
	
	public ArrayList<Insurance> select();
	public ArrayList<Insurance> selectForConfirm();
	public ArrayList<Insurance> selectSimpleData();
	public ArrayList<String> selectInsuranceId();

	public Insurance selectActualCostInsurance(Insurance insurance);
	public Insurance selectCancerInsurance(Insurance insurance);
	public Insurance selectDentalInsurance(Insurance insurance);
	public Insurance selectDriverInsurance(Insurance insurance);
	public Insurance selectFireInsurance(Insurance insurance);
	public Insurance selectTripInsurance(Insurance insurance);
	public Insurance selectInsurance(String insuranceId);
	public boolean updateConfirmedStatus(String insuranceId, boolean confirmedStatus);
	public boolean updateDel(String insuranceId, boolean del);
	public boolean delete(String insuranceId);
	public boolean deleteInsuranceByTime();
	
	public Insurance selectTypeInsurance(Insurance insurance);
	
}
