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
package org.tecnocchio.jpmorganengine.engine.view;

import java.util.List;
import java.util.Locale;

import org.tecnocchio.jpmorganengine.engine.model.DayPj;
import org.tecnocchio.jpmorganengine.engine.model.EntityPj;

/**
 * @author Maurizio Barbato
 *     tecnocchio@gmail.com
 * 
 *
 */
public class InstructionViewImpl implements InstructionView {

	/**
	 * View implementation.
	 */
	
	LanguageSentence ls;
	
	public InstructionViewImpl() {
		// Messages can be in different languages ??
		// anyway is a good idea to not cable them in the code
		ls=new LanguageSentenceImpl(Locale.ENGLISH);
	}

	@Override
	public void show(List<DayPj> days, List<EntityPj> entities) {
		showIncome(days);		// list of income by day order by date
		showOutgoing(days);		// list of out by day order by date
		showEntIn(entities);	// entity income ranked
		showEntOut(entities);	// entity out ranked
	}

	private void showIncome(List<DayPj> days) {
		System.out.println(ls.getLocalizedMsg("INCOMING_MSG"));
		// java 8
		days.stream().
		sorted((d1, d2) -> d1.getDateTime()>d2.getDateTime()?1:-1) // order by value bigger last
		.forEach(
		(day)-> System.out.print(
				day.getIn()==null?"":(String.format("%1$1td-%1$1tm-%1$1tY", day.getDate())+" -> "+String.format("  %1$10s",day.getIn())+"\n")));

		
	}
	private void showOutgoing(List<DayPj> days) {
		System.out.println(ls.getLocalizedMsg("OUTGOING_MSG"));
		days.stream().
		sorted((d1, d2) -> d1.getDateTime()>d2.getDateTime()?1:-1) // order by value bigger last
		.forEach(
		(day)-> System.out.print(
				day.getOut()==null?"":(String.format("%1$1td-%1$1tm-%1$1tY", day.getDate())+" -> "+String.format("  %1$10s", day.getOut())+"\n")));	
	}
	private void showEntIn(List<EntityPj> ents) {
		System.out.println(ls.getLocalizedMsg("INCOMINGENTITY_MSG"));
		ents.stream().
		sorted((d1, d2) -> d2.getIn().compareTo(d1.getIn())) // order by value bigger first
		.forEach(
		(ent)-> System.out.print(
				ent.getIn().stripTrailingZeros().toString().equals("0")?"":(ent.getName()+" -> "+String.format("  %1$10s",ent.getIn())+"\n")));	
	}
	private void showEntOut(List<EntityPj> ents) {
		System.out.println(ls.getLocalizedMsg("OUTGOINGENTITY_MSG"));
		ents.stream().
		sorted((d1, d2) -> d2.getOut().compareTo(d1.getOut())) // order by value bigger first
		.forEach(
		(ent)-> System.out.print(
				ent.getOut().stripTrailingZeros().toString().equals("0")?"":(ent.getName()+" -> "+String.format("  %1$10s",ent.getOut())+"\n")));	
	}

}
