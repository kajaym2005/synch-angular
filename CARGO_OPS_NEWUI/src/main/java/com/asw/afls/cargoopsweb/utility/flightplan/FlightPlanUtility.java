package com.asw.afls.cargoopsweb.utility.flightplan;

import java.rmi.RemoteException;

import com.accenture.asw.afls.cargoops.buildplan.api.OpsBuildplanManager;
import com.accenture.asw.afls.cargoops.cfc.buildplan.dto.view.AssntInfoDTO;
import com.asw.afls.cargoopsweb.model.flightplan.FlightPlan;
import com.asw.afls.cargoopsweb.model.flightplan.FlightPlanCriteria;
import com.asw.afls.cargoopsweb.translator.flightplan.FlightPlanTranslator;

public class FlightPlanUtility {
	private OpsBuildplanManager opsBuildplanManager = null;
	private FlightPlanTranslator translator = null;
	
	public OpsBuildplanManager getOpsBuildplanManager() {
		return opsBuildplanManager;
	}

	public void setOpsBuildplanManager(OpsBuildplanManager opsBuildplanManager) {
		this.opsBuildplanManager = opsBuildplanManager;
	}

	public FlightPlanTranslator getTranslator() {
		return translator;
	}

	public void setTranslator(FlightPlanTranslator translator) {
		this.translator = translator;
	}

	public FlightPlan getFlightPlan(FlightPlanCriteria criteria) throws RemoteException{
		
		//create CFC and then call the ejb
		AssntInfoDTO assntInfoDTO = opsBuildplanManager.getAssntinfo(translator.buildSearchInfoDTO(criteria));
		
		//create flight plan
		FlightPlan flightPlan = getTranslator().toFlightPlan(assntInfoDTO);
		
		return flightPlan;
	}
}
