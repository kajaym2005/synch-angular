package com.asw.afls.cargoopsweb.model.flightplan;

import java.io.Serializable;
import java.util.Date;

public class FlightSummary implements Serializable {
	private static final long serialVersionUID = 1L;

	private String carrier = null;
	private String flightNo = null;
	private String legOrigin = null;
	private String legDestination = null;
	private Date fltDepDate = null;
	private String fltType= null;
	private String fltGroup = null;

	public FlightSummary(){
		
	}

	public String getCarrier() {
		return carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	public String getFlightNo() {
		return flightNo;
	}

	public void setFlightNo(String flightNo) {
		this.flightNo = flightNo;
	}

	public String getLegOrigin() {
		return legOrigin;
	}

	public void setLegOrigin(String legOrigin) {
		this.legOrigin = legOrigin;
	}

	public String getLegDestination() {
		return legDestination;
	}

	public void setLegDestination(String legDestination) {
		this.legDestination = legDestination;
	}

	public Date getFltDepDate() {
		return fltDepDate;
	}

	public void setFltDepDate(Date fltDepDate) {
		this.fltDepDate = fltDepDate;
	}

	public String getFltType() {
		return fltType;
	}

	public void setFltType(String fltType) {
		this.fltType = fltType;
	}

	public String getFltGroup() {
		return fltGroup;
	}

	public void setFltGroup(String fltGroup) {
		this.fltGroup = fltGroup;
	}
	
	
}
