package db.dao;

import java.util.ArrayList;

import business.contract.Accident;

public interface AccidentDAO {
	public boolean insert(Accident accident);
	public ArrayList<Accident> select();
	public ArrayList<Accident> selectByContractId(String contractId);
	public Accident selectAccident(String accidentId);
	public boolean updateCompensation(String accidentId, int compensation);
	public boolean updateHandlingStatus(String accidentId, boolean handlingStatus);
	public boolean delete(String accidentId);
}
