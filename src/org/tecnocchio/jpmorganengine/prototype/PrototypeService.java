package org.tecnocchio.jpmorganengine.prototype;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

/**
 * This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

/**
 * @author Maurizio Barbato tecnocchio@gmail.com
 *
 */
public class PrototypeService {

	/*
	 * light fast approach to a problem way. fast parsing and ready output solution
	 */
	public void elabPrototypeService(String sampleData) {

		// final reports needed
		Map<String, BigDecimal> incoming = new TreeMap<String, BigDecimal>();// sorted by key
		Map<String, BigDecimal> outgoing = new TreeMap<String, BigDecimal>();
		Map<String, BigDecimal> entityIn = new HashMap<String, BigDecimal>();
		Map<String, BigDecimal> entityOut = new HashMap<String, BigDecimal>();

		// reading data with caution (bad input filtered)
		for (String sInstruction : sampleData.split("\n")) { // for each row
			String[] mappedData = sInstruction.split(" "); // split row in field
			if (mappedData == null || mappedData.length != 12) {
				 MyLog.log("wrong format -" + sInstruction);
				continue;
			} // bad input check

			if (!mappedData[1].equals("B") && !mappedData[1].equals("S")) {
				MyLog.log("wrong Buy/Sell info -" + sInstruction);
				continue;
			} // bad input check
			Date dSettle = getSettleDay(mappedData[3], mappedData[7] + " " + mappedData[8] + " " + mappedData[9]);
			if (dSettle == null) {
				MyLog.log("wrong settled data -" + sInstruction);
				continue;
			} // bad input check
			BigDecimal prezzo = toBigDecimal(mappedData[11]);
			if (prezzo == null) {
				MyLog.log("wrong price -" + sInstruction);
				continue;
			} // bad input check
			BigDecimal units = toBigDecimal(mappedData[10]);
			if (units == null) {
				MyLog.log("wrong units number -" + sInstruction);
				continue;
			} // bad input check
			BigDecimal afx = toBigDecimal(mappedData[2]);
			if (afx == null) {
				MyLog.log("wrong agreed fx -" + sInstruction);
				continue;
			} // bad input check

			// x * 10 000 ( no need about bigdecimal for small numbers )
			BigDecimal usd = prezzo.multiply(units).multiply(afx);

			// year4moth2day2 for direct sorting
			String date = String.format("%1$tY%1$tm%1$td", dSettle);
			// entity name
			String entity = mappedData[0];

			if (mappedData[1].equals("B")) { // its a BUY so it goes in out expense
				// we have a map where the key is the date , if we have not that date yet, we take default("0")
				// than we add the value for that day to the map
				outgoing.put(date, outgoing.getOrDefault(date,new BigDecimal( "0")).add( usd));
				// same for entity map 
				entityOut.put(entity, entityOut.getOrDefault(entity, new BigDecimal( "0")) .add( usd));
			} else {
				// as for out, for incoming this time 
				incoming.put(date, incoming.getOrDefault(date, new BigDecimal( "0")) .add( usd));
				entityIn.put(entity, entityIn.getOrDefault(entity, new BigDecimal( "0")) .add( usd));
			}
		}
		/*
		 * final report ready :
		 */
		System.out.println("- Incoming: -"); // TreeMap is default sorted by key, our key is year4month2day2
		incoming.entrySet()  // for each record we provide an output to the console formatted a little
				.forEach((en) -> System.out.println(en.getKey().substring(6) + "-"
						+ en.getKey().substring(4, 6) + "-" + en.getKey().substring(0, 4) + " -> " + String
								.format("%1$10s", en.getValue())));
		System.out.println("- Outgoing: -"); // TreeMap is default sorted by key
		outgoing.entrySet()
				.forEach((en) -> System.out.println(en.getKey().substring(6) + "-"
						+ en.getKey().substring(4, 6) + "-" + en.getKey().substring(0, 4) + " -> " + String
								.format("%1$10s", en.getValue())));
		System.out.println("- Incoming entity -");
		// we need to sort the content by value, from bigger, so that we can show the result 
		entityIn.entrySet().stream().sorted((en1, en2) -> en2.getValue().compareTo(en1.getValue()) )
				.forEach((en) -> System.out.println(en.getKey() + " -> "
						+ String.format("%1$10s", en.getValue())));
		System.out.println("- Outgoing entity -");
		entityOut.entrySet().stream().sorted((en1, en2) -> en2.getValue().compareTo(en1.getValue()))
				.forEach((en) -> System.out.println(en.getKey() + " -> "
						+ String.format("%1$10s", en.getValue())));
	}

	

	private BigDecimal toBigDecimal(String number) {
		BigDecimal b=null;
		try {
			b=new BigDecimal(number);
		}
		catch(NumberFormatException e) {
			return null;
		}
		return b;
	}



	/**
	 * lets find a good day to settle
	 *
	 * @param cur
	 *            currency
	 * @param data
	 *            data to settle
	 * @return date to settle not in holiday
	 */
	private Date getSettleDay(String cur, String data) {
		DateFormat format = new SimpleDateFormat("d MMM yyyy", Locale.ENGLISH);
		Date date = null;
		try {
			date = format.parse(data);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int dow = c.get(Calendar.DAY_OF_WEEK);
		// day of week between sunday and friday not aed nor sar is ok
		// day of week before thursday and aed or sar is ok
		// otherway add a day and re-check
		while (!(dow > Calendar.SUNDAY && dow < Calendar.SATURDAY && !cur.equals("AED") && !cur.equals("SAR"))
				&& !(dow < Calendar.FRIDAY && (cur.equals("AED") || cur.equals("SAR")))) {
			c.add(Calendar.DAY_OF_MONTH, 1);
			dow = c.get(Calendar.DAY_OF_WEEK);
		}
		return c.getTime();
	}

}

/**
 * 
 * could implement same sort of log and ...
 */
class MyLog  {

	public static void log(String arg0) {
		// something more complex than
		System.err.println(arg0);
	}
}
