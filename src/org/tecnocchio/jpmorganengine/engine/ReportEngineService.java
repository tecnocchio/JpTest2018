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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.tecnocchio.jpmorganengine.engine.controller.GroupUtil;
import org.tecnocchio.jpmorganengine.engine.model.DayPj;
import org.tecnocchio.jpmorganengine.engine.model.EntityPj;
import org.tecnocchio.jpmorganengine.engine.model.InstructionDao;
import org.tecnocchio.jpmorganengine.engine.model.InstructionDaoImpl;
import org.tecnocchio.jpmorganengine.engine.model.InstructionPj;
import org.tecnocchio.jpmorganengine.engine.view.InstructionView;
import org.tecnocchio.jpmorganengine.engine.view.InstructionViewImpl;

/**
 * @author Maurizio Barbato tecnocchio@gmail.com
 * 
 *
 */
public class ReportEngineService {

	/**
	 * 
	 * 
	 */
	InstructionDao iDao; // the interfaces grants software abstraction
	InstructionView view;
	GroupUtil grpUtil;

	public ReportEngineService() {
		iDao = new InstructionDaoImpl();
		view = new InstructionViewImpl();
		grpUtil= new GroupUtil();
	}

	/**
	 * program hearth it coordinate the job
	 */
	public  void produceReport(String dataIn) {
		List<InstructionPj> instr = iDao.getIstrucionList(dataIn);
		List<DayPj> days = grpUtil.groupByDays(instr);
		List<EntityPj> entities = grpUtil.groupByEntities(instr);
		view.show(days, entities);
	}


}
