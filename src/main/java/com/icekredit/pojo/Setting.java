package com.icekredit.pojo;

import com.icekredit.enumeration.SettingType;
import com.icekredit.utils.MyString;

import java.io.Serializable;

public class Setting implements Serializable {

	private static final long serialVersionUID = 1L;
	private String id;
	private String key;
	private String value;
	private String remark;
	private String createTime;
	private byte status;
	private String type;
	private byte canDelete;
	private int sequence;

	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public byte getCanDelete() {
		return canDelete;
	}
	public void setCanDelete(byte canDelete) {
		this.canDelete = canDelete;
	}
	public String getTypeName(){
		if(!MyString.isEmpty(type)){
			return SettingType.getValue(type);
		}
		return "";

	}
}