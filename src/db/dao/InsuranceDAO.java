package db.dao;

import java.util.ArrayList;

import business.insurance.Insurance;

public interface InsuranceDAO {
	
	public boolean insert(Insurance insurance);
	public ArrayList<Insurance> select();
	public ArrayList<String> selectInsuranceId();
	public Insurance selectInsurance(String insuranceId);
	public ArrayList<Insurance> selectForConfirm();
	public ArrayList<Insurance> selectSimpleData();
	public boolean updateConfirmedStatus(String insuranceId, boolean confirmedStatus);
	public boolean updateDel(String insuranceId, boolean del);
	public boolean delete(String insuranceId);
	public boolean deleteInsuranceByTime();
	
	public Insurance selectTypeInsurance(Insurance insurance);
	
}
