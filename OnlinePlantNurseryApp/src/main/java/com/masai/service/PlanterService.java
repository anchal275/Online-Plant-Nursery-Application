package com.masai.service;

import java.util.List;


import com.masai.exceptions.LoginException;
import com.masai.exceptions.PlanterException;
import com.masai.model.Planter;

public interface PlanterService {
	public Planter addPlanter(Planter planter, String key)throws PlanterException, LoginException;

	public Planter updatePlanter(Planter planter,  String key)throws PlanterException, LoginException;

	public Planter deletePlanter(Integer planterId,  String key)throws PlanterException, LoginException;

	public Planter viewPlanter(Integer planterId, String key)throws PlanterException, LoginException;

	public List<Planter> viewPlanter(String planterShape, String key) throws PlanterException, LoginException;

	public List<Planter> viewAllPlanters(String key)throws PlanterException, LoginException;

	public List<Planter> viewAllPlanters(double minCost, double maxCost, String key)throws PlanterException, LoginException;
}
