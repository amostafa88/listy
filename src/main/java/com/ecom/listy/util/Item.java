package com.ecom.listy.util;

public class Item {
	
	private String name_en;
	private String name_ar;
	private float invQuantity;
	private String description;
	
	
	
	
	
	@Override
	public String toString() {
		return "Item [name_en=" + name_en + ", name_ar=" + name_ar
				+ ", invQuantity=" + invQuantity + ", description="
				+ description + "]";
	}



	public Item() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Item(String name_en, String name_ar, float invQuantity,
			String description) {
		super();
		this.name_en = name_en;
		this.name_ar = name_ar;
		this.invQuantity = invQuantity;
		this.description = description;
	}
	
	
	
	public String getName_en() {
		return name_en;
	}
	public void setName_en(String name_en) {
		this.name_en = name_en;
	}
	public String getName_ar() {
		return name_ar;
	}
	public void setName_ar(String name_ar) {
		this.name_ar = name_ar;
	}
	public float getInvQuantity() {
		return invQuantity;
	}
	public void setInvQuantity(float invQuantity) {
		this.invQuantity = invQuantity;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	

}
