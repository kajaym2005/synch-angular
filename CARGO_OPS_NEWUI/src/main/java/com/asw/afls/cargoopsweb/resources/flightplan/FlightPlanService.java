package com.asw.afls.cargoopsweb.resources.flightplan;

import java.rmi.RemoteException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.asw.afls.cargoopsweb.model.flightplan.FlightPlan;
import com.asw.afls.cargoopsweb.model.flightplan.FlightPlanCriteria;
import com.asw.afls.cargoopsweb.utility.flightplan.FlightPlanUtility;
import com.asw.afls.cargoopsweb.validator.flightplan.FlightPlanValidator;

@Component
@Path("/flightplan")
public class FlightPlanService {

	@Autowired
	@Qualifier("flightPlanUtility")
	private FlightPlanUtility utility;

	@Autowired
	@Qualifier("flightPlanValidator")
	private FlightPlanValidator validator;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public FlightPlan getFlightPlan(FlightPlanCriteria criteria) {
	
		FlightPlan flightPlan = null;
		
		try {
			flightPlan = utility.getFlightPlan(criteria);
		} catch (RemoteException e) {
			// TODO: Exception Handle, set appropriate response code.
			e.printStackTrace();
		}
		
		//FIXME: set the response code
		return flightPlan;
	}
}
