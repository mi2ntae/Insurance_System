package contract;

import java.util.ArrayList;

public interface AccidentDAO {
	public boolean insert(Accident accident);
	public ArrayList<Accident> select();
	public Accident selectAccident(String accidentId);
	public boolean updateCompensation(String accidentId, int compensation);
	public boolean updateHandlingStatus(String accidentId, boolean handlingStatus);
	public boolean delete(String accidentId);
}
