package customer;

import java.util.ArrayList;

public interface InsurantDAO {
	public boolean insert(Insurant insurant);
	public ArrayList<Insurant> select();
}
