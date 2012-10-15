package com.claro.sccweb.decorator.view;

import org.apache.commons.lang.StringUtils;

import com.claro.cobillingweb.persistence.view.SccFinanceiroView;
import com.claro.sccweb.decorator.rownum.RownumDecorator;
import com.claro.sccweb.vo.RelatorioFinanceiroVO;

public class SccFinanceiroViewDecorator extends RownumDecorator<SccFinanceiroView> {
	
	public SccFinanceiroViewDecorator(SccFinanceiroView entity, int rownum) {
		super(entity, rownum);
		
	}

	private RelatorioFinanceiroVO item;
	
	private RelatorioFinanceiroVO getCurrentItem(){
		return (RelatorioFinanceiroVO) getCurrentRowObject();
	}
	
	private String getFormatedItem(String lNumber) {
		String aux = lNumber;
		if (StringUtils.isNotEmpty(aux)) {
			if (String.valueOf(aux.charAt(0)).equals("-")) {
				aux = aux.substring(1);
				return "<a style='color:red;'>(" + aux + ")</a>";
			}
		} 
		
		return lNumber;
	}
	
	public String getDescricao() {
		item = getCurrentItem();
		
		if (StringUtils.isNotEmpty(item.getDescricao())
				&& item.getDescricao().equals("Desconto Contratual")) {
				return "<a style='color:green;'>" + item.getDescricao() + "</a>";
		}
		
		return item.getDescricao();
	}

	public String getM12() {
		item = getCurrentItem();
		return this.getFormatedItem(item.getM12());
	}
	
	public String getM11() {
		item = getCurrentItem();
		return this.getFormatedItem(item.getM11());
	}

	public String getM10() {
		item = getCurrentItem();
		return this.getFormatedItem(item.getM10());
	}

	public String getM9() {
		item = getCurrentItem();
		return this.getFormatedItem(item.getM9());
	}

	public String getM8() {
		item = getCurrentItem();
		return this.getFormatedItem(item.getM8());
	}

	public String getM7() {
		item = getCurrentItem();
		return this.getFormatedItem(item.getM7());
	}

	public String getM6() {
		item = getCurrentItem();
		return this.getFormatedItem(item.getM6());
	}

	public String getM5() {
		item = getCurrentItem();
		return this.getFormatedItem(item.getM5());
	}

	public String getM4() {
		item = getCurrentItem();
		return this.getFormatedItem(item.getM4());
	}

	public String getM3() {
		item = getCurrentItem();
		return this.getFormatedItem(item.getM3());
	}

	public String getM2() {
		item = getCurrentItem();
		return this.getFormatedItem(item.getM2());
	}

	public String getM1() {
		item = getCurrentItem();
		return this.getFormatedItem(item.getM1());
	}

	public String getMApuracao() {
		item = getCurrentItem();
		return this.getFormatedItem(item.getMapuracao());
	}

	@Override
	protected boolean isDeleteEnabled() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	

	

	
	

}
