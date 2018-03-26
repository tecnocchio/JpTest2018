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
package org.tecnocchio.jpmorganengine.engine.lib;

import java.util.Calendar;
import java.util.Date;

/**
 * @author Maurizio Barbato
 *     tecnocchio@gmail.com
 * 
 *
 */
public class SettlementLib {

	/**
	 * 
	 */
	public SettlementLib() {
		
	}

	/**
	 * @param currency
	 * @param dSettlement
	 * @return the correct Date to settle as described in rules
	 */
	public Date getFirstGoodDate(String currency, Date dSettlement) {
		Calendar c=Calendar.getInstance();
		c.setTime(dSettlement);
		int dow=c.get(Calendar.DAY_OF_WEEK);
		// day of week between sunday and saturday not aed nor sar is ok 
		// day of week before friday and  aed or sar is ok 
		// otherway add a day and re-check
		while (!(dow>Calendar.SUNDAY && dow<Calendar.SATURDAY && !currency.equals("AED")&& ! currency.equals("SAR"))&&
				!(dow<Calendar.FRIDAY && (currency.equals("AED") || currency.equals("SAR")) )){
			c.add(Calendar.DAY_OF_MONTH, 1);
			dow=c.get(Calendar.DAY_OF_WEEK);
		}
		return c.getTime();
	}

}
