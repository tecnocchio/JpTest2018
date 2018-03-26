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
/**
 * @author Maurizio Barbato tecnocchio@gmail.com
 * 
 *
 */
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.tecnocchio.jpmorganengine.engine.model.DayPj;
import org.tecnocchio.jpmorganengine.engine.model.EntityPj;
import org.tecnocchio.jpmorganengine.engine.model.InstructionPj;

/**
 * @author maurizio
 *
 */
public class GroupUtil {


	/**
	 * @param instrs
	 *            a list of incoming instructions to process
	 * @return a list of entities filled with incoming and outgoing instruction
	 *         data
	 */
	public List<EntityPj> groupByEntities(List<InstructionPj> instrs) {
		List<EntityPj> enpjs = new ArrayList<>();// the list to return
		for (InstructionPj ins : instrs) { // for each instruction
			EntityPj enpj = findOrCreateEntity(ins.getName(), enpjs);
			// fill the entity record with new instruction data
			enpj.addOperation(ins.getOperation(), ins.getuSD());
		}
		return enpjs;
	}

	/**
	 * @param name
	 *            entity name to find
	 * @param enpjs
	 *            the list of entities (to read from or update)
	 * @return the entity found or created with that name
	 */
	private EntityPj findOrCreateEntity(String name, List<EntityPj> enpjs) {
		for (EntityPj en : enpjs) { // we can have the same entity in the past.
			if (en.getName().equals(name))
				return en;
		}
		// we did not find it so let's create and add to the list
		EntityPj ent = new EntityPj(name);
		enpjs.add(ent);
		return ent;
	}

	/**
	 * @param instrs the list of instruction to parse
	 * @return a list of days filled with instruction information
	 */
	public List<DayPj> groupByDays(List<InstructionPj> instrs) {
		List<DayPj> daypjs = new ArrayList<>();
		for (InstructionPj ins : instrs) {
			DayPj dpj = findOrCreateDay(ins.getdSettlement(), daypjs);
			dpj.addOperation(ins.getOperation(), ins.getuSD());
		}
		return daypjs;
	}

	/**
	 * @param date the "day"  to retrive
	 * @param daypjs the list of days
	 * @return the day retrived or created
	 */
	private DayPj findOrCreateDay(Date date, List<DayPj> daypjs) {
		for (DayPj day : daypjs) {
			if (day.getDateTime() == date.getTime())
				return day;// if it exists
		}  
		DayPj day = new DayPj(date); // otherway create it
		daypjs.add(day);  // add the new day to the list
		return day;
	}

}
