package db.dao;

import java.util.ArrayList;

import business.insurance.GuaranteePlan;

public interface GuaranteePlanDAO {
	public boolean insert(GuaranteePlan guaranteePlan);
	public ArrayList<GuaranteePlan> selectById(String insuranceId);
}
