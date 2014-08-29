package com.asw.afls.cargoopsweb.model.flightplan;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.asw.afls.cargoopsweb.adapter.DateAdapter;

@XmlRootElement
public class FlightPlanCriteria implements Serializable {
	private static final long serialVersionUID = 1L;

	private String carrier = null;
	private String flightNo = null;
	private String legOrigin = null;
	private String legDestination = null;
	private Date fltDepDate = null;
	private Long legKey = null;

	public FlightPlanCriteria() {

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

	@XmlJavaTypeAdapter(DateAdapter.class)
	public Date getFltDepDate() {
		return fltDepDate;
	}

	public void setFltDepDate(Date fltDepDate) {
		this.fltDepDate = fltDepDate;
	}

	public Long getLegKey() {
		return legKey;
	}

	public void setLegKey(Long legKey) {
		this.legKey = legKey;
	}

}
