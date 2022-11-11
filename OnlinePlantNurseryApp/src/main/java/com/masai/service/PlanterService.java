package com.masai.service;

import java.util.List;

import com.masai.exceptions.AdminException;
import com.masai.exceptions.PlanterException;
import com.masai.model.Planter;

public interface PlanterService {
	public Planter addPlanter(Planter planter, String key)throws PlanterException, AdminException;

	public Planter updatePlanter(Planter planter,  String key)throws PlanterException, AdminException;

	public Planter deletePlanter(Integer planterId,  String key)throws PlanterException, AdminException;

	public Planter viewPlanter(Integer planterId)throws PlanterException;

	public List<Planter> viewPlanter(String planterShape) throws PlanterException;

	public List<Planter> viewAllPlanters()throws PlanterException;

	public List<Planter> viewAllPlanters(double minCost, double maxCost)throws PlanterException;
}
