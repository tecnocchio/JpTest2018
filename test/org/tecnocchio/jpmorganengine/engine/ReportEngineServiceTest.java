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
package org.tecnocchio.jpmorganengine.engine;
/**
 * @author Maurizio Barbato tecnocchio@gmail.com
 * 
 *
 */
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.tecnocchio.jpmorganengine.engine.controller.GroupUtil;
import org.tecnocchio.jpmorganengine.engine.model.DayPj;
import org.tecnocchio.jpmorganengine.engine.model.EntityPj;
import org.tecnocchio.jpmorganengine.engine.model.InstructionDao;
import org.tecnocchio.jpmorganengine.engine.model.InstructionDaoImpl;
import org.tecnocchio.jpmorganengine.engine.model.InstructionPj;
import org.tecnocchio.jpmorganengine.engine.view.InstructionView;
import org.tecnocchio.jpmorganengine.engine.view.InstructionViewImpl;

/**
 * @author maurizio
 *
 */
public class ReportEngineServiceTest {
	final static String sampleData = 
			"foo B 0.50 SGP 01 Jan 2016 02 Jan 2016 200 100.25\n"
			+ "bar S 0.22 AED 05 Jan 2016 07 Jan 2016 450 150.5\n"
			+ "lol B 0.30 SGP 07 Jan 2016 08 Jan 2016 240 200.25\n" 
			+ "wrong line\n"
			+ "bar S 0.12 SAR 09 Jan 2016 11 Jan 2016 420 120.5\n"
			+ "foo B 0.25 SGP 10 Jan 2016 12 Jan 2016 210 110.25\n"
			+ "art S 0.42 AED 11 Jan 2016 13 Jan 2016 470 15.5\n" 
			+ "den S 0.5 SGP 08 Jan 2016 09 Jan 2016 110 10.22\n"
			+ "sit S 0.42 AED 11 Jan 2016 13 Jan 2016 470 15.5\n";
	


	

	/**
	 * Test method for {@link org.tecnocchio.jpmorganengine.engine.ReportEngineService#produceReport(java.lang.String)}.
	 */
	@Test
	public void testProduceReport() {
		List<InstructionPj> instr = new InstructionDaoImpl().getIstrucionList(sampleData);
		assertEquals("number of istruction",instr.size(), 8);
		List<DayPj> days = new GroupUtil().groupByDays(instr);
		assertEquals("number of days with data",days.size(), 6);		
		List<EntityPj> entities = new GroupUtil().groupByEntities(instr);
		assertEquals("number of different entities",entities.size(), 6);	
		for (EntityPj ep:entities) {
			if (ep.getName().equals("foo"))
				assertEquals(ep.getOut().intValue(), 15813);
			if (ep.getName().equals("lol"))
				assertEquals(ep.getOut().intValue(), 14418);
			
		}
	}

}
