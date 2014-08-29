package com.asw.afls.cargoopsweb.adapter;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class DateAdapter extends XmlAdapter<String, Date> {
	//private SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"); //ISO 8601 format
	
	@Override
	public String marshal(Date date) throws Exception {
		if (date != null) {
			return dateFormat.format(date);
		}
		return null;
		/*
		 * if(date != null){ Calendar cal = new
		 * GregorianCalendar(java.util.TimeZone.getTimeZone("GMT"));
		 * cal.setTime(date); return DatatypeConverter.printDateTime(cal); }
		 * return null;
		 */
	}

	@Override
	public Date unmarshal(String string) throws Exception {
		return dateFormat.parse(string);
		/*
		 * return DatatypeConverter.parseDateTime(string).getTime();
		 */
	}
}
