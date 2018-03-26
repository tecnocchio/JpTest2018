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
package org.tecnocchio.jpmorganengine.engine.controller;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.junit.Before;
import org.junit.Test;
import org.tecnocchio.jpmorganengine.engine.model.InstructionPj;

/**
 * @author Maurizio Barbato
 *     tecnocchio@gmail.com
 * 
 * InsrParserTest
 */

public class InsrParserTest {

	/**
	 * @throws java.lang.Exception
	 */
	String [] records;
	SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

	
	@Before
	public void setUp() throws Exception {
		records=new String[]{
				"foo B 0.50 SGP 01 Jan 2016 02 Jan 2016 200 100.25", // 0
				"bar S 0.22 AED 05 Jan 2016 07 Jan 2016 450 150.5",// 1
				"lol B 0.30 SGP 07 Jan 2016 08 Jan 2016 240 200.25", // 2
				"wrong line",// 0
				"bar S 0.12 SAR 09 Jan 2016 11 Jan 2016 420 120.5",// 4
				"foo B 0.25 SGP 10 Jan 2016 12 Jan 2016 210 110.25",// 5
				"art S 0.42 AED 11 Jan 2016 13 Jan 2016 470 15.5", // 6
				"den S 0.5 SGP 08 Jan 2016 09 Jan 2016 110 10.22",// 7
	     		"sit S 0.42 AED 11 Jan 2016 13 Jan 2016 470 15.5"};// 8
	}
	/**
	 * Test method for {@link org.tecnocchio.jpmorganengine.engine.controller.InsrParser#parse(java.lang.String)}.
	 * @throws ParseException 
	 */
	@Test
	public void testParse() throws ParseException {
		InsrParser ip= new InsrParser();
	 
		
		InstructionPj ist0=ip.parse(records[0]);
		assertNotNull(ist0);
		assertNotNull(ist0.getdSettlement());
		// 02 01 2016 is saturday so become settled in monday 04 01 2016
		assertEquals("date correct",ist0.getdSettlement().getTime(),format.parse("04-01-2016").getTime() );
		// the amout USD is 10025
		assertEquals("USD correct",ist0.getuSD().intValue(),10025);
		
		InstructionPj ist3=ip.parse(records[3]);
		// the record format is not correct
		assertNull(ist3);
	}

}
