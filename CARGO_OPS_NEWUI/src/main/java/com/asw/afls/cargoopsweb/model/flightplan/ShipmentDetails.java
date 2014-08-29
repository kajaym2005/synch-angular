package com.asw.afls.cargoopsweb.model.flightplan;

import java.io.Serializable;

public class ShipmentDetails implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer pieces= null;
	private Double lineWt = null;
	private Double lineVol = null;

	public ShipmentDetails() {

	}

	public Integer getPieces() {
		return pieces;
	}

	public void setPieces(Integer pieces) {
		this.pieces = pieces;
	}

	public Double getLineWt() {
		return lineWt;
	}

	public void setLineWt(Double lineWt) {
		this.lineWt = lineWt;
	}

	public Double getLineVol() {
		return lineVol;
	}

	public void setLineVol(Double lineVol) {
		this.lineVol = lineVol;
	}
	

}
