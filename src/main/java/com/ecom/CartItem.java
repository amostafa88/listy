package com.ecom;

import java.io.Serializable;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public abstract class  CartItem implements Serializable{
	
//	@Id
//	protected String id;
//	
//	//protected String id;

//	public String getId() {
//		return id;
//	}
//
//	public void setId(String id) {
//		this.id = id;
//	}


	protected double quantity;
	
	protected String comments;

	

	
	public CartItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CartItem(double quantity, String comments) {
		super();
		this.quantity = quantity;
		this.comments = comments;
	}
	
//	public String getId() {
//		return id;
//	}
//
//	public void setId(String id) {
//		this.id = id;
//	}
	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
	

}
