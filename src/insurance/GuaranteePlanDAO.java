package insurance;

import java.util.ArrayList;

public interface GuaranteePlanDAO {
	public boolean insert(GuaranteePlan guaranteePlan);
	public ArrayList<GuaranteePlan> selectById(String insuranceId);
}
