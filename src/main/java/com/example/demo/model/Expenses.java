package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "Expenses")
public class Expenses {

	public Expenses() {
		super();
		
	}
	public Expenses(int expense_id, String key, String label_id, double expense) {
		super();
		this.expenseid = expense_id;
		this.key = key;
		this.labelid = label_id;
		this.expense = expense;
	}
	@Column(name="expense_id")
	@Id
	private int expenseid;
	@Column(name="key")
	private String key;
	@Column(name="label_id")
	private String labelid;
	@Column(name="expense")
	private double expense;
	
	
	public int getExpenseid() {
		return expenseid;
	}
	public void setExpenseid(int expense_id) {
		this.expenseid = expense_id;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getLabelid() {
		return labelid;
	}
	public void setLabelid(String label_id) {
		this.labelid = label_id;
	}
	public double getExpense() {
		return expense;
	}
	public void setExpense(double expense) {
		this.expense = expense;
	}
}
