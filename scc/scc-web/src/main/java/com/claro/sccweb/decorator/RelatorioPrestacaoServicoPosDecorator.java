package com.claro.sccweb.decorator;

import com.claro.cobillingweb.persistence.view.RelPrestacaoServicoView;
import com.claro.sccweb.decorator.rownum.RownumDecorator;

public class RelatorioPrestacaoServicoPosDecorator extends RownumDecorator<RelPrestacaoServicoView> {
	
	
	public RelatorioPrestacaoServicoPosDecorator(
			RelPrestacaoServicoView entity, int rownum) {
		super(entity, rownum);
		popularCampos(entity);
		
	}
	
	private RelPrestacaoServicoView entity;
	private String operadoraClaro;
	private String embratel;
	private String intelig;
	private String brasil_telecom;
	private String telefonica;
	private String tnl;
	private String gvt;
	private String sercontel;
	private String tim;
	private String ctbc;
	private String telemar;
	private String ipCorp;
	private String nexus;
	private String telecom65;
	private String cambridge;
	
	
	
	private void popularCampos(RelPrestacaoServicoView entity){
		if (entity != null){
			
			operadoraClaro = entity.getDsOperadoraClaro();
			embratel = formataDouble(entity.getEmbratel());
			intelig = formataDouble(entity.getIntelig());
			brasil_telecom = formataDouble(entity.getBrasil_telecom());
			telefonica = formataDouble(entity.getTelefonica());
			tnl = formataDouble(entity.getTnl());	
			gvt = formataDouble(entity.getGvt());
			sercontel = formataDouble(entity.getSercontel());
			tim = formataDouble(entity.getTim());
			ctbc = formataDouble(entity.getCtbc());
			telemar = formataDouble(entity.getTelemar());
			ipCorp = formataDouble(entity.getIpCorp());
			nexus = formataDouble(entity.getNexus());
			telecom65 = formataDouble(entity.getTelecom65());
			cambridge = formataDouble(entity.getCambridge());
		}
	}
	
	
	public RelPrestacaoServicoView getEntity() {
		return entity;
	}

	public void setEntity(RelPrestacaoServicoView entity) {
		this.entity = entity;
	}

	public String getOperadoraClaro() {
		return operadoraClaro;
	}

	public void setOperadoraClaro(String operadoraClaro) {
		this.operadoraClaro = operadoraClaro;
	}


	public String getEmbratel() {
		return embratel;
	}

	public void setEmbratel(String embratel) {
		this.embratel = embratel;
	}

	public String getIntelig() {
		return intelig;
	}

	public void setIntelig(String intelig) {
		this.intelig = intelig;
	}

	public String getBrasil_telecom() {
		return brasil_telecom;
	}

	public void setBrasil_telecom(String brasil_telecom) {
		
		this.brasil_telecom = brasil_telecom;
	}

	public String getTelefonica() {
		return telefonica;
	}

	public void setTelefonica(String telefonica) {
		this.telefonica = telefonica;
	}

	public String getTnl() {
		return tnl;
	}

	public void setTnl(String tnl) {
		this.tnl = tnl;
	}

	public String getGvt() {
		return gvt;
	}

	public void setGvt(String gvt) {
		this.gvt = gvt;
	}

	public String getSercontel() {
		return sercontel;
	}

	public void setSercontel(String sercontel) {
		this.sercontel = sercontel;
	}

	public String getTim() {
		return tim;
	}

	public void setTim(String tim) {
		this.tim = tim;
	}

	public String getCtbc() {
		return ctbc;
	}

	public void setCtbc(String ctbc) {
		this.ctbc = ctbc;
	}

	public String getTelemar() {
		return telemar;
	}

	public void setTelemar(String telemar) {
		
		this.telemar = telemar;
	}

	public String getIpCorp() {
		return ipCorp;
	}

	public void setIpCorp(String ipCorp) {
		this.ipCorp = ipCorp;
	}

	public String getNexus() {
		return nexus;
	}


	public void setNexus(String nexus) {
		this.nexus = nexus;
	}

	public String getTelecom65() {
		return telecom65;
	}


	public void setTelecom65(String telecom65) {
		this.telecom65 = telecom65;
	}
	
	public String getCambridge() {
		return cambridge;
	}


	public void setCambridge(String cambridge) {
		this.cambridge = cambridge;
	}
	

	@Override
	protected boolean isDeleteEnabled() {
		return false;
	}
	
	/*

	public String getOperadoraClaro(){
		return getRow().getDsOperadoraClaro();
	}
	
	public String getEmbratel(){
		return formataDouble(getRow().getEmbratel());
	}
	
	
	public String getIntelig(){
		return formataDouble(getRow().getIntelig());
	}
	
	public String getBrasilTelecom(){
		return formataDouble(getRow().getBrasil_telecom());
	}
	
	public String getTelefonica(){
		return formataDouble(getRow().getTelefonica());
	}
	
	public String getTnl(){
		return formataDouble(getRow().getTnl());
	}
	
	public String getGvt(){
		return formataDouble(getRow().getGvt());
	}
	
	public String getSercomtel(){
		return formataDouble(getRow().getSercontel());
	}
	
	public String getTim(){
		return formataDouble(getRow().getTim());
	}
	
	public String getCtbc(){
		return formataDouble(getRow().getCtbc());
	}
	
	public String getTelemar(){
		return formataDouble(getRow().getTelemar());
	}
	
	public String getIpCorp(){
		return formataDouble(getRow().getIpCorp());
	}
	
	public String getNexus(){
		return formataDouble(getRow().getNexus());
	}
	*/

}
