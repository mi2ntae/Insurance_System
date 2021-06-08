package db.dao;

public interface CompensationCauseDAO {
	public boolean insert(String accidentId, String cause);
	public String selectByAccidentId(String contractId);
}