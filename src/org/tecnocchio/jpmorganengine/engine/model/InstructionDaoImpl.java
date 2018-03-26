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
package org.tecnocchio.jpmorganengine.engine.model;

import java.util.ArrayList;
import java.util.List;

import org.tecnocchio.jpmorganengine.engine.controller.InsrParser;

/**
 * @author Maurizio Barbato
 *     tecnocchio@gmail.com
 * 
 *
 */
public class InstructionDaoImpl implements InstructionDao {

	/**
	 * 
	 */
	public InstructionDaoImpl() {
	}


	@Override
	public List<InstructionPj> getIstrucionList(String inputData) {
		List <InstructionPj> insrList=new ArrayList<>();
		InsrParser parser= new InsrParser();
		for (String sIns:inputData.split("\n")){
			InstructionPj tmp=parser.parse(sIns);
			if (tmp!=null) insrList.add(tmp);
		}
		return insrList;
	}

}
