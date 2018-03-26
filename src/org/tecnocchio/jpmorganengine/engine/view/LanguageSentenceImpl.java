/**
 * 
 */
package org.tecnocchio.jpmorganengine.engine.view;

import java.util.Locale;

/**
 * @author Maurizio Barbato
 *     tecnocchio@gmail.com
 * 
 *
 */
public class LanguageSentenceImpl implements LanguageSentence {

	private Locale defaultLocale;
	public LanguageSentenceImpl(Locale defaultLocale) {
		this.defaultLocale=defaultLocale;
	}
	@Override
	public String getLocalizedMsg(String msg) {		
		return getLocalizedMsg(msg, defaultLocale);
	}
	@Override
	public String getLocalizedMsg(final String msg, Locale locale) {
		// property file or db or.. could be better 
		
		if (locale==Locale.ENGLISH){
			switch (msg) {
			case "INCOMING_MSG":
				return " - Incoming - ";
			case "OUTGOING_MSG":
				return " - Outgoing - ";
			case "INCOMINGENTITY_MSG":
				return "- Incoming entity -";
			case "OUTGOINGENTITY_MSG":
				return "- Outgoing entity -";
				
			}
			
		}
		
		return null;
	}

}
