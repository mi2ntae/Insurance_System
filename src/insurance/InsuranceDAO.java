package insurance;

import java.util.ArrayList;

public interface InsuranceDAO {
	
	public boolean insert(Insurance insurance);
	public ArrayList<Insurance> select();
	public Insurance selectInsurance(String insuranceId);
	public boolean updateConfirmedStatus(String insuranceId, boolean confirmedStatus);
	public boolean delete(String insuranceId);
	
	public Insurance selectTypeInsurance(Insurance insurance);
}
