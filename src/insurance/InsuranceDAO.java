package insurance;

import java.util.ArrayList;

public interface InsuranceDAO {
	
	public boolean insert(Insurance insurance);
	public ArrayList<Insurance> select();
	public boolean updateConfirmedStatus(String insuranceId);
	public boolean delete(String insuranceId);
	
	public Insurance selectTypeInsurance(Insurance insurance);
}
