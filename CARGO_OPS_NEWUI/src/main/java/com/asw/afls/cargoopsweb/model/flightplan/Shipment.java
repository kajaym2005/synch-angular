package com.asw.afls.cargoopsweb.model.flightplan;

import java.io.Serializable;
import java.util.List;

public class Shipment implements Serializable {
	private static final long serialVersionUID = 1L;

	private String awbNumber = null;
	private String awbOrigin = null;
	private String awbDestination = null;
	private Double shipWt = null;
	private Double shipVol = null;
	private Double actShipVol = null;
	private String dimDesc = null;

	private Double loadPriority = null;
	private Double comPriority = null;

	private List<ShipmentDetails> details = null;

	public Shipment() {

	}

	public String getAwbNumber() {
		return awbNumber;
	}

	public void setAwbNumber(String awbNumber) {
		this.awbNumber = awbNumber;
	}

	public String getAwbOrigin() {
		return awbOrigin;
	}

	public void setAwbOrigin(String awbOrigin) {
		this.awbOrigin = awbOrigin;
	}

	public String getAwbDestination() {
		return awbDestination;
	}

	public void setAwbDestination(String awbDestination) {
		this.awbDestination = awbDestination;
	}

	public List<ShipmentDetails> getDetails() {
		return details;
	}

	public void setDetails(List<ShipmentDetails> details) {
		this.details = details;
	}

	public Double getShipWt() {
		return shipWt;
	}

	public void setShipWt(Double shipWt) {
		this.shipWt = shipWt;
	}

	public Double getShipVol() {
		return shipVol;
	}

	public void setShipVol(Double shipVol) {
		this.shipVol = shipVol;
	}

	public Double getActShipVol() {
		return actShipVol;
	}

	public void setActShipVol(Double actShipVol) {
		this.actShipVol = actShipVol;
	}

	
	public String getDimDesc() {
		return dimDesc;
	}

	public void setDimDesc(String dimDesc) {
		this.dimDesc = dimDesc;
	}

	public Double getLoadPriority() {
		return loadPriority;
	}

	public void setLoadPriority(Double loadPriority) {
		this.loadPriority = loadPriority;
	}

	public Double getComPriority() {
		return comPriority;
	}

	public void setComPriority(Double comPriority) {
		this.comPriority = comPriority;
	}

}
