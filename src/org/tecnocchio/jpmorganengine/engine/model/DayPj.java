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

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Maurizio Barbato
 *     tecnocchio@gmail.com
 * 
 * 
 */
public class DayPj {

	private Date date;
	private BigDecimal in;
	private BigDecimal out;
	
	
	public DayPj(Date date) {
		this.date=date;
	}

	public long getDateTime() {
		return date.getTime();
	}

	/**
	 * @param operation   sell or buy operation
	 * @param getuSD the amount to store / add to stored one
	 */
	public void addOperation(String operation, BigDecimal getuSD) {
		if (operation.equals("B")){  // we store up to 2 kind of information for day
			if (out==null) out=new BigDecimal(0);
			out=out.add(getuSD);
		}else
		{
			if (in==null) in=new BigDecimal(0);
			in=in.add(getuSD);
		}
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public BigDecimal getIn() {
		return in;
	}

	public void setIn(BigDecimal in) {
		this.in = in;
	}

	public BigDecimal getOut() {
		return out;
	}

	public void setOut(BigDecimal out) {
		this.out = out;
	}

}
