package com.asw.afls.cargoopsweb.translator.flightplan;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.accenture.asw.afls.cargoops.cfc.buildplan.dto.common.FlightDTO;
import com.accenture.asw.afls.cargoops.cfc.buildplan.dto.common.ShipmentDetailInfoDTO;
import com.accenture.asw.afls.cargoops.cfc.buildplan.dto.common.ShipmentInfoDTO;
import com.accenture.asw.afls.cargoops.cfc.buildplan.dto.search.BuildPlanSearchInfoDTO;
import com.accenture.asw.afls.cargoops.cfc.buildplan.dto.search.SearchByFlightDTO;
import com.accenture.asw.afls.cargoops.cfc.buildplan.dto.view.AssntInfoDTO;
import com.asw.afls.cargoopsweb.model.flightplan.FlightPlan;
import com.asw.afls.cargoopsweb.model.flightplan.FlightPlanCriteria;
import com.asw.afls.cargoopsweb.model.flightplan.FlightSummary;
import com.asw.afls.cargoopsweb.model.flightplan.Shipment;
import com.asw.afls.cargoopsweb.model.flightplan.ShipmentDetails;

public class FlightPlanTranslator {

	/**
	 * Flight Search criteria to cfc object. FIXME : Exception Handler
	 * 
	 * @param flightPlan
	 * @return
	 */
	public BuildPlanSearchInfoDTO buildSearchInfoDTO(FlightPlanCriteria criteria) {
		BuildPlanSearchInfoDTO searchInfoDTO = new BuildPlanSearchInfoDTO();

		// set flight dto object.
		SearchByFlightDTO searchByFlightDTO = new SearchByFlightDTO();
		searchByFlightDTO.setCarrier(criteria.getCarrier());
		searchByFlightDTO.setFltNo(criteria.getFlightNo());
		searchByFlightDTO.setLegOrg(criteria.getLegOrigin());
		searchByFlightDTO.setLegDis(criteria.getLegDestination());
		searchByFlightDTO.setLegKey(criteria.getLegKey());

		searchInfoDTO.setSearchByFlightDTO(searchByFlightDTO);
		return searchInfoDTO;
	}

	/**
	 * Convert to Flight Summary.
	 * @param flightDTO
	 * @return
	 */
	private FlightSummary convertToFlightSummary(FlightDTO flightDTO) {

		FlightSummary summary = null;

		if (flightDTO != null) {
			summary = new FlightSummary();
			summary.setCarrier(flightDTO.getCarrier());
			summary.setFlightNo(flightDTO.getFltNum());
			summary.setLegOrigin(flightDTO.getLegOrg());
			summary.setLegDestination(flightDTO.getLegDis());
			summary.setFltDepDate(flightDTO.getFltDetDate());
			summary.setFltType(flightDTO.getAircraftType());
			summary.setFltGroup(flightDTO.getAircraftGroup());
		}

		return summary;
	}

	/**
	 * Convert to Flight Shipments.
	 * @param assignedshipDTOList
	 * @return
	 */
	private List<Shipment> convertToFlightShipments(
			List<ShipmentInfoDTO> assignedshipDTOList) {
		
		List<Shipment> shipments = null;
		
		if (assignedshipDTOList != null && !assignedshipDTOList.isEmpty()) {
			shipments = new ArrayList<Shipment>();

			for (ShipmentInfoDTO shipmentInfo : assignedshipDTOList) {

				// create shipment
				Shipment shipment = new Shipment();
				shipment.setAwbNumber(shipmentInfo.getAwbNumber());
				shipment.setAwbOrigin(shipmentInfo.getAwbOrg());
				shipment.setAwbDestination(shipmentInfo.getAwbDis());
				// FIXME: Map correct values here
				shipment.setShipWt(shipmentInfo.getTotalWt());
				shipment.setShipVol(shipmentInfo.getTotalVol());
				shipment.setActShipVol(shipmentInfo.getTotalVol());

				shipment.setDimDesc(shipmentInfo.getDimDetail());
				shipment.setLoadPriority(shipmentInfo.getLoadPriority());
				shipment.setComPriority(shipmentInfo.getComPriority());
				
				//create new shipment details
				List<ShipmentDetails> details = new ArrayList<ShipmentDetails>();

				// add shipment details
				for (ShipmentDetailInfoDTO shipmentDetailsInfo : shipmentInfo
						.getShipDetailDTOList()) {
					
					//add new shipment detail
					ShipmentDetails detail = new ShipmentDetails();
					detail.setPieces(shipmentDetailsInfo.getPieces());
					detail.setLineWt(shipmentDetailsInfo.getPieceWeight());
					detail.setLineVol(shipmentDetailsInfo.getPieceVolume());
					details.add(detail);

				}
				
				shipment.setDetails(details);
				shipments.add(shipment);
			}
		}

		return shipments;
	}

	/**
	 * FIXME : Exception Handler
	 * @param assntInfoDTO
	 * @return
	 */
	public FlightPlan toFlightPlan(AssntInfoDTO assntInfoDTO) {
		FlightPlan plan = new FlightPlan();

		// FIXME: Modify to set criteria object directly.
		FlightSummary summary = convertToFlightSummary(assntInfoDTO.getFltDTO());
		plan.setSummary(summary);

		List<Shipment> shipments = convertToFlightShipments(assntInfoDTO
				.getAssignedshipDTOList());
		plan.setShipments(shipments);

		// TODO : ULD details to be set...
		// List<ULDDescriptionDTO> uldDescription =
		// convertToULDDescription(assntInfoDTO.getAssignedULDDTOList());

		return plan;
	}

	/**
	 * FIXME: Remove if not required.
	 * 
	 * Test Flight Plan object itself returned.
	 * 
	 * @return FligtPlan.
	 */
	public FlightPlan toFlightPlan() {
		FlightPlan plan = new FlightPlan();

		FlightSummary summary = new FlightSummary();
		summary.setCarrier("XX");
		summary.setFlightNo("0111");
		summary.setLegOrigin("FRA");
		summary.setLegDestination("SFO");
		summary.setFltDepDate(new Date());
		summary.setFltType("747");
		summary.setFltGroup("W");
		plan.setSummary(summary);

		List<Shipment> shipments = new ArrayList<Shipment>();
		// 1 Shipment
		Shipment shipment = new Shipment();
		shipment.setAwbNumber("999-70350254");
		shipment.setAwbOrigin("FRA");
		shipment.setAwbDestination("SFO");
		shipment.setShipWt(90d);
		shipment.setShipVol(14d);
		shipment.setActShipVol(14d);
		shipment.setDimDesc("100/100/100");
		shipment.setLoadPriority(null);
		shipment.setComPriority(15d);

		List<ShipmentDetails> details = new ArrayList<ShipmentDetails>();
		ShipmentDetails detail = new ShipmentDetails();
		detail.setPieces(4);
		detail.setLineWt(10d);
		detail.setLineVol(1d);
		details.add(detail);

		detail = new ShipmentDetails();
		detail.setPieces(5);
		detail.setLineWt(10d);
		detail.setLineVol(2d);
		details.add(detail);

		shipment.setDetails(details);
		shipments.add(shipment);

		// 2 Shipment
		shipment = new Shipment();
		shipment.setAwbNumber("999-70350302");
		shipment.setAwbOrigin("FRA");
		shipment.setAwbDestination("SFO");
		shipment.setShipWt(100d);
		shipment.setShipVol(30d);
		shipment.setActShipVol(30d);
		shipment.setDimDesc("400/300/100");
		shipment.setLoadPriority(null);
		shipment.setComPriority(15d);

		details = new ArrayList<ShipmentDetails>();
		detail = new ShipmentDetails();
		detail.setPieces(2);
		detail.setLineWt(25d);
		detail.setLineVol(10d);
		details.add(detail);

		detail = new ShipmentDetails();
		detail.setPieces(1);
		detail.setLineWt(50d);
		detail.setLineVol(20d);
		details.add(detail);

		shipment.setDetails(details);
		shipments.add(shipment);

		plan.setShipments(shipments);

		return plan;
	}

}
