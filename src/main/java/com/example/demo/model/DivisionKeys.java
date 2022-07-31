package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "DivisionKeys", 
uniqueConstraints = { 
		@UniqueConstraint(columnNames = "key"),
		@UniqueConstraint(columnNames = "region"),
		@UniqueConstraint(columnNames = "keyvalue")})

public class DivisionKeys {

	@Id
    private String key;
	private String region;
	private double keyvalue;
	
	
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public double getKeyvalue() {
		return keyvalue;
	}
	public void setKeyvalue(double keyvalue) {
		this.keyvalue = keyvalue;
	}
	
}
