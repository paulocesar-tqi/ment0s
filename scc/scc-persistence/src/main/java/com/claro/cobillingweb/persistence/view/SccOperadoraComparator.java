package com.claro.cobillingweb.persistence.view;

import java.util.Comparator;

import com.claro.cobillingweb.persistence.entity.SccOperadora;

public class SccOperadoraComparator implements Comparator<SccOperadora> {

	@Override
	public int compare(SccOperadora o1, SccOperadora o2) {
		
		return o1.getDsOperadora().compareTo(o2.getDsOperadora());
	}

}
