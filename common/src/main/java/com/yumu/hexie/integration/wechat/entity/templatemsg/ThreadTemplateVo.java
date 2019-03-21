package com.yumu.hexie.integration.wechat.entity.templatemsg;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ThreadTemplateVo {
	
	private static final long serialVersionUID = 4594080226499648498L;
	
	@JsonProperty("keyword1")
	private TemplateItem custName;	//业主姓名
	
	@JsonProperty("keyword2")
	private TemplateItem custMobile;	//联系方式
	
	@JsonProperty("keyword3")
	private TemplateItem custAddr;	//业主地址
	
	@JsonProperty("keyword4")
	private TemplateItem content;	//消息内容


	@JsonProperty("keyword5")
	private TemplateItem msgType;	//消息类型
	
	public TemplateItem getCustName() {
		return custName;
	}

	public void setCustName(TemplateItem custName) {
		this.custName = custName;
	}

	public TemplateItem getCustMobile() {
		return custMobile;
	}

	public void setCustMobile(TemplateItem custMobile) {
		this.custMobile = custMobile;
	}

	public TemplateItem getCustAddr() {
		return custAddr;
	}

	public void setCustAddr(TemplateItem custAddr) {
		this.custAddr = custAddr;
	}

	public TemplateItem getMsgType() {
		return msgType;
	}

	public void setMsgType(TemplateItem msgType) {
		this.msgType = msgType;
	}

	public TemplateItem getContent() {
		return content;
	}

	public void setContent(TemplateItem content) {
		this.content = content;
	}

	

}
