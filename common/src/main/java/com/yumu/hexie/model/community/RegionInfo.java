package com.yumu.hexie.model.community;

import javax.persistence.Entity;

import com.yumu.hexie.model.BaseModel;

//资讯
@Entity
public class RegionInfo extends BaseModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5165892854588598286L;
	private String name;	//区域名称
	private String db_code;	//
	private String regionType;	//区域类型
	private long super_regionId;	//上级ID
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDb_code() {
		return db_code;
	}
	public void setDb_code(String db_code) {
		this.db_code = db_code;
	}
	public String getRegionType() {
		return regionType;
	}
	public void setRegionType(String regionType) {
		this.regionType = regionType;
	}
	public long getSuper_regionId() {
		return super_regionId;
	}
	public void setSuper_regionId(long super_regionId) {
		this.super_regionId = super_regionId;
	}
	
}
