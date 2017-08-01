package com.yumu.hexie.model.community;

import java.io.Serializable;

public class QueryIds implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6315723946316479469L;
	private String sect_id;
	private String zx_id;
	private String csp_id;
	private String pt_id;
	public String getSect_id() {
		return sect_id;
	}
	public void setSect_id(String sect_id) {
		this.sect_id = sect_id;
	}
	public String getZx_id() {
		return zx_id;
	}
	public void setZx_id(String zx_id) {
		this.zx_id = zx_id;
	}
	public String getCsp_id() {
		return csp_id;
	}
	public void setCsp_id(String csp_id) {
		this.csp_id = csp_id;
	}
	public String getPt_id() {
		return pt_id;
	}
	public void setPt_id(String pt_id) {
		this.pt_id = pt_id;
	}
	
}
