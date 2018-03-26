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
public interface LanguageSentence {

	String getLocalizedMsg(String string);
	String getLocalizedMsg(String string,Locale locale);
	

}
