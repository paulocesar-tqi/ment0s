/**
 * 
 */
package com.claro.sccweb.decorator.view;

import org.displaytag.decorator.TableDecorator;

import com.claro.cobillingweb.persistence.entity.SccDisputa;

/**
 * @author vagner.souza
 *
 */
public class SccDisputaDecorator extends TableDecorator {
	
public String getLink1(){
		
		SccDisputa entity;
		entity = (SccDisputa) getCurrentRowObject();
		return "<a href=\"editarDisputa?sqDisputa=" + entity.getSqDisputa() + "</a>";
	}


}
