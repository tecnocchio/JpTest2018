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
public class InstructionPj {
	private String name;        // entity name
	private String operation;   // sell or buy
	private BigDecimal uSD;     // amount 
	private Date dSettlement;   // settlement day
	


	public InstructionPj(String name, String operation, BigDecimal uSD, Date dSettlement) {
		super();
		this.name = name;
		this.operation = operation;
		this.uSD = uSD;
		this.dSettlement = dSettlement;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public BigDecimal getuSD() {
		return uSD;
	}

	public void setuSD(BigDecimal uSD) {
		this.uSD = uSD;
	}

	public Date getdSettlement() {
		return dSettlement;
	}

	public void setdSettlement(Date dSettlement) {
		this.dSettlement = dSettlement;
	}

}
