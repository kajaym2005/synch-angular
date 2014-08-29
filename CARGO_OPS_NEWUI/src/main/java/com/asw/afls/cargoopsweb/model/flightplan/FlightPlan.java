package com.asw.afls.cargoopsweb.model.flightplan;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class FlightPlan implements Serializable {
	private static final long serialVersionUID = 1L;

	private FlightSummary summary = null;
	private List<Shipment> shipments = null;

	public FlightPlan() {

	}
	
	public FlightSummary getSummary() {
		return summary;
	}


	public void setSummary(FlightSummary summary) {
		this.summary = summary;
	}


	public List<Shipment> getShipments() {
		return shipments;
	}

	public void setShipments(List<Shipment> shipments) {
		this.shipments = shipments;
	}

}
