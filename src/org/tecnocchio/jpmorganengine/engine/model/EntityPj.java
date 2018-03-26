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

/**
 * @author Maurizio Barbato
 *     tecnocchio@gmail.com
 * 
 *
 */
public class EntityPj {
	private String name;
	private BigDecimal in= new BigDecimal(0);
	private BigDecimal out= new BigDecimal(0);
	
	public EntityPj(String name) {
		this.name=name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public void addOperation(String operation, BigDecimal getuSD) {
		if (operation.equals("B")){
			
		out=	out.add(getuSD);
		}else
		{
		in=	in.add(getuSD);
		}
		
	}

}
