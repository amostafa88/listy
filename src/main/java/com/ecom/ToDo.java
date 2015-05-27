package com.ecom;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public abstract class ToDo implements Serializable{

	@Id
	protected String id;

	protected boolean status;
	
	protected double quantity;
	
	protected double total;

	
	public ToDo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ToDo(boolean status, double quantity, double total) {
		super();
		this.status = status;
		this.quantity = quantity;
		this.total = total;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
	
	
}
