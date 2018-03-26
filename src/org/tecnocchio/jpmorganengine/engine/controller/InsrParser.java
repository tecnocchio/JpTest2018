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

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.tecnocchio.jpmorganengine.engine.lib.ErrorParsingRow;
import org.tecnocchio.jpmorganengine.engine.lib.SettlementLib;
import org.tecnocchio.jpmorganengine.engine.model.InstructionPj;

/**
 * @author Maurizio Barbato
 *     tecnocchio@gmail.com
 * 
 * Instruction Parser
 */
/**
 * @author maurizio
 *
 */
public class InsrParser {

	SettlementLib sLib; // used to find the real good date
	
	public InsrParser() {
		sLib= new SettlementLib();
	}


	/**
	 * @param sInput
	 * @return the pojo of the instruction provided 
	 */
	public  InstructionPj parse(String sInput) {
		
		String []dataField=sInput.split(" ");// array of values of a single instruction
		if (dataField==null || dataField.length!=12){ // check the record if has its correct number of values
			new ErrorParsingRow("wrong format:"+sInput);
			return null;
		}
		
		String name=dataField[0];
		String operation=dataField[1];
		if (!operatinValid(operation)) {
			new ErrorParsingRow("wrong operation:"+sInput);
			return null;			
		}
		
		BigDecimal afx=getBdVal(dataField[2]);
		if (afx==null) {
			new ErrorParsingRow("wrong afx:"+sInput);
			return null;			
		}
		
		BigDecimal price=getBdVal(dataField[11]);
		if (price==null) {
			new ErrorParsingRow("wrong price:"+sInput);
			return null;			
		}
		
		BigDecimal units=getBdVal(dataField[10]);
		if (units==null) {
			new ErrorParsingRow("wrong units:"+sInput);
			return null;			
		}
		
		BigDecimal uSD=price.multiply(afx).multiply(units);
		
		String currency=dataField[3];
		
		Date dSettlement=getDateFromString(dataField[7]+" "+dataField[8]+" "+dataField[9]);
		
		if (dSettlement==null) {
			new ErrorParsingRow("wrong settlement date:"+sInput);
			return null;			
		}
		
		// we provide the real Settled DATE as rules provided
		
		dSettlement=sLib.getFirstGoodDate(currency,dSettlement);
		
		return new InstructionPj(name,operation,uSD,dSettlement);
		
	}


	/**
	 * @param sDate
	 * @return the date converted value or null
	 */
	private Date getDateFromString(String sDate) {
		DateFormat format = new SimpleDateFormat("d MMM yyyy", Locale.ENGLISH);
		try {
			Date date = format.parse(sDate);
		return date;
		} catch (ParseException e) {	
			e.printStackTrace();
			return null;
		}
		
	}


	/**
	 * @param val
	 * @return the bigDecimal value or null
	 */
	private  BigDecimal getBdVal(String val) {
		try{
		return new BigDecimal(val);			
		}catch(NumberFormatException e){
			e.printStackTrace();
			return null;
		}			
	}

	/**
	 * @param operation
	 * @return the operation provided is valid
	 */
	private  boolean operatinValid(String operation) {		
		return operation.equals("B") ||  operation.equals("S")	;
	}

}
