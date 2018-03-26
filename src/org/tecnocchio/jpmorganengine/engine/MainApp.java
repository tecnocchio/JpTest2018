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

import org.tecnocchio.jpmorganengine.prototype.PrototypeService;

/**
 * @author Maurizio Barbato tecnocchio@gmail.com
 * 
 *  Main Application
 */
public class MainApp {

	/**
	 * @param args
	 */
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
	public static void main(String[] args) {
		new ReportEngineService().produceReport(sampleData);
		new PrototypeService().elabPrototypeService(sampleData);
	}

}
