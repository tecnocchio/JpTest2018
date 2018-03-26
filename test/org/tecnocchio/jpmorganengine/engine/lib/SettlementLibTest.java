package org.tecnocchio.jpmorganengine.engine.lib;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.tecnocchio.jpmorganengine.engine.lib.SettlementLib;
/*
 * rules :
 * A work week starts Monday and ends Friday, 
 * unless the currency of the trade is AED or SAR, 
 *         where the work week starts Sunday and ends Thursday. 
 * No other holidays to be taken into account.
 * 
 * */
public class SettlementLibTest {
	Date dThursday = null;
	Date dFriday = null;
	Date dSaturday = null;
	Date dSunday = null;
	Date dMonday = null;
	Date dTuesday = null;
	Date dWednesday = null;
	
	public SettlementLibTest() throws ParseException {
		dThursday = new SimpleDateFormat("dd/MM/yyyy").parse("23/02/2017"); // thursday
		dFriday = new SimpleDateFormat("dd/MM/yyyy").parse("24/02/2017");  // friday
		dSaturday = new SimpleDateFormat("dd/MM/yyyy").parse("25/02/2017"); // saturday
		dSunday = new SimpleDateFormat("dd/MM/yyyy").parse("26/02/2017");  // sunday
		dMonday = new SimpleDateFormat("dd/MM/yyyy").parse("27/02/2017");  // monday
		dTuesday = new SimpleDateFormat("dd/MM/yyyy").parse("28/02/2017");  // tuesday
		dWednesday = new SimpleDateFormat("dd/MM/yyyy").parse("01/03/2017");  // wednesday
		
	}

	@Test
	public final void testSettlementLib() {
		


		SettlementLib sl = new SettlementLib();
		// Thursday is a week day for all even AED
		assertEquals("th ok AED", dThursday.getTime(), sl.getFirstGoodDate("AED", dThursday).getTime());
		// Thursday is a week day for all even SAR
		assertEquals("th ok SAR", dThursday.getTime(), sl.getFirstGoodDate("SAR", dThursday).getTime());
		// Thursday is a week day for all even !AED
		assertEquals("th ok !AED", dThursday.getTime(), sl.getFirstGoodDate("!AED", dThursday).getTime());
		// Friday become Sunday  for AED ( friday/saturday is weekend for AED)
		assertEquals("AED fr 2 sunday", dSunday.getTime(), sl.getFirstGoodDate("AED", dFriday).getTime());
		// Saturday become Sunday  for SAR ( friday/saturday is weekend for SAR)		
		assertEquals("SAR fr 2 sunday", dSunday.getTime(), sl.getFirstGoodDate("SAR", dSaturday).getTime());
		// Sunday is a week day for AED
		assertEquals("AED sunday ok", dSunday.getTime(), sl.getFirstGoodDate("AED", dSunday).getTime());
		// Sunday is not a week day unless for for AED or SAR
		assertFalse("SAED sunday !ok", dSunday.getTime() == sl.getFirstGoodDate("SAED", dSunday).getTime());
		// Saturday become Sunday  for AED ( friday/saturday is weekend for AED)		
		assertEquals("AED sat 2 sunday", dSunday.getTime(), sl.getFirstGoodDate("AED", dSaturday).getTime());
		// Monday is a week day after Saturday if not aed or sar
		assertEquals("!AED sat 2 monday", dMonday.getTime(), sl.getFirstGoodDate("!AED", dSaturday).getTime());

	}

}
